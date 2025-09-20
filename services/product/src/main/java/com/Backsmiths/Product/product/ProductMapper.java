package com.Backsmiths.Product.product;

import com.Backsmiths.Product.category.Category;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {


    public Product toProduct(@Valid ProductRequest request) {
        return new Product(
                request.id(), request.name(), request.description(), request.availableQuantity(), request.price(),new Category (request.categoryId()
        ));

    }

    public ProductResponse toProductResponse( Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
              return  new ProductPurchaseResponse(
                      product.getId(),
                      product.getName(),
                      product.getDescription(),
                      product.getPrice(),
                      quantity
              );
    }
}
