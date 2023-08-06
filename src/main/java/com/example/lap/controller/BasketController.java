package com.example.lap.controller;

import com.example.lap.dao.*;
import com.example.lap.dto.ProductDTO;
import com.example.lap.dto.ResponseDTO;
import com.example.lap.dto.SimpleProductDTO;
import com.example.lap.dto.StatusCode;
import com.example.lap.security.CustomUserDetails;
import com.example.lap.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Api endpoint for basket interaction
 */
@Controller
@RequestMapping(path="/api/basket")
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

    @PostMapping(path="/add")
    public @ResponseBody ResponseDTO addProduct(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody SimpleProductDTO simpleProductDTO) {
        WebUser user = webUserRepository.findUserByEmail(userDetails.getUsername());
        Product product = productRepository.findProductById(simpleProductDTO.getId());

        if (product == null) {
            return new ResponseDTO("Product does not exist", StatusCode.ERROR);
        }

        Basket basket = user.getBasket();
        List<BasketProduct> basketProducts = basket.getBasketProducts();

        boolean exists = false;
        //increase product amount if already in basket
        for (BasketProduct basketProduct: basketProducts) {
            if (basketProduct.getProduct().equals(product)) {
                int currentAmount = basketProduct.getAmount();
                basketProduct.setAmount(currentAmount + simpleProductDTO.getAmount());
                exists = true;
                break;
            }
        }

        //add product if not already exists
        if (!exists) {
            BasketProduct basketProduct = new BasketProduct(product, basket, simpleProductDTO.getAmount());
            basketProductRepository.save(basketProduct);
            basketProducts.add(basketProduct);
            product.getBasketProducts().add(basketProduct);
        }

        basketRepository.saveAndFlush(basket);
        return new ResponseDTO("Successfully added product", StatusCode.OK);
    }

    @GetMapping(path="/remove/{id}")
    public @ResponseBody ResponseDTO removeProduct(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id) {
        WebUser user = webUserRepository.findUserByEmail(userDetails.getUsername());
        Product product = productRepository.findProductById(id);

        if (product == null) {
            return new ResponseDTO("Product does not exist", StatusCode.ERROR);
        }

        Basket basket = user.getBasket();
        List<BasketProduct> basketProducts = basket.getBasketProducts();

        for (BasketProduct basketProduct: basketProducts) {
            if (basketProduct.getProduct().equals(product)) {
                basketProduct.getProduct().getBasketProducts().remove(basketProduct);
                basketProducts.remove(basketProduct);
                basketProductRepository.delete(basketProduct);
                basketProductRepository.flush();
                break;
            }
        }

        return new ResponseDTO("Successfully removed product", StatusCode.OK);
    }

    @GetMapping(path="/list")
    public @ResponseBody List<ProductDTO> getProducts(@AuthenticationPrincipal CustomUserDetails userDetails) {
        WebUser user = webUserRepository.findUserByEmail(userDetails.getUsername());
        Basket basket = user.getBasket();
        List<BasketProduct> basketProducts = basket.getBasketProducts();

        List<ProductDTO> products = new ArrayList<>();

        for (BasketProduct basketProduct: basketProducts) {
            ProductDTO product = productService.mapProductToProductDTO(basketProduct.getProduct());
            product.setAmount(basketProduct.getAmount());
            products.add(product);
        }

        return products;
    }
}