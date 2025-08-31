package com.example.demo.cartITEMRepository;

import com.example.demo.cartitem.CartITEM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface cartitemrepository extends JpaRepository<CartITEM , Long> {
    List<CartITEM> findByCartId(Long cartId);
}
