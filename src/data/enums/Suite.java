package data.enums;

public enum Suite {
    DIAMONDS("diamonds"),
    HEARTS("hearts"),
    SPADES("spades"),
    CLUBS("clubs");

    private String displayString;

    Suite(String displayString) {
        this.displayString = displayString;
    }

    public String displayString() {
        return displayString;
    }

    @Override
    public String toString() {
        return displayString;
    }
}
