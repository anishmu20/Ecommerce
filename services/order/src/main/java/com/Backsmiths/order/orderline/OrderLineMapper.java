package com.Backsmiths.order.orderline;

import com.Backsmiths.order.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return new OrderLine(orderLineRequest.id(),new Order(orderLineRequest.orderId()), orderLineRequest.productId(), orderLineRequest.quantity());
    }

    public OrderLineResponse toOrderResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
