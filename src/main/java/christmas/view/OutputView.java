package christmas.view;

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
}
