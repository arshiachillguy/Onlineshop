package com.example.demo.orderRepository;

import com.example.demo.ShopOrder.shoporder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface repositoryorder extends JpaRepository<shoporder, Long > {
}
