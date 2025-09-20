package com.backsmiths.notification.notification;

import com.backsmiths.notification.kafka.order.OrderConfirmation;
import com.backsmiths.notification.kafka.payment.PaymentConfirmation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Notification {
    @Id
    private  String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;

    public Notification() {
    }

    public Notification(String id, NotificationType type, LocalDateTime notificationDate, PaymentConfirmation paymentConfirmation) {
        this.id = id;
        this.type = type;
        this.notificationDate = notificationDate;
        this.paymentConfirmation = paymentConfirmation;
    }

    public Notification(String id, NotificationType type, LocalDateTime notificationDate, OrderConfirmation orderConfimation) {
        this.id = id;
        this.type = type;
        this.notificationDate = notificationDate;
        this.orderConfirmation = orderConfirmation;
    }

    public Notification(String id, NotificationType type, LocalDateTime notificationDate, OrderConfirmation orderConfirmation, PaymentConfirmation paymentConfirmation) {
        this.id = id;
        this.type = type;
        this.notificationDate = notificationDate;
        this.orderConfirmation = orderConfirmation;
        this.paymentConfirmation = paymentConfirmation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public LocalDateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public OrderConfirmation getOrderConfimation() {
        return orderConfirmation;
    }

    public void setOrderConfimation(OrderConfirmation orderConfimation) {
        this.orderConfirmation = orderConfimation;
    }

    public PaymentConfirmation getPaymentConfirmation() {
        return paymentConfirmation;
    }

    public void setPaymentConfirmation(PaymentConfirmation paymentConfirmation) {
        this.paymentConfirmation = paymentConfirmation;
    }


}
