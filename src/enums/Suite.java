package enums;

public enum Suite {
    DIAMONDS("diamonds"),
    HEARTS("hearts"),
    SPADES("spades"),
    CLUBS("clubs");

    private final String displayString;

    Suite(String displayString) {
        this.displayString = displayString;
    }

    @Override
    public String toString() {
        return displayString;
    }
}
