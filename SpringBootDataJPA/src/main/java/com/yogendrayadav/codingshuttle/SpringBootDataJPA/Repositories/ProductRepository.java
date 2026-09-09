package com.yogendrayadav.codingshuttle.SpringBootDataJPA.Repositories;

import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>
{

    List<ProductEntity> findByQuantity(int quantity);

    Integer countByPrice(BigDecimal price);

    boolean existsByTitle(String title);

    void deleteBySku(String s);

    List<ProductEntity> findTop5ByCreatedAt(LocalDateTime dateTime);

    List<ProductEntity> findByPriceBetween(Integer i, Integer j);

    List<ProductEntity> findByTitleOrPrice(String title, BigDecimal price);

    List<ProductEntity> findBySkuContaining(String word);

    List<ProductEntity> findByQuantityLessThanAndPriceGreaterThan(Integer quantity, BigDecimal price);

    @Query("SELECT p FROM ProductEntity p WHERE p.title = :title AND p.price = :price")
    List<ProductEntity> findByNameAndRate(String title, BigDecimal price) ;

    @Query("SELECT pe FROM ProductEntity pe WHERE pe.quantity BETWEEN ?1 AND ?2")
    List<ProductEntity> findByStocksBetween(Integer i, Integer j) ;
}
