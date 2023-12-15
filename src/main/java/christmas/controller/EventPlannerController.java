package christmas.controller;

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
        start();
        order();
    }

    private void start() { // TODO: 캘린더 반환
        outputView.printStartMessage();
        int date = repeatUntilSuccessWithReturn(this::readDate);
    }

    private List<OrderRequest> order() { // TODO: order 반환
        return repeatUntilSuccessWithReturn(this::readOrder);
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
