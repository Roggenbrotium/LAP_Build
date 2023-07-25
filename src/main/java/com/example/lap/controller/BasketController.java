package com.example.lap.controller;

import com.example.lap.dao.Basket;
import com.example.lap.dao.BasketRepository;
import com.example.lap.dao.Product;
import com.example.lap.dao.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Api endpoint for basket interaction
 * Has currently no real use
 */
@Controller
@RequestMapping(path="/basket")
public class BasketController {
    @Autowired
    private BasketRepository basketRepository;

    @PostMapping(path="/get")
    public @ResponseBody Basket getBasketById(@RequestParam Long id) {
        // This returns a JSON or XML with the users
        return basketRepository.findBasketById(id);
    }
}