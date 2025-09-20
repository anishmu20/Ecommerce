package com.Backsmiths.Product.product;

import com.Backsmiths.Product.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private  ProductRepository repository;
    @Autowired
    private  ProductMapper mapper;

    public Integer createProduct(@Valid ProductRequest request) {
        var product= mapper.toProduct(request);

        return repository.save(product).getId();
    }
    // revisit this method
    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request) {
        var productIds=request.stream().map(ProductPurchaseRequest::productId).toList();
        var storedProducts=repository.findAllByIdInOrderById(productIds);
        if(productIds.size()!=storedProducts.size()){
            throw new ProductPurchaseException("One or more products does not exists");
        }

        var storedRequest= request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts=new ArrayList<ProductPurchaseResponse>();
        for (int i=0;i<storedRequest.size();i++){
            var product=storedProducts.get(i);
            var productRequest=storedRequest.get(i);
            if (product.getAvailableQuantity()<productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock for product with Id:"+productRequest.productId());
            }
            var newAvailableQuantity=product.getAvailableQuantity()-productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product,productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(()-> new EntityNotFoundException("Product does not exists with this id "+productId));
    }

    public List<ProductResponse> findAll() {
        return repository.findAll().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }
}
