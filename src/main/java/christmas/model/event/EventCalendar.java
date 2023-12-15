package christmas.model.event;

import java.time.LocalDate;

public class EventCalendar {
    private LocalDate visitedDate;

    public EventCalendar(LocalDate visitedDate) {
        this.visitedDate = visitedDate;
    }

    public static EventCalendar of(int date) {
        try {
            LocalDate visitedDate = LocalDate.of(2023, 12, date);
            return new EventCalendar(visitedDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
