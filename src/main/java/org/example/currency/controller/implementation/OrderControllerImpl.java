package org.example.currency.controller.implementation;

import jakarta.validation.Valid;
import org.example.currency.controller.OrderController;

import org.example.currency.dto.OrderDTO;
import org.example.currency.security.config.CurrencyUserDetails;
import org.example.currency.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderControllerImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    @PostMapping
    public void addOrder(@Valid @RequestBody OrderDTO order,
                         @AuthenticationPrincipal CurrencyUserDetails userDetails) {

        orderService.addOrder(order.toBuilder().userLogin(userDetails.getLogin()).build());
    }

    @Override
    @GetMapping
    public List<OrderDTO> getOrders(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                    @RequestParam(name = "size", defaultValue = "10", required = false) int size,
                                    @AuthenticationPrincipal CurrencyUserDetails userDetails) {

        String userLogin = userDetails.getUsername();

        return orderService.getOrders(userLogin, page, size);
    }
}
