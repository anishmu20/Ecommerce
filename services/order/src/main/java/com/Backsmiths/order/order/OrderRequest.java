package com.Backsmiths.order.order;

import com.Backsmiths.order.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment Method is required")
        PaymentMethod paymentMethod,
        @NotNull(message = "CustomerId is required")
        @NotEmpty(message = "CustomerId is required")
        @NotBlank(message = "CustomerId is required")
        String customerId,
        @NotEmpty(message = "Atleast One product should be priced to make orderRequest ")
        List<PurchaseRequest> products

) {
}
