package christmas.model.event;

import christmas.model.event.dto.EventResponse;
import christmas.model.event.enums.BadgeEvent;
import christmas.model.event.enums.DiscountEvent;
import christmas.model.event.enums.GiftEvent;
import christmas.model.order.Orders;
import java.util.List;

public class EventPlanner {
    public EventResponse applyTo(Orders orders, EventCalendar eventCalendar) {
        int totalPrice = orders.calculateTotalPrice(); // 총 주문 금액

        List<DiscountEvent> discountEvents = DiscountEvent.findEligibleEvents(orders, eventCalendar);
        GiftEvent giftEvent = GiftEvent.findEligibleEvent(totalPrice);

        int totalDiscountPrice = discountEvents.stream()
                .mapToInt(discountEvent -> discountEvent.calculateDiscount(orders, eventCalendar))
                .sum(); // 총 할인 금액
        int totalBenefitPrice = totalDiscountPrice + giftEvent.getGiftPrice(); // (출력) 총혜택 금액
        BadgeEvent badgeEvent = BadgeEvent.findEligibleBadge(totalBenefitPrice);

        int totalPaymentPrice = totalPrice - totalDiscountPrice;  // (출력) 예상 결제 금액 = 총주문 금액 - 할인 금액
        return new EventResponse(giftEvent, discountEvents, badgeEvent, totalBenefitPrice, totalPaymentPrice);
    }
}
