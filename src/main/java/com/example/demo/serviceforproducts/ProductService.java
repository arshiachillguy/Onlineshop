package com.example.demo.serviceforproducts;
import com.example.demo.products.Product;
import com.example.demo.repositoriesforproducts.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    @Autowired
    public ProductRepository productRepository;

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public Product saveProducts(Product product){

        return productRepository.save(product);
    }

    public Optional<Product> getProductByID(Long id){

        return productRepository.findById(id) ;
    }

    public void deleteProductByID(Long id){
        productRepository.deleteById(id);

    }



}
