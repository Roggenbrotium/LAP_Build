package com.example.lap.controller;

import com.example.lap.dao.*;
import com.example.lap.dto.OrderingDTO;
import com.example.lap.security.CustomUserDetails;
import com.example.lap.service.OrderingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Api endpoint for ordering interaction
 */
@Controller
@RequestMapping(path="/api/ordering")
public class OrderingController {
    @Autowired
    private OrderingRepository orderingRepository;

    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    private BasketProductRepository basketProductRepository;

    @Autowired
    private OrderingProductRepository orderingProductRepository;

    @Autowired
    private OrderingService orderingService;

    @PostMapping(path="/get/{id}")
    public @ResponseBody Ordering getOrderingById(@PathVariable Long id) {
        return orderingRepository.findOrderingById(id);
    }

    @PostMapping(path="/start")
    public @ResponseBody ResponseEntity<?> startOrder(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody OrderingDTO orderingDTO) {
        Ordering ordering = orderingService.mapOrderingDTOToOrdering(orderingDTO);

        if (userDetails == null) {
            orderingRepository.save(ordering);
            Set<OrderingProduct> orderingProducts = new HashSet<>();

            for (OrderingProduct orderingProduct: ordering.getOrderingProducts()) {
                orderingProduct.setOrdering(ordering);
                orderingProducts.add(orderingProduct);
            }

            orderingProductRepository.saveAllAndFlush(orderingProducts);
        } else {
            WebUser user = webUserRepository.findUserByEmail(userDetails.getUsername());
            ordering.setWebUser(user);
            orderingRepository.save(ordering);
            Set<BasketProduct> basketProducts = user.getBasket().getBasketProducts();
            Set<OrderingProduct> orderingProducts = new HashSet<>();
            for (BasketProduct basketProduct: basketProducts) {
                OrderingProduct orderingProduct = new OrderingProduct(basketProduct.getProduct(), ordering, basketProduct.getAmount());
                orderingProducts.add(orderingProduct);
            }

            basketProductRepository.deleteAll(user.getBasket().getBasketProducts());
            orderingProductRepository.saveAllAndFlush(orderingProducts);
        }

        return new ResponseEntity<>("Order started successfully!", HttpStatus.OK);
    }
}