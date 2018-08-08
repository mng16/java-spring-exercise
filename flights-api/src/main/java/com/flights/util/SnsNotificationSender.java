package com.flights.util;

import com.amazonaws.services.sns.AmazonSNS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;

public class SnsNotificationSender {

    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @Autowired
    public SnsNotificationSender(AmazonSNS amazonSns) {
        this.notificationMessagingTemplate = new NotificationMessagingTemplate(amazonSns);
    }

    public void send(String subject, String message) {
        this.notificationMessagingTemplate.sendNotification("FlightUpdate", message, subject);
    }
}