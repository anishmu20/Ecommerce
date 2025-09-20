package com.backsmiths.payment.payment;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(@Valid PaymentRequest request) {
        return  new Payment(
                request.Id(),
                request.amount(),
                request.paymentMethod(),
                request.orderId()
        );
    }
}
