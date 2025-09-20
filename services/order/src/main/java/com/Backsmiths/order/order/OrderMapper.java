package com.Backsmiths.order.order;


import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder( OrderRequest request) {
        return new Order(request.id(), request.reference(), request.amount()
        ,request.paymentMethod(), request.customerId());
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
          order.getId(),
          order.getReference(),
          order.getTotalAmount(),
          order.getPaymentMethod(),
          order.getCustomerId()
        );
    }
}
