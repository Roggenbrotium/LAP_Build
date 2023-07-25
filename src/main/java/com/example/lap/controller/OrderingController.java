package com.example.lap.controller;

import com.example.lap.dao.Basket;
import com.example.lap.dao.BasketRepository;
import com.example.lap.dao.Ordering;
import com.example.lap.dao.OrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Api endpoint for ordering interaction
 */
@Controller
@RequestMapping(path="/ordering")
public class OrderingController {
    @Autowired
    private OrderingRepository orderingRepository;

    @PostMapping(path="/get")
    public @ResponseBody Ordering getOrderingById(@RequestParam Long id) {
        // This returns a JSON or XML with the users
        return orderingRepository.findOrderingById(id);
    }
}