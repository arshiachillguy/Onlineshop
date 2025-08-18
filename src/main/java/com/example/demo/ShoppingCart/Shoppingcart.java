package com.example.demo.ShoppingCart;

import com.example.demo.Users.User;
import com.example.demo.cartitem.CartITEM;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

public class Shoppingcart {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "cart")
    private List<CartITEM> items = new ArrayList<>(); // لیست آیتم‌های سبد

    @OneToOne
    private User user;
}
