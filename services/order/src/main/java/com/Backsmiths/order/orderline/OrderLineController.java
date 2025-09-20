package com.Backsmiths.order.orderline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
public class OrderLineController {

    @Autowired
    private OrderLineService service;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderLineResponse>> getOrderLine(@PathVariable("orderId") Integer orderId) {
        return ResponseEntity.ok(service.findAllByOrderId(orderId));
    }

}
