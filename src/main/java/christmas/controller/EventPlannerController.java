package christmas.controller;

import christmas.model.event.EventCalendar;
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
        order(eventCalendar);
        event();
    }

    private EventCalendar start() {
        outputView.printStartMessage();
        return repeatUntilSuccessWithReturn(this::readDate);
    }

    private void order(EventCalendar eventCalendar) { // TODO: order 반환
        List<OrderRequest> orderRequests = repeatUntilSuccessWithReturn(this::readOrder);
        outputView.printEventStartMessageWith(eventCalendar.getVisitedDate());
        Orders orders = new Orders();
        orders.addBy(orderRequests);
        OrderFindAllResponse orderFindAllResponse = orders.findAll();
        outputView.printOrderResponse(orderFindAllResponse);
    }

    private void event() {
        outputView.printEventResponse(null);
    }

    private List<OrderRequest> readOrder() {
        return inputView.readOrder();
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
