package christmas.model.order.dto;

import java.util.List;

public record OrderFindAllResponse(List<OrderDto> orders, int totalPrice) {
}
