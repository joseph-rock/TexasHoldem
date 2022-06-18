package enums;

public enum PlayerStatus {
    BET  ("Bet:"),
    CALL ("Call:"),
    FOLD ("Fold"),
    ALL_IN ("All In"),
    WINNER ("Winner!"),
    BIG_BLIND   ("Big Blind:"),
    SMALL_BLIND ("Small Blind:");

    private final String displayString;

    PlayerStatus(String displayString) {
        this.displayString = displayString;
    }

    @Override
    public String toString() {
        return displayString;
    }
}
