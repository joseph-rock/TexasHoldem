package enums;

public enum PokerHand {
    ROYAL_FLUSH(9, "Royal Flush"),
    STRAIGHT_FLUSH(8, "Straight Flush"),
    FOUR_OF_A_KIND(7, "Four of a Kind"),
    FULL_HOUSE(6, "Full House"),
    FLUSH(5, "Flush"),
    STRAIGHT(4, "Straight"),
    THREE_OF_A_KIND(3, "Three of a Kind"),
    TWO_PAIR(2, "Two Pair"),
    PAIR(1, "Pair"),
    HIGH_CARD(0, "High Card"),
    INIT(-1, "");

    private final int score;
    private final String displayString;

    PokerHand(int score, String displayString) {
        this.score = score;
        this.displayString = displayString;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return displayString;
    }
}
