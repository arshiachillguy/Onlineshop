package com.example.demo.CART;

import com.example.demo.Users.User;
import com.example.demo.cartitem.CartITEM;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @OneToMany(mappedBy = "cart" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<CartITEM> items = new ArrayList<>();

    @ManyToOne
    private User user;

    private boolean isCompleted = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartITEM> getItems() {
        return items;
    }

    public void setItems(List<CartITEM> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
