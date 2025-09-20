package com.backsmiths.payment.payment;

import com.backsmiths.payment.notification.NotificationProducer;
import com.backsmiths.payment.notification.PaymentNotificationRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private PaymentMapper mapper;

    @Autowired
    private NotificationProducer  producer;

    public Integer createPayment(@Valid PaymentRequest request) {

        var payment = repository.save(mapper.toPayment(request));
        log.info("Payment created: {}", request);
        producer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );
        return payment.getId();

//        2025-08-31T00:13:32.968+05:30  INFO 39586 --- [notification-service] [ntainer#0-0-C-1] [                                                 ] c.b.n.kafka.NotificationConsumer         : Received PaymentConfirmation request PaymentConfirmation[orderReference=MS-2025-8-31:00-11, amount=10000, paymentMethod=PAYPAL, customerFirstname=null, customerLastname=null, customerEmail=anishmu3020@gmail.com]

    }
}
