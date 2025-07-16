package com.example.demo.controller;


import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductViewController {
    @Autowired
    public ProductService productService;

    @GetMapping("/v1/products/view")
    public String showproducts(@org.jetbrains.annotations.NotNull Model model){
        model.addAttribute("products" , productService.getAllProducts());
        return "product-list";
    }

}
