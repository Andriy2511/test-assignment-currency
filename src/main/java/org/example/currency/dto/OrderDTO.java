package org.example.currency.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.example.currency.model.Order;

@Getter
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private Long orderId;

    @NotBlank(message = "The field cannot be void")
    private String productName;

    private String userLogin;

    private String currency;

    private Double price;

    private Double priceInEUR;

    public static OrderDTO toDTO(Order order) {
        return OrderDTO.builder()
                .orderId(order.getId())
                .productName(order.getProduct().getName())
                .userLogin(order.getUser().getLogin())
                .price(order.getProduct().getPrice())
                .build();
    }

    public static Order toEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getOrderId());
        return order;
    }
}
