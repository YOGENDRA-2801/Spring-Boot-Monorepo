package com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "product", columnNames = {"title", "unit_price"})
        },
        indexes = {
                @Index(name = "sku_index", columnList = "stock_keeping_unit")
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "stock_keeping_unit", nullable = false, length = 50)
    private String sku ;

    @Column(length = 50, nullable = false)
    private String title ;

    private Integer quantity ;

    @Column(name = "unit_price")
    private BigDecimal price ;

    @CreationTimestamp
    private LocalDateTime createdAt ;

    @UpdateTimestamp
    private LocalDateTime updatedAt ;
}
