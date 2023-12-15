package christmas.model.order.enums;

import java.util.ArrayList;
import java.util.List;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6000, MenuType.APPETIZER),
    TAPAS("타파스", 5500, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, MenuType.APPETIZER),
    T_BONE_STEAK("티본스테이크", 55000, MenuType.MAIN),
    BBQ_RIBS("바비큐립", 54000, MenuType.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MenuType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MenuType.MAIN),
    CHOCOLATE_CAKE("초코케이크", 15000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5000, MenuType.DESSERT),
    ZERO_COLA("제로콜라", 3000, MenuType.DRINK),
    RED_WINE("레드와인", 60000, MenuType.DRINK),
    CHAMPAGNE("샴페인", 25000, MenuType.DRINK);

    private final String name;
    private final int price;
    private final MenuType type;

    Menu(String name, int price, MenuType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public static Menu findMenuByName(String menuName) {
        for (Menu menu : Menu.values()) {
            System.out.println("menu = " + menu.getName());
            System.out.println("menuName = " + menuName);
            if (menu.getName().equals(menuName)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public MenuType getType() {
        return type;
    }

    public static boolean exists(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static MenuType findMenuTypeByName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(name)) {
                return menu.getType();
            }
        }
        return null;
    }

    public static List<Menu> findMenusByType(MenuType type) {
        List<Menu> menus = new ArrayList<>();
        for (Menu menu : Menu.values()) {
            if (menu.getType() == type) {
                menus.add(menu);
            }
        }
        return menus;
    }

    public boolean isBeverage() {
        return type == MenuType.DRINK;
    }

    public int calculatePrice(int count) {
        return price * count;
    }

    public boolean isSameMenuType(MenuType menuType) {
        return type == menuType;
    }
}