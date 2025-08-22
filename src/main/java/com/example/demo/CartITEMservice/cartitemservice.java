package com.example.demo.CartITEMservice;

import com.example.demo.CART.cart;
import com.example.demo.cartITEMRepository.cartitemrepository;
import com.example.demo.cartRepository.cartRepository;
import com.example.demo.cartitem.CartITEM;
import com.example.demo.products.Product;
import com.example.demo.serviceforproducts.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;

@Service
public class cartitemservice {


    @Autowired
    private cartitemrepository cartitemrepository;
    @Autowired
    private cartRepository cartRepository;
    @Autowired
    private ProductService productService;


    public CartITEM additemtoCart( Long cartid , Long productid , Long quantity ) throws IllegalAccessException, InsufficientResourcesException {
        // اعتبار سنجی میکنه که ایدی محصول یا سبد وجود داره یا نه
        if (cartid == null || productid == null || quantity == null || quantity <= 0) {
            throw  new IllegalAccessException("error");
        }

        Product product = productService.getProductByID(productid).orElseThrow(() -> new RuntimeException("not fount"));

        cart cart = cartRepository.findById(cartid).orElseThrow(()-> new RuntimeException("not found"));

        // چک موجودی
        if (product.getStock() < quantity ){
        throw new InsufficientResourcesException("we need money ");}

        CartITEM item = new CartITEM();
        item.setQuantity(Math.toIntExact(quantity));
        item.setProduct(product);
        item.setCart(cart);


        return cartitemrepository.save(item);
        }


}
