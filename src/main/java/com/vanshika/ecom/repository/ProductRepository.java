package com.vanshika.ecom.repository;

import com.vanshika.ecom.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("select p from Product p where p.name=:name")
    Iterable<Product> findUsingName(String name);

    @Query("select p from Product p where p.category=:category")
    Iterable<Product> findUsingCategory(String category);

    @Query("select p from Product p where p.subCategory=:subCategory")
    Iterable<Product> findUsingSubCategory(String subCategory);

    @Query("select p from Product p where p.category=:category and p.subCategory=:subCategory")
    Iterable<Product> findUsingCategoryAndSubCategory(String category, String subCategory);

    @Query("select p from Product p where p.category=:id")
    Product findUsingId(Long id);
}

