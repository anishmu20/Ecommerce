package com.Backsmiths.order.orderline;

import org.springframework.stereotype.Service;


public record OrderLineRequest(

        Integer id,
        Integer orderId,
        Integer productId,
        double quantity)
{

}
