package enums;

public enum PlayerStatus {
    FOLD("Fold"),
    BIG_BLIND("Big Blind:"),
    SMALL_BLIND("Small Blind:"),
    BET("Bet:"),
    CALL("Call:"),
    ALL_IN("All In"),
    WINNER("Winner!");

    private final String displayString;

    PlayerStatus(String displayString) {
        this.displayString = displayString;
    }

    @Override
    public String toString() {
        return displayString;
    }
}
