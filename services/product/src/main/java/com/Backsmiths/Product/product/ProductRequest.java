package com.Backsmiths.Product.product;


import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest (

         Integer id,
         @NotNull(message = "Product name is Required")
         String name,
         @NotNull(message = "Product des is Required")
         String description,
         @NotNull(message = "Available Quantity Should be positive")
         double availableQuantity,
         @NotNull(message = "Price Should be positive")
         BigDecimal price,
         @NotNull(message = "Product category is required")
         Integer categoryId


){
}
