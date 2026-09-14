package com.yogendrayadav.codingshuttle.SpringBootDataJPA;

import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class ProductServiceTest
{

	@Autowired
	private ProductRepository productRepository ;

	@Test
	public void CrudOperations() {
		System.out.println(productRepository.findAll());
	}

	@Test
	@Transactional
	@Rollback(value = false)
	public void querySubjectKeywords() {
		System.out.println( productRepository.findByQuantity(30) );
		System.out.println( productRepository.existsByTitle("LED Table Lamp") );
		System.out.println( productRepository.countByPrice(BigDecimal.valueOf(599)) );
		productRepository.deleteBySku("SKU-BOOK-002") ;
		System.out.println( productRepository.findTop5ByCreatedAt(LocalDateTime.of(2026, 8, 16, 21, 39, 32)) );
	}

	@Test
	public void predicateKeyword() {
		System.out.println( productRepository.findByPriceBetween(100,500) );
		System.out.println( productRepository.findByTitleOrPrice("Wireless Mouse", BigDecimal.valueOf(599.00)) );
		System.out.println( productRepository.findBySkuContaining("eLEc") );
		System.out.println( productRepository.findByQuantityLessThanAndPriceGreaterThan(50, BigDecimal.valueOf(599.00)) );
	}

	@Test
	public void customQuery() {
		System.out.println( productRepository.findByNameAndRate("Mechanical Keyboard", BigDecimal.valueOf(2499.00)) );
		System.out.println( productRepository.findByStocksBetween(50, 100) );
	}

}
