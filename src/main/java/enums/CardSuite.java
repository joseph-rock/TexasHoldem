package enums;

public enum CardSuite {
    DIAMONDS("diamonds"),
    HEARTS  ("hearts"),
    SPADES  ("spades"),
    CLUBS   ("clubs");

    private final String displayString;

    CardSuite(String displayString) {
        this.displayString = displayString;
    }

    @Override
    public String toString() {
        return displayString;
    }
}
