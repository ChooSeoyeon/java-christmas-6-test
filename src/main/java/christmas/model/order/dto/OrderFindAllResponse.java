package christmas.model.order.dto;

import christmas.model.order.Order;
import java.util.List;

public record OrderFindAllResponse(List<OrderDto> orders, int totalPrice) {
    public static OrderFindAllResponse create(List<Order> orders, int totalPrice) {
        return new OrderFindAllResponse(OrderDto.create(orders), totalPrice);
    }
}
