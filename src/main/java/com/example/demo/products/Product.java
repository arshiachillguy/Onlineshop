package com.example.demo.products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @NotBlank(message = "name is required")
    @Column(nullable = false)
    private String name;

    @Size(max = 300)
    private String description;

    @NotNull(message = "price is required")
    @Min(value = 0 , message = "perice must be positive")
    private Integer price;
    private String category;
}


