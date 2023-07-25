package com.example.lap.controller;

import com.example.lap.dao.Product;
import com.example.lap.dao.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Api endpoint for product interaction
 */
@Controller
@RequestMapping(path="/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewProduct (@RequestParam String name) {
        Product product = new Product();
        product.setName(name);
        productRepository.saveAndFlush(product);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Product> listAllProducts() {
        // This returns a JSON or XML with the products
        return productRepository.findAll();
    }

    @PostMapping(path="/get")
    public @ResponseBody Product getProductById(@RequestParam Long id) {
        // This returns a JSON or XML with the users
        return productRepository.findProductById(id);
    }
}