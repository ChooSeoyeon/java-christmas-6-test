package christmas.model.event.enums;

import christmas.model.order.enums.Menu;

public enum GiftEvent {
    CHAMPAGNE_GIFT("샴페인", 1, 120000),
    NONE("없음", 0, 0);

    private final String giftMenu;
    private final int quantity;
    private final int threshold;

    GiftEvent(String giftMenu, int quantity, int threshold) {
        this.giftMenu = giftMenu;
        this.quantity = quantity;
        this.threshold = threshold;
    }

    public String getGiftMenu() {
        return giftMenu;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isEligible(int totalAmount) {
        return totalAmount >= threshold;
    }

    public static GiftEvent findEligibleEvent(int totalAmount) {
        for (GiftEvent event : GiftEvent.values()) {
            if (event.isEligible(totalAmount)) {
                return event;
            }
        }
        return NONE;
    }

    public int getGiftPrice() {
        if (this == NONE) {
            return 0;
        }
        return Menu.findMenuByName(giftMenu).getPrice() * quantity;
    }

    public String getName() {
        return giftMenu;
    }
}
