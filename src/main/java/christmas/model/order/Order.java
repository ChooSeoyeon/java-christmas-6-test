package christmas.model.order;

import christmas.model.order.dto.OrderRequest;
import christmas.model.order.enums.Menu;
import christmas.model.order.enums.MenuType;

public class Order {
    private final Menu menu;
    private final int count;

    public Order(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
        validate();
    }

    public static Order create(OrderRequest request) {
        return new Order(Menu.findMenuByName(request.menuName()), request.quantity());
    }

    private void validate() {
        if (count < 1) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public int getCount() {
        return count;
    }

    public boolean isBeverage() {
        return menu.isBeverage();
    }

    public int calculatePrice() {
        return menu.calculatePrice(count);
    }

    public String getMenuName() {
        return menu.getName();
    }

    public boolean isSameMenuType(MenuType menuType) {
        return menu.isSameMenuType(menuType);
    }
}
