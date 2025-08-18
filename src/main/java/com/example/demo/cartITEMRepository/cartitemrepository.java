package com.example.demo.cartITEMRepository;

import com.example.demo.cartitem.CartITEM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface cartitemrepository extends JpaRepository<CartITEM , Long> {
}
