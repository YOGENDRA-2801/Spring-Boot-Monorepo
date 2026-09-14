package com.yogendrayadav.codingshuttle.SpringBootDataJPA.controllers;

import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.ProductEntity;
import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository ;
    private final Integer PAGE_SIZE = 5 ;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/orderby")
    public List<ProductEntity> sortingViaOrderBy() {
//     return  productRepository.findByOrderByTitleAsc() ;
        return productRepository.findByOrderByTitleDesc() ;
    }

    @GetMapping("/sortclass")
    public List<ProductEntity> sortingViaSortClass(@RequestParam(name = "field") String productField) {
        return productRepository.findAll(
                Sort.by(Sort.Order.asc(productField), Sort.Order.desc("title"))
        );
    }

    Pageable pageable = PageRequest.of(0, PAGE_SIZE) ;

    @GetMapping("/pageNoSort")
    public Page<ProductEntity> pageWithoutSort() {
        return productRepository.findAll(pageable);
    }

    @GetMapping("/pageWithSort")
    public Page<ProductEntity> pageWithSort(@RequestParam(name = "number", defaultValue = "0") Integer pageNumber) {
        return productRepository.findAll(
                PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("title")));
    }

    @GetMapping("/mixup")
    public List<ProductEntity> sortFilterPage(
            @RequestParam(name = "number", defaultValue = "0") Integer pageNumber
    ) {
        return productRepository.
                findBySkuContaining("001", PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("title").descending()) ) ;
    }

}
