package enums;

public enum PlayerAction {
    BET  ("Bet:"),
    CALL ("Call:"),
    FOLD ("Fold"),
    CHECK("Check"),
    ALL_IN ("All In"),
    WINNER ("Winner!"),
    BIG_BLIND   ("Big Blind:"),
    SMALL_BLIND ("Small Blind:");

    private final String displayString;

    PlayerAction(String displayString) {
        this.displayString = displayString;
    }

    @Override
    public String toString() {
        return displayString;
    }
}
