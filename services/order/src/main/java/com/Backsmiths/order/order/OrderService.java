package com.Backsmiths.order.order;

import com.Backsmiths.order.customer.CustomerClient;
import com.Backsmiths.order.exception.BusinessException;
import com.Backsmiths.order.kafka.OrderConfirmation;
import com.Backsmiths.order.kafka.OrderProducer;
import com.Backsmiths.order.orderline.OrderLineRequest;
import com.Backsmiths.order.orderline.OrderLineService;
import com.Backsmiths.order.payment.PaymentClient;
import com.Backsmiths.order.payment.PaymentRequest;
import com.Backsmiths.order.product.ProductClient;
import com.Backsmiths.order.product.PurchaseRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private CustomerClient customerClient;
    @Autowired
    private ProductClient productClient;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineService orderLineService;

    @Autowired
    private OrderMapper mapper;

    @Autowired
    private  OrderProducer  orderProducer;

    @Autowired
    private PaymentClient paymentClient;





    public Integer createOrder(@Valid OrderRequest request) {
        // check customer is present --- > OpenFeign

        var customer=customerClient.findCustomerById(request.customerId())
                .orElseThrow(()->new BusinessException("Cannot create order -> No customer is present with Id : "+request.customerId()));

        // purchase product  --> product-ms
        var productList=productClient.purchaseProduct(request.products())
                .orElseThrow(()->new BusinessException("An error while processing with product"));

        // persist order
        var order=orderRepository.save(mapper.toOrder(request));


        // persist order lines

        for (PurchaseRequest purchaseRequest : request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );

        }


        //start payment process
         paymentClient.requestOrderPayment(new PaymentRequest(
                request.amount(),
                 request.paymentMethod(),
                 order.getId(),
                 order.getReference(),
                 customer
         ));

        // send the order confirmation --> notification-ms (kafka)

        orderProducer.sendOrder(new OrderConfirmation(
                request.reference(),
                request.amount(),
                request.paymentMethod(),
                customer,
                productList
        ));





        return order.getId();

    }

    public List<OrderResponse> findAll() {

        return orderRepository.findAll().stream().map(mapper::fromOrder).collect(Collectors.toList());
    }

    public OrderResponse findOrderById(Integer orderId) {
        return orderRepository.findById(orderId).map(mapper::fromOrder).orElseThrow(() ->new BusinessException("Order with provided id does not exist"));
    }
}
