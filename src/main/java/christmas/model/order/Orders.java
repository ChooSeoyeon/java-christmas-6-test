package christmas.model.order;

import christmas.model.order.dto.OrderFindAllResponse;
import christmas.model.order.dto.OrderRequest;
import christmas.model.order.enums.MenuType;
import java.util.ArrayList;
import java.util.List;

public class Orders {
    private final List<Order> orders = new ArrayList<>();

    public void addBy(List<OrderRequest> requests) {
        List<Order> beforeAdd = new ArrayList<>();
        for (OrderRequest request : requests) {
            beforeAdd.add(Order.create(request));
        }
        validate(beforeAdd);

        for (OrderRequest request : requests) {
            orders.add(Order.create(request));
        }
    }

    private void validate(List<Order> orders) {
        if (orders.stream().distinct().count() != orders.size()) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        if (orders.stream().allMatch(Order::isBeverage)) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        if (orders.size() > 20) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public OrderFindAllResponse findAll() {
        return OrderFindAllResponse.create(orders, calculateTotalPrice());
    }

    private int calculateTotalPrice() {
        return orders.stream()
                .mapToInt(Order::calculatePrice)
                .sum();
    }

    public int findCountBy(MenuType menuType) {
        return (int) orders.stream()
                .filter(order -> order.isSameMenuType(menuType))
                .count();
    }
}
