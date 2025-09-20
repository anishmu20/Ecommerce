package com.backsmiths.payment.payment;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer Id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
