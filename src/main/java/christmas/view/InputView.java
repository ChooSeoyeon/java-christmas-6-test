package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.order.dto.OrderRequest;
import java.util.ArrayList;
import java.util.List;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String inputDate = Console.readLine();
        return parseInputDateToInt(inputDate);
    }

    private int parseInputDateToInt(String inputDate) {
        try {
            return Integer.parseInt(inputDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public List<OrderRequest> readOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String inputTime = Console.readLine();
        return parseInputOrderToOrderRequestList(inputTime);
    }

    private List<OrderRequest> parseInputOrderToOrderRequestList(String inputOrder) {
        try {
            List<OrderRequest> orderRequestList = new ArrayList<>();
            String[] inputOrderPart = inputOrder.split(",");
            for (String orderPart : inputOrderPart) {
                String[] orderPartDetail = orderPart.split("-");
                String menuName = orderPartDetail[0];
                int quantity = Integer.parseInt(orderPartDetail[1]);
                orderRequestList.add(new OrderRequest(menuName, quantity));
            }
            return orderRequestList;
        } catch (Exception e) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
