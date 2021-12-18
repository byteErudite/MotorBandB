package com.vaibhav.parkingReservation.controllers;

import com.vaibhav.parkingReservation.DTOs.NotificationDTO;
import com.vaibhav.parkingReservation.requests.NotificationSearchRequest;
import com.vaibhav.parkingReservation.response.NotificationSearchResponse;
import com.vaibhav.parkingReservation.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping(path = "/search")
    public ResponseEntity<NotificationSearchResponse> searchNotification(@RequestBody NotificationSearchRequest notificationSearchRequest, @RequestParam(required = false, defaultValue = "1") final Integer pageNo,
                                                                         @RequestParam(required = false, defaultValue = "20") final Integer pageSize,
                                                                         @RequestParam(required = false, defaultValue = "20") final String sort) {
        return new ResponseEntity<>(notificationService.getNotifications(notificationSearchRequest, pageNo, pageSize, sort), HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Map<String, List<NotificationDTO>>> createNotification(@RequestBody Set<NotificationDTO> notifications) {
        return new ResponseEntity<>(notificationService.createNotification(notifications), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteNotification(@RequestBody List<UUID> notificationIds) {
        return new ResponseEntity<>(notificationService.deleteNotifications(notificationIds), HttpStatus.OK);
    }
}
