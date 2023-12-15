package christmas.model.order.dto;

import christmas.model.order.Order;
import java.util.List;

public record OrderDto(String menuName, int quantity) {
    public static List<OrderDto> create(List<Order> orders) {
        return orders.stream()
                .map(order -> new OrderDto(order.getMenuName(), order.getCount()))
                .toList();
    }
}
