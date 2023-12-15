package christmas.model.event.enums;

import christmas.model.event.EventCalendar;
import christmas.model.order.Orders;
import christmas.model.order.enums.MenuType;
import java.util.ArrayList;
import java.util.List;

public enum DiscountEvent {
    CHRISTMAS_DAY("크리스마스 디데이 할인") {
        @Override
        public int calculateDiscount(Orders orders, EventCalendar eventCalendar) {
            if (eventCalendar.isBeforeChristmasDay()) {
                return 1000 + (eventCalendar.getVisitedDate().getDayOfMonth() - 1) * 100;
            }
            return 0;
        }
    },
    WEEKDAY_DISCOUNT("평일 할인") {
        @Override
        public int calculateDiscount(Orders orders, EventCalendar eventCalendar) {
            if (eventCalendar.isWeekday()) {
                return 1000 * orders.findCountBy(MenuType.DESSERT);
            }
            return 0;
        }
    },
    WEEKEND_DISCOUNT("주말 할인") {
        @Override
        public int calculateDiscount(Orders orders, EventCalendar eventCalendar) {
            if (eventCalendar.isWeekend()) {
                return 1000 * orders.findCountBy(MenuType.DESSERT);
            }
            return 0;
        }
    },
    SPECIAL_DISCOUNT("특별 할인") {
        @Override
        public int calculateDiscount(Orders orders, EventCalendar eventCalendar) {
            if (eventCalendar.isSpecialDay()) {
                return 1000;
            }
            return 0;
        }
    };

    private final String eventName;

    public abstract int calculateDiscount(Orders orders, EventCalendar eventCalendar);

    DiscountEvent(String eventName) {
        this.eventName = eventName;
    }

    public static List<DiscountEvent> findEligibleEvents(Orders orders, EventCalendar eventCalendar) {
        List<DiscountEvent> discountEvents = new ArrayList<>();
        for (DiscountEvent discountEvent : DiscountEvent.values()) {
            if (discountEvent.calculateDiscount(orders, eventCalendar) > 0) {
                discountEvents.add(discountEvent);
            }
        }
        return discountEvents;
    }

    public String getDiscountName() {
        return this.eventName;
    }
}