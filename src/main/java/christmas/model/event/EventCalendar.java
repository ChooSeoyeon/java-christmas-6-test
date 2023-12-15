package christmas.model.event;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class EventCalendar {
    private static final int[] SPECIAL_DAYS = {3, 10, 17, 24, 25, 31};
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

    public boolean isSpecialDay() {
        for (int specialDay : SPECIAL_DAYS) {
            if (visitedDate.getDayOfMonth() == specialDay) {
                return true;
            }
        }
        return false;
    }

    public boolean isWeekend() {
        return visitedDate.getDayOfWeek() == DayOfWeek.FRIDAY
                || visitedDate.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public boolean isBeforeChristmasDay() {
        return visitedDate.getDayOfMonth() <= 25;
    }
}
