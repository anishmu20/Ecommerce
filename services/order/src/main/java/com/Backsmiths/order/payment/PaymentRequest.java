package com.Backsmiths.order.payment;

import com.Backsmiths.order.customer.CustomerResponse;
import com.Backsmiths.order.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
