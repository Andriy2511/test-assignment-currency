package org.example.currency.controller;

import jakarta.validation.Valid;
import org.example.currency.dto.OrderDTO;
import org.example.currency.security.config.CurrencyUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderController {
    @PostMapping
    void addOrder(@Valid OrderDTO order,
                  @AuthenticationPrincipal CurrencyUserDetails userDetails);

    @GetMapping
    List<OrderDTO> getOrders(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                             @RequestParam(name = "size", defaultValue = "10", required = false) int size,
                             @AuthenticationPrincipal CurrencyUserDetails userDetails);
}
