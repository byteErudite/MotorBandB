package com.vaibhav.parkingReservation.serviceImpl;

import com.vaibhav.parkingReservation.DTOs.NotificationDTO;
import com.vaibhav.parkingReservation.customRepositories.NotificationCustomRepository;
import com.vaibhav.parkingReservation.entity.Notification;
import com.vaibhav.parkingReservation.entity.User;
import com.vaibhav.parkingReservation.exceptions.ResourceCreationFailureException;
import com.vaibhav.parkingReservation.exceptions.ResourceUpdateFailureException;
import com.vaibhav.parkingReservation.logUtil.LoggerHelper;
import com.vaibhav.parkingReservation.mapper.NotificationMapper;
import com.vaibhav.parkingReservation.producers.NotificationProducer;
import com.vaibhav.parkingReservation.repositories.NotificationRepository;
import com.vaibhav.parkingReservation.repositories.UserRepository;
import com.vaibhav.parkingReservation.requests.NotificationSearchRequest;
import com.vaibhav.parkingReservation.response.NotificationSearchResponse;
import com.vaibhav.parkingReservation.services.NotificationService;
import org.hibernate.graph.EntityGraphs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;


import static com.vaibhav.parkingReservation.constants.Constants.ERROR;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationCustomRepository notificationCustomRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    NotificationMapper notificationMapper;

    @Autowired
    LoggerHelper loggerHelper;

    @Autowired
    NotificationProducer notificationProducer;

    @Override
    public NotificationSearchResponse getNotifications(NotificationSearchRequest notificationSearchRequest, int page, int size, String sort) {
        try {
            return notificationCustomRepository.searchNotifications(notificationSearchRequest, page, size, sort);
        } catch (Exception e) {
            loggerHelper.write(ERROR, "NOTIFICATION SEARCH FAILED : request -> {} : ", notificationSearchRequest.toString(), e, e.getMessage());
            throw e;
        }
    }

    @Override
    public Map<String, List<NotificationDTO>> createNotification(Set<NotificationDTO> notifications) {
        if (CollectionUtils.isEmpty(notifications)) {
            return Collections.emptyMap();
        }

        Map<UUID, User> usersByuserId = findUsersFromNotifications.apply(notifications);
        List<NotificationDTO> successCreation = new ArrayList<>();
        List<NotificationDTO> failureCreation = new ArrayList<>();
        notifications.stream().forEach(notification -> {
            try {
                if (!isUserSetInNotification(usersByuserId, notification)) {
                    throw new ResourceCreationFailureException("Invalid userId");
                }
                Notification saveNotification = createNewNotification(notification, usersByuserId.get(notification.getUserId()));
                //save notification or send to kafka producer
                notificationProducer.sendActivityNotification(notification);
                notificationRepository.save(saveNotification.forUser(usersByuserId.get(notification.getUserId())));
                successCreation.add(notificationMapper.notificationToNotificationDTO(saveNotification));
            } catch (Exception e) {
                loggerHelper.write(ERROR, "NOTIFICATION CREATION FAILED : request -> {} : ", notification.toString(), e, e.getMessage());
                failureCreation.add(notification);
            }
        });
        return Map.of("SUCCESS", successCreation, "FAILED", failureCreation);
    }

    public String deleteNotifications(List<UUID> notificationIds) {
        if (CollectionUtils.isEmpty(notificationIds)) {
            return "No notification Ids found to delete";
        }

        try {
            findANotificationsAndMarkDeleted(notificationIds);
        } catch (Exception e) {
            loggerHelper.write(ERROR, "Batch notification deletion failed : request -> {} : ", notificationIds.toString(), e, e.getMessage());
            throw new ResourceUpdateFailureException("Batch deletion failed for notification Ids : " + notificationIds);
        }
        return "success";
    }

    private void findANotificationsAndMarkDeleted(List<UUID> notificationIds) {
        List<Notification> notifications = notificationRepository.findAllById(notificationIds.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        notifications.stream().forEach(notification -> notification.setDeleted(true));
        notificationRepository.saveAll(notifications);
    }

    private boolean isUserSetInNotification(Map<UUID, User> userByUserId, NotificationDTO notification) {
        return userByUserId.containsKey(notification.getUserId());
    }

    Function<Set<NotificationDTO>, Map<UUID, User>> findUsersFromNotifications =
            this.collectUserIdsFromNotifications()
                    .andThen(searchUserIdsInDatabase()
                            .andThen(collectUsersByUserId()));

    Function<Set<NotificationDTO>, Set<UUID>> collectUserIdsFromNotifications() {
        return notifications -> notifications.stream().
                map(NotificationDTO::getUserId).
                filter(Objects::nonNull).collect(Collectors.toSet());
    }

    Function<Set<UUID>, List<User>> searchUserIdsInDatabase() {
        return userIds -> userRepository.findAllById(userIds);
    }

    Function<List<User>, Map<UUID, User>> collectUsersByUserId() {
        return users -> users.stream().
                collect(Collectors.toMap(User::getUserId, Function.identity(), (first, second) -> first));
    }


    public Notification createNewNotification(NotificationDTO notificationDTO, User user) {
        return new Notification().
                addMessage(notificationDTO.getMessage()).
                setType(notificationDTO.getNotificationType()).
                forUser(user).
                create();
    }

}
