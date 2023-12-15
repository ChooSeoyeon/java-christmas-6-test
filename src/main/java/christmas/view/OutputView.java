package christmas.view;

import christmas.model.event.EventCalendar;
import christmas.model.event.dto.EventResponse;
import christmas.model.event.enums.GiftEvent;
import christmas.model.order.Orders;
import christmas.model.order.dto.OrderFindAllResponse;
import java.time.LocalDate;

public class OutputView {
    public void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    public void printStartMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventStartMessageWith(LocalDate date) {
        System.out.println(formatDate(date) + "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public String formatDate(LocalDate date) {
        return date.getMonthValue() + "월 " + date.getDayOfMonth() + "일";
    }

    public void printOrderResponse(OrderFindAllResponse response) {
        System.out.println("\n<주문 메뉴>");
        response.orders().forEach(order -> {
            System.out.println(order.menuName() + " " + order.quantity() + "개");
        });
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(formatPrice(response.totalPrice()));
    }

    public String formatPrice(int number) {
        return String.format("%,d", number) + "원";
    }

    public void printEventResponse(EventResponse eventResponse, Orders orders, EventCalendar eventCalendar) {
        System.out.println("\n<증정 메뉴>");
        System.out.println(formatGift(eventResponse.gift()));
        System.out.println();
        System.out.println("<혜택 내역>"); // TODO: dto 세부 분리 필요
        System.out.println(formatBenefit(eventResponse, orders, eventCalendar));
        System.out.println();
        System.out.println("<총혜택 금액>");
        System.out.println(formatTotalBenefitPrice(eventResponse));
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(formatPrice(eventResponse.totalPaymentPrice()));
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
        System.out.println(formatBadge(eventResponse));
    }

    public String formatTotalBenefitPrice(EventResponse eventResponse) {
        int totalBenefitPrice = eventResponse.totalBenefitPrice();
        if (totalBenefitPrice != 0) {
            totalBenefitPrice *= (-1);
        }
        return formatPrice(totalBenefitPrice);
    }

    public String formatBenefit(EventResponse eventResponse, Orders orders, EventCalendar eventCalendar) {
        return eventResponse.discounts().stream()
                .map(discountEvent -> discountEvent.getDiscountName() + ": -" + formatPrice(
                        discountEvent.calculateDiscount(orders, eventCalendar)))
                .reduce((s1, s2) -> s1 + "\n" + s2)
                .orElse("없음") + formatGiftInList(eventResponse.gift());
    }

    public String formatBadge(EventResponse eventResponse) {
        return eventResponse.badge().getName();
    }

    public String formatGift(GiftEvent gift) {
        if (gift == GiftEvent.NONE) {
            return "없음";
        }
        return gift.getGiftMenu() + " " + gift.getQuantity() + "개";
    }

    public String formatGiftInList(GiftEvent gift) {
        if (gift == GiftEvent.NONE) {
            return "";
        }
        return "\n증정 이벤트: " + formatPrice(gift.getGiftPrice());
    }
}
