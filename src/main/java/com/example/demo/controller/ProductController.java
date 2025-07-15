package com.example.demo.controller;

import com.example.demo.products.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    public ProductService productService;

    @GetMapping(path = "api/products")
    public List<Product> GetallProduct(){
        return productService.getAllProducts();
    }

    @PostMapping(path = "api/products")
    public Product postProducts(@RequestBody Product product){
        return productService.saveProducts(product);
    }

    @GetMapping(path = "api/products/{id}")
    public Optional<Product> getproductById(@PathVariable long id){
        return productService.getProductByID(id);
    }


    @DeleteMapping(path = "api/products/{id}")
    public void DeleteproductsById(@PathVariable long id){

    }

}
