package com.example.demo.controller;

import com.example.demo.products.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/products")
public class ProductController {

    @Autowired
    public ProductService productService;


    @GetMapping("/hello")
    public String hello(){
        return "hello guys";
    }

    @GetMapping
    public List<Product> GetallProduct(){
        return productService.getAllProducts();
    }

    @PostMapping
    public Product postProducts(@Valid @RequestBody Product product){
        return productService.saveProducts(product);
    }

    @GetMapping("/{id}")
    public Optional<Product> getproductById(@PathVariable long id){
        return productService.getProductByID(id);
    }


    @DeleteMapping("/{id}")
    public void DeleteproductsById(@PathVariable long id){
        productService.deleteProductByID(id);
    }

}
