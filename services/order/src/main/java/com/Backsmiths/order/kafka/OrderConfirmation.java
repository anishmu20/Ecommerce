package com.Backsmiths.order.kafka;

import com.Backsmiths.order.customer.CustomerResponse;
import com.Backsmiths.order.order.PaymentMethod;
import com.Backsmiths.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
