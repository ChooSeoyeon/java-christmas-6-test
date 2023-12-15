package christmas.model.event.dto;

import christmas.model.event.enums.BadgeEvent;
import christmas.model.event.enums.DiscountEvent;
import christmas.model.event.enums.GiftEvent;
import java.util.List;

public record EventResponse(GiftEvent gift, List<DiscountEvent> discounts, BadgeEvent badge,
                            int totalBenefitPrice, int totalPaymentPrice) {
}
