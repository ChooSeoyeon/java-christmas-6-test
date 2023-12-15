package christmas.model.order.dto;

import christmas.model.order.Order;
import java.util.List;

public record OrderFindAllResponse(List<OrderDto> orders, int totalPrice) {
    public static List<OrderFindAllResponse> create(List<Order> orders, int totalPrice) {
        return List.of(new OrderFindAllResponse(OrderDto.create(orders), totalPrice));
    }
}
