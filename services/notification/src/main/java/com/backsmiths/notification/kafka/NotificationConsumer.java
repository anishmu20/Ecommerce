package com.backsmiths.notification.kafka;

import com.backsmiths.notification.email.EmailService;
import com.backsmiths.notification.kafka.order.OrderConfirmation;
import com.backsmiths.notification.kafka.payment.PaymentConfirmation;
import com.backsmiths.notification.notification.Notification;
import com.backsmiths.notification.notification.NotificationRepository;
import com.backsmiths.notification.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class NotificationConsumer {

    @Autowired
    private  NotificationRepository repository;

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentNotification(PaymentConfirmation request) throws MessagingException {
        log.info("Received PaymentConfirmation request {}", request);
        repository.save(
                new Notification(
                        UUID.randomUUID().toString(),
                        NotificationType.PAYMENT_CONFIRMATION,
                        LocalDateTime.now(),
                        request

                )
        );

        // todo send email
        log.info("PaymentRequestItems  {}", request);
        emailService.sendPaymentSuccessEmail(
                request.customerEmail(),
                request.customerFirstName()+" "+request.customerLastName(),
                request.amount(),
                request.orderReference()
        );

    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Received PaymentConfirmation request {}", orderConfirmation);
        repository.save(
                new Notification(
                        UUID.randomUUID().toString(),
                        NotificationType.ORDER_CONFIRMATION,
                        LocalDateTime.now(),
                        orderConfirmation

                )
        );
        log.info("OrderConfirmation  {}", orderConfirmation);
        // todo send email
        emailService.sendOrderSuccessEmail(
                orderConfirmation.customer().email(),
                orderConfirmation.customer().firstname()+" "+orderConfirmation.customer().lastname(),
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );

    }



}

