package com.example.demo.cartRepository;

import com.example.demo.CART.cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface cartRepository extends JpaRepository<cart, Long> {
}
