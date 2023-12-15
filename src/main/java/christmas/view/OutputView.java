package christmas.view;

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
            System.out.println(order.menuName() + " | " + order.quantity());
        });
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(formatPrice(response.totalPrice()));
    }

    public String formatPrice(int number) {
        return String.format("%,d", number) + "원";
    }
}
