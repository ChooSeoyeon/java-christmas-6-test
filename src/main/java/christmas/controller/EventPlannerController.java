package christmas.controller;

import christmas.model.event.EventCalendar;
import christmas.model.event.EventPlanner;
import christmas.model.event.dto.EventResponse;
import christmas.model.order.Orders;
import christmas.model.order.dto.OrderFindAllResponse;
import christmas.model.order.dto.OrderRequest;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class EventPlannerController {

    private final InputView inputView;
    private final OutputView outputView;

    public EventPlannerController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        EventCalendar eventCalendar = start();
        Orders orders = repeatUntilSuccessWithReturn(() -> order(eventCalendar));
        event(orders, eventCalendar);
    }

    private EventCalendar start() {
        outputView.printStartMessage();
        return repeatUntilSuccessWithReturn(this::readDate);
    }

    private Orders order(EventCalendar eventCalendar) {
        List<OrderRequest> orderRequests = inputView.readOrder();
        outputView.printEventStartMessageWith(eventCalendar.getVisitedDate());
        Orders orders = new Orders();
        orders.addBy(orderRequests);
        OrderFindAllResponse orderFindAllResponse = orders.findAll();
        outputView.printOrderResponse(orderFindAllResponse);
        return orders;
    }

    private void event(Orders orders, EventCalendar eventCalendar) {
        EventPlanner eventPlanner = new EventPlanner();
        EventResponse eventResponse = eventPlanner.applyTo(orders, eventCalendar);
        outputView.printEventResponse(eventResponse);
    }

    private EventCalendar readDate() {
        int date = inputView.readDate();
        return EventCalendar.of(date);
    }

    private <T> T repeatUntilSuccessWithReturn(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void repeatUntilSuccess(Runnable action) {
        while (true) {
            try {
                action.run();
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
