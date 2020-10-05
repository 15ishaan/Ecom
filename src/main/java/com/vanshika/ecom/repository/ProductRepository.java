package com.vanshika.ecom.repository;

import com.vanshika.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.name=:name")
    Iterable<Product> findUsingName(String name);

    @Query("select p from Product p where p.id=:id")
    Iterable<Product> findUsingId(Long id);

    @Query("select p from Product p where p.id=:id")
    Product findProductUsingId(Long id);

    @Query("select p from Product p where p.category=:category")
    Iterable<Product> findUsingCategory(String category);

    @Query("select p from Product p where p.subCategory=:subCategory")
    Iterable<Product> findUsingSubCategory(String subCategory);

    @Query("select p from Product p where p.category=:category and p.subCategory=:subCategory")
    Iterable<Product> findUsingCategoryAndSubCategory(String category, String subCategory);

    @Query("select p from Product p where p.prodType=:prodType")
    Iterable<Product> findUsingProductType(String prodType);

    @Query("select p from Product p where p.category=:category and p.prodType=:prodType")
    Iterable<Product> findUsingCategoryAndProductType(String category, String prodType);

    @Query("select p from Product p where p.sellerUsername=:sellerUsername")
    Iterable<Product> findByUsername(String sellerUsername);

    @Query(nativeQuery=true, value="select * from PRODUCT order by RAND() LIMIT 8")
    Iterable<Product> findFeaturedProducts();

    @Query(nativeQuery=true, value="select * from PRODUCT where PRODUCT.category=:category order by RAND() LIMIT 8")
    Iterable<Product> findPersonalisedProducts(String category);

    @Transactional
    @Modifying
    @Query("update Product p set p.totalRating=:totalRating, p.totalUser=:totalUser where p.id=:id")
    void setProductRating(Double totalRating, Integer totalUser, Long id);

    @Transactional
    @Modifying
    @Query("update Product p set p.picByte = ?2 where p.id = ?1")
    void setImageByteForProduct(Long id, byte[] picByte);

}

