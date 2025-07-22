package com.example.demo.controllerforproducts;

import com.example.demo.products.Product;
import com.example.demo.serviceforproducts.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductViewController {
    @Autowired
    private ProductService productService;

//    show all products
    @GetMapping("/v1/products/view")
    public String showproducts( Model model){
        model.addAttribute("products" , productService.getAllProducts());
        return "product-list";
    }
// show form for add new products
    @GetMapping("/v1/products/add")
    public String showaddform(Model model){
        model.addAttribute("product" , new Product());
        return "product-form";
    }

// save that new products
    @PostMapping("/v1/products/save")
    public String saveproducts(@ModelAttribute Product product , Model model ){
        productService.saveProducts(product);
        return "redirect:/v1/products/view";
    }


}
