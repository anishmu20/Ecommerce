package com.backsmiths.payment.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationProducer {

    @Autowired
    private  KafkaTemplate<String,PaymentNotificationRequest> kafkaTemplate;

    public void sendNotification(PaymentNotificationRequest notificationRequest) {
        log.info("Sending notification: {}", notificationRequest);
        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(notificationRequest)
                .setHeader(KafkaHeaders.TOPIC,"payment-topic")
                .build();
        kafkaTemplate.send(message);

    }

}
