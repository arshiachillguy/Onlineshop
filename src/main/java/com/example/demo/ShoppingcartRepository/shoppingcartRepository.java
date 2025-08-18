package com.example.demo.ShoppingcartRepository;

import com.example.demo.ShoppingCart.Shoppingcart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface shoppingcartRepository extends JpaRepository<Shoppingcart, Long> {
}
