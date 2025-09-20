package com.Backsmiths.order.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "PRODUCT",
        path = "/api/v1/products"
)
public interface ProductClient {

    @PostMapping("/purchase")
    Optional<List<PurchaseResponse>> purchaseProduct(
            @RequestBody List<PurchaseRequest> request
    );





//    @PostMapping("/purchase")
//    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(
//            @RequestBody List<ProductPurchaseRequest> request
//    ){
//        return ResponseEntity.ok(service.purchaseProduct(request));
//    }



}
