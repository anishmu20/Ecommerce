package com.Backsmiths.order.orderline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class OrderLineService {

    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {

        var order=mapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();

    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId).stream().map(mapper::toOrderResponse).collect(Collectors.toList());

    }
}


