package com.example.Security08022025.service;

import com.example.Security08022025.dto.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    List<Product> productList = null;


    @PostConstruct
    public void loadProductsFromDB() {
        productList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> new Product(i, "product " + i , new Random().nextInt(10) ,new Random().nextInt(5000))
                ).collect(Collectors.toList());
    }

    public Product getProductById(int id) {

        return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("product " + id + " not found"));
    }

    public List<Product> getAllProducts() {
        return  productList;
    }
}
