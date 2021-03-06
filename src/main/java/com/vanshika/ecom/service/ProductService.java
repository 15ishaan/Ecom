package com.vanshika.ecom.service;


import com.vanshika.ecom.model.Product;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface ProductService {

    @NotNull Iterable<Product> getAllProducts();
    Product findUsingName(String name);
    Iterable<Product> findUsingId(Long id);
    Product findProductUsingId(Long id);
    Iterable<Product> findUsingCategory(String category);
    Iterable<Product> findUsingSubCategory(String subCategory);
    Iterable<Product> findUsingCategoryAndSubCategory(String category, String subCategory);
    Iterable<Product> findUsingProductType(String prodType);
    Iterable<Product> findUsingCategoryAndProductType(String category, String prodType);
    Iterable<Product> findByUsername(String sellerUsername);
    Iterable<Product> findFeaturedProducts();
    Iterable<Product> findPersonalisedProducts(String category);
    Double getProductRating(Double rating, Long id);
    void addProduct(Product product);
}
