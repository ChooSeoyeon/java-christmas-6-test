package christmas.controller;

import christmas.model.order.dto.OrderDto;
import christmas.model.order.dto.OrderFindAllResponse;
import christmas.model.order.dto.OrderRequest;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
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
        start();
        order();
        event();
    }

    private void start() { // TODO: 캘린더 반환
        outputView.printStartMessage();
        int date = repeatUntilSuccessWithReturn(this::readDate);
    }

    private void order() { // TODO: order 반환
        List<OrderRequest> orderRequests = repeatUntilSuccessWithReturn(this::readOrder);
        outputView.printEventStartMessageWith(LocalDate.now());
        outputView.printOrderResponse(new OrderFindAllResponse
                (List.of(new OrderDto("후라이드", 1)),
                        1000));
    }

    private void event() {
        outputView.printEventResponse(null);
    }

    private List<OrderRequest> readOrder() {
        return inputView.readOrder();
    }

    private int readDate() {
        return inputView.readDate();
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
