package com.example.Security08022025.controller;

import com.example.Security08022025.dto.Product;
import com.example.Security08022025.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductCntroller {

    @Autowired
    private ProductService productService;

    @GetMapping("/welcome")
    public String getWelcomeMessage()
    {
        return "Welcome to Suplex City";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllProducts(HttpServletRequest httpServletRequest)
    {
        System.out.println(httpServletRequest.getHeaderNames());
        return productService.getAllProducts();

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id)
    {
        return productService.getProductById(id);
    }

}
