package com.Backsmiths.order.order;

import java.math.BigDecimal;

public record OrderResponse(
        Integer orderId,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
