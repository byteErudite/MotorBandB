package com.vaibhav.parkingReservation.customRepositories;

import com.vaibhav.parkingReservation.DTOs.NotificationDTO;
import com.vaibhav.parkingReservation.exceptions.SortParamUnavailableException;
import com.vaibhav.parkingReservation.requests.NotificationSearchRequest;
import com.vaibhav.parkingReservation.response.NotificationSearchResponse;
import com.vaibhav.parkingReservation.response.Page;
import com.vaibhav.parkingReservation.utilities.CommonUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class NotificationCustomRepository {

    @Autowired
    RootCustomRepository rootCustomRepository;

    private static final Set<String> PERMITTED_SORT_PARAMS = Set.of("message","createdAt","notificationType");

    private static final String NOTIFICATION_SEARCH_QUERY = "Select distinct new com.vaibhav.parkingReservation.DTOs.NotificationDTO " +
            "( notification.notificationId as notificationId, " +
            "notification.message as message, " +
            "notification.createdAt as createdAt, " +
            "notification.isDeleted as isDeleted, " +
            "notification.notificationType as notificationType, " +
            "notification.isRead as isRead, " +
            "notification.user.userId as userId ) ";

    private static final String NOTIFICATION_FROM_QUERY = " from Notification notification ";

    private String DISTINCT_NOTIFICATION_COUNT_QUERY = " select count(notification.notificationId) ";

    private String NOT_DELETED_CLAUSE = " isDeleted = false ";

    private String SORT_BY = " order by ";

    public NotificationSearchResponse searchNotifications(NotificationSearchRequest notificationSearchRequest, int pageNumber, int pageSize, String sort) {
        Map<String, Object> parameters = new HashMap<>();
        final String whereQuery = generateWhereQueryForSlotSearch(notificationSearchRequest, parameters);
        int totalCount = getTotalCountForSearchRequest(DISTINCT_NOTIFICATION_COUNT_QUERY, parameters, whereQuery, NOTIFICATION_FROM_QUERY);
        if (totalCount == 0) {
            return new NotificationSearchResponse(new Page(pageSize, 0, 0), Collections.emptyList());
        }
        String sortQuery = "";
        if (Objects.nonNull(sort)) {
            sortQuery = generateOrderQuery(sort);
        }
        List<NotificationDTO> notifications = rootCustomRepository.getQueryResult(NOTIFICATION_SEARCH_QUERY + NOTIFICATION_FROM_QUERY + whereQuery + sortQuery, parameters, pageNumber, pageSize, NotificationDTO.class);
        NotificationSearchResponse notificationSearchResponse = new NotificationSearchResponse(new Page(pageSize, totalCount, pageNumber), notifications);
        return notificationSearchResponse;
    }

    private String generateOrderQuery(String sort) {
        StringBuilder sortQueryResult = new StringBuilder();
        String[] sortParams = sort.split("-");
        for(String sortParam : sortParams) {
            if (!PERMITTED_SORT_PARAMS.contains(sortParam)) {
                throw new SortParamUnavailableException("Sorting is not available on : "+sortParam);
            }
            sortQueryResult.append(sortParam+", ");
        }
        return sortQueryResult.length() > 0 ? "" : sortQueryResult.toString().substring(0, sortQueryResult.length() -2);
    }

    private Integer getTotalCountForSearchRequest(final String distinctQuery, final Map<String, Object> parameterMap, final String whereQuery,
                                                  final String fromQuery) {
        StringBuilder sql = new StringBuilder(
                whereQuery.length() + fromQuery.length() + distinctQuery.length() + 1);
        Long queryCount = rootCustomRepository.getQueryCount(
                sql.append(distinctQuery).append(fromQuery).append(whereQuery).toString(),
                parameterMap);
        return queryCount.intValue();
    }

    private String generateWhereQueryForSlotSearch(NotificationSearchRequest notificationSearchRequest, Map<String, Object> parameters) {
        StringBuilder whereQuery = new StringBuilder(" where ");
        if (CommonUtilities.isNotEmpty(notificationSearchRequest.getNotificationIds())) {
            Set<UUID> notificationIds = notificationSearchRequest.getNotificationIds().stream().map(notificationId -> UUID.fromString(notificationId)).collect(Collectors.toSet());
            parameters.put("notificationIds", notificationIds);
            whereQuery.append(" notification.notificationId in (:notificationIds) and ");
        }

        if (CommonUtilities.isNotEmpty(notificationSearchRequest.getUserIds())) {
            Set<UUID> userIds = notificationSearchRequest.getUserIds().stream().map(userId -> UUID.fromString(userId)).collect(Collectors.toSet());
            parameters.put("userIds", userIds);
            whereQuery.append(" notification.userId in (:userIds) and ");
        }

        if (Objects.nonNull(notificationSearchRequest.getCreatedAt())) {
            parameters.put("createdAt", notificationSearchRequest.getCreatedAt());
            whereQuery.append(" notification.createdAt = :createdAt and ");
        }

        if (Objects.nonNull(notificationSearchRequest.getDeleted())) {
            parameters.put("isDeleted", notificationSearchRequest.getDeleted());
            whereQuery.append(" notification.isDeleted = :isDeleted and ");
        }

        if (CommonUtilities.isNotEmpty(notificationSearchRequest.getNotificationTypes())) {
            parameters.put("notificationTypes", notificationSearchRequest.getNotificationTypes().stream().map(notificationType -> String.valueOf(notificationType)).collect(Collectors.toSet()));
            whereQuery.append(" notification.notificationTypes = :notificationTypes and ");
        }

        if (whereQuery.length() < 8) {
            return "where s.isDeleted = false ";
        }
        return whereQuery.append(" s.isDeleted = false and sa.isDeleted = false ").toString();
    }
}
