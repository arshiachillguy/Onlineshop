package com.example.demo.ShoppingcartService;

import com.example.demo.ShoppingcartRepository.shoppingcartRepository;
import com.example.demo.cartITEMRepository.cartitemrepository;
import com.example.demo.products.Product;
import com.example.demo.serviceforproducts.ProductService;
import jakarta.transaction.Transactional;
import org.apache.el.lang.ELArithmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class shoppingcartservice {
    @Autowired
    private shoppingcartRepository shoppingcartRepository;

    @Autowired
    private cartitemrepository cartitemrepository;

    @Autowired
    private ProductService productService;

    // add new product to order basket
    public void addITEM(Long cartID , long productId , int quantity){
       //check this product to understand it is exits or not
        Optional<Product> product = Optional.ofNullable(productService.getProductByID(productId).orElseThrow(() -> new RuntimeException("not found this product")));
    }


}
