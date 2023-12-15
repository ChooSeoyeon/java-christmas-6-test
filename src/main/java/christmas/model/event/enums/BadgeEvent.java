package christmas.model.event.enums;

public enum BadgeEvent {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NONE("없음", 0);

    private final String name;
    private final int threshold;

    BadgeEvent(String name, int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public String getName() {
        return name;
    }

    public boolean isEligible(int totalBenefit) {
        return totalBenefit >= threshold;
    }

    public static BadgeEvent findEligibleBadge(int totalBenefit) {
        for (BadgeEvent badge : BadgeEvent.values()) {
            if (badge.isEligible(totalBenefit)) {
                return badge;
            }
        }
        return NONE;
    }
}
