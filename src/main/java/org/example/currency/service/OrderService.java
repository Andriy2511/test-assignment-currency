package org.example.currency.service;

import org.example.currency.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    void addOrder(OrderDTO order);

    List<OrderDTO> getOrders(String userLogin, int page, int size);
}
