package christmas.model.event.enums;

import christmas.model.event.EventCalendar;
import christmas.model.order.Orders;
import christmas.model.order.enums.MenuType;

public enum DiscountEvent {
    CHRISTMAS_DAY("크리스마스 디데이 할인") {
        @Override
        public int calculateDiscount(int day, Orders orders, EventCalendar eventCalendar) {
            if (eventCalendar.isBeforeChristmasDay()) {
                return 1000 + (day - 1) * 100;
            }
            return 0;
        }
    },
    WEEKDAY_DISCOUNT("평일 할인") {
        @Override
        public int calculateDiscount(int day, Orders orders, EventCalendar eventCalendar) {
            if (eventCalendar.isWeekday()) {
                return 1000 * orders.findCountBy(MenuType.DESSERT);
            }
            return 0;
        }
    },
    WEEKEND_DISCOUNT("주말 할인") {
        @Override
        public int calculateDiscount(int day, Orders orders, EventCalendar eventCalendar) {
            if (eventCalendar.isWeekend()) {
                return 1000 * orders.findCountBy(MenuType.DESSERT);
            }
            return 0;
        }
    },
    SPECIAL_DISCOUNT("특별 할인") {
        @Override
        public int calculateDiscount(int day, Orders orders, EventCalendar eventCalendar) {
            if (eventCalendar.isSpecialDay()) {
                return 1000;
            }
            return 0;
        }
    };

    private final String name;

    public abstract int calculateDiscount(int day, Orders orders, EventCalendar eventCalendar);

    DiscountEvent(String name) {
        this.name = name();
    }
}