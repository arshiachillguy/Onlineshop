package com.example.demo.CartITEMservice;

import com.example.demo.CART.cart;
import com.example.demo.cartITEMRepository.cartitemrepository;
import com.example.demo.cartRepository.cartRepository;
import com.example.demo.cartitem.CartITEM;
import com.example.demo.products.Product;
import com.example.demo.serviceforproducts.ProductService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import java.util.List;
import java.util.Optional;

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

    //edit order list
    public CartITEM EditITEM(Long cartitemId , int newquantity ) throws InsufficientResourcesException {

        if (newquantity < 0 ) {
            throw new InsufficientResourcesException("quantity should be greater than zero !");
        }

        if (newquantity == 0){
            throw new InsufficientResourcesException("quantity most grater than zero");
        }

        CartITEM item = cartitemrepository.findById(cartitemId).orElseThrow(()-> new RuntimeException("cart item not found "));
        Product product = item.getProduct();

        // check balance
        int mainQuantity =   newquantity - item.getQuantity();
        if ( mainQuantity > 0 && product.getStock() < mainQuantity){
            throw new InsufficientResourcesException("Insufficient stock for product ID: \"" + product.getId());
        }

        item.setQuantity(newquantity);
        return cartitemrepository.save(item);

    }

    public void DeleteITEM(Long cartitemId){
        CartITEM item1 = cartitemrepository.findById(cartitemId).orElseThrow(()->new RuntimeException("Cart item not found for ID: " + cartitemId));
        Product product = item1.getProduct();
        if (product == null){
            throw new RuntimeException("Product not found for cart item ID: \"" + cartitemId);
        }
        int quantity = item1.getQuantity();
        product.setStock(product.getStock()+ quantity);
        productService.saveProducts(product);

        cartitemrepository.delete(item1);
    }

    public List<CartITEM> getItemsByCartId(Long cartId){

        if (cartId == null ){
            throw new RuntimeException("Cart ID cannot be null  " + cartId);
        }
        cartRepository.findById(cartId).orElseThrow(()-> new RuntimeException("cart not found for id :" + cartId));
        List<CartITEM> item1 = cartitemrepository.findByCartId(cartId);

        return item1;
    }


















}
