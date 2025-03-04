package org.example.currency.service.implementation;

import org.example.currency.dto.OrderDTO;
import org.example.currency.model.Order;
import org.example.currency.repository.OrderRepository;
import org.example.currency.service.FixerService;
import org.example.currency.service.OrderService;
import org.example.currency.service.ProductService;
import org.example.currency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;
    private final FixerService fixerService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ProductService productService, FixerService fixerService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
        this.fixerService = fixerService;
    }

    @Override
    public void addOrder(OrderDTO orderDTO) {
        Order order = OrderDTO.toEntity(orderDTO);
        order.setUser(userService.findUserByLogin(orderDTO.getUserLogin()));
        order.setProduct(productService.getProductByName(orderDTO.getProductName()));

        orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> getOrders(String userLogin, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Order> orders = orderRepository.findByUser(userService.findUserByLogin(userLogin), pageable);

        return formOrderDtoList(orders);
    }

    private List<OrderDTO> formOrderDtoList(List<Order> orders) {
        List<OrderDTO> orderDtoList = new ArrayList<>();
        for (Order order : orders) {
            OrderDTO orderDTO = OrderDTO.toDTO(order);
            orderDTO = setCurrencyDetails(order, orderDTO);
            orderDtoList.add(orderDTO);
        }

        return orderDtoList;
    }

    private OrderDTO setCurrencyDetails(Order order, OrderDTO orderDTO) {
        String currency = order.getProduct().getCurrency().getCode();

        OrderDTO.OrderDTOBuilder builder = orderDTO.toBuilder().currency(currency);

        setPriceInEuroIfCodeDifferentFromEuro(builder, currency, order.getProduct().getPrice());

        return builder.build();
    }

    private void setPriceInEuroIfCodeDifferentFromEuro(OrderDTO.OrderDTOBuilder builder, String currency, double price) {
        if (!currency.equals("EUR")) {
            Double rate = fixerService.getCurrencyRate(currency);
            if (rate != null) {
                builder.priceInEUR(convertToEUR(price, rate));
            }
        }
    }

    private double convertToEUR(double price, double rate) {
        return price / rate;
    }
}
