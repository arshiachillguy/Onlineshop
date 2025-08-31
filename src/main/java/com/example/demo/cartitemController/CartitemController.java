package com.example.demo.cartitemController;

import com.example.demo.CartITEMservice.cartitemservice;
import com.example.demo.cartitem.CartITEM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.InsufficientResourcesException;
import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartitemController {

    @Autowired
    private cartitemservice cartitemservice;

    @GetMapping("/manage")
    public String showCartManagementPage() {
        return "Basket";
    }

    @GetMapping("/cart")
    public String getitembycartid(@RequestParam Long cartid , Model model){
        try {
            List<CartITEM> items = cartitemservice.getItemsByCartId(cartid);
            model.addAttribute("cartitem" , items);
            model.addAttribute("success" , "cart item loaded successfully for cart id :" + cartid);
        } catch (RuntimeException e){
            model.addAttribute("error" , "Error" + e.getMessage());
        }
        return "Basket";
    }

    @PostMapping("/add-item")
    public String addItem(Long cartId , Long productId , Long quantity , Model model) throws InsufficientResourcesException, IllegalAccessException {
        try {
            CartITEM item = cartitemservice.additemtoCart(cartId, productId, quantity);
            model.addAttribute("succcess " , "item added successfully to cart id :" + cartId);
        } catch (IllegalAccessException e) {
            model.addAttribute("error", "Invalid input: " + e.getMessage());
        } catch (InsufficientResourcesException e) {
            model.addAttribute("error", "Insufficient stock: " + e.getMessage());
        } catch (RuntimeException e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }
    return "Basket";
    }

    @RequestMapping( value = "/{cartitemId}" , method = RequestMethod.PUT)
    public String updateItem(@PathVariable Long cartitemId, @RequestParam int newQuantity, Model model){
        try {
            cartitemservice.EditITEM(cartitemId, newQuantity);
            model.addAttribute("success", "Item updated successfully for ID: " + cartitemId);
        } catch (InsufficientResourcesException e) {
            model.addAttribute("error", "Insufficient stock: " + e.getMessage());
        } catch (RuntimeException e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }
        return "Basket";
    }

    @RequestMapping(value = "/{cartitemId}", method = RequestMethod.DELETE)
    public String deleteItem(@PathVariable Long cartitemId, Model model) {
        try {
            cartitemservice.DeleteITEM(cartitemId);
            model.addAttribute("success", "Item deleted successfully for ID: " + cartitemId);
        } catch (RuntimeException e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }
        return "Basket";
    }


}

