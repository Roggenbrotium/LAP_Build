package com.example.lap.controller;

import com.example.lap.dao.*;
import com.example.lap.dto.ProductDTO;
import com.example.lap.security.CustomUserDetails;
import com.example.lap.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Api endpoint for basket interaction
 */
@Controller
@RequestMapping(path="/basket")
public class BasketController {
    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    private BasketProductRepository basketProductRepository;

    @Autowired
    private ProductService productService;

    @PostMapping(path="/get")
    public @ResponseBody Basket getBasketById(@RequestParam Long id) {
        // This returns a JSON or XML with the users
        return basketRepository.findBasketById(id);
    }

    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity<?> addProduct(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam Long id, int amount) {
        WebUser user = webUserRepository.findUserByEmail(userDetails.getUsername());
        Product product = productRepository.findProductById(id);

        if (product == null) {
            return new ResponseEntity<>("Product does not exist", HttpStatus.OK);
        }

        Basket basket = user.getBasket();
        Set<BasketProduct> basketProducts = basket.getBasketProducts();

        boolean exists = false;
        //increase product amount if already in basket
        for (BasketProduct basketProduct: basketProducts) {
            if (basketProduct.getProduct().equals(product)) {
                int currentAmount = basketProduct.getAmount();
                basketProduct.setAmount(currentAmount + amount);
                exists = true;
                break;
            }
        }

        //add product if not already exists
        if (!exists) {
            BasketProduct basketProduct = new BasketProduct(product, basket, amount);
            basketProductRepository.save(basketProduct);
            basketProducts.add(basketProduct);
            product.getBasketProducts().add(basketProduct);
        }

        basketRepository.saveAndFlush(basket);
        return new ResponseEntity<>("Successfully added product", HttpStatus.OK);
    }

    @PostMapping(path="/remove")
    public @ResponseBody ResponseEntity<?> removeProduct(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam Long id) {
        WebUser user = webUserRepository.findUserByEmail(userDetails.getUsername());
        Product product = productRepository.findProductById(id);

        if (product == null) {
            return new ResponseEntity<>("Product does not exist", HttpStatus.OK);
        }

        Basket basket = user.getBasket();
        Set<BasketProduct> basketProducts = basket.getBasketProducts();

        for (BasketProduct basketProduct: basketProducts) {
            if (basketProduct.getProduct().equals(product)) {
                basketProduct.getProduct().getBasketProducts().remove(basketProduct);
                basketProducts.remove(basketProduct);
                basketProductRepository.delete(basketProduct);
                basketProductRepository.flush();
                break;
            }
        }

        return new ResponseEntity<>("Successfully removed product", HttpStatus.OK);
    }

    @GetMapping(path="/list")
    public @ResponseBody Set<ProductDTO> getProducts(@AuthenticationPrincipal CustomUserDetails userDetails) {
        WebUser user = webUserRepository.findUserByEmail(userDetails.getUsername());
        Basket basket = user.getBasket();
        Set<BasketProduct> basketProducts = basket.getBasketProducts();

        Set<ProductDTO> products = new HashSet<>();

        for (BasketProduct basketProduct: basketProducts) {
            ProductDTO product = productService.mapProductToProductDTO(basketProduct.getProduct());
            products.add(product);
        }

        return products;
    }
}