package data;

import enums.PokerHand;

public class RankedPlayer {

    private final int rank;
    private final String name;
    private final PokerHand pokerHand;
    private final String encodedHand;

    public RankedPlayer(int rank, String name, PokerHand pokerHand, String encodedHand) {
        this.rank = rank;
        this.name = name;
        this.pokerHand = pokerHand;
        this.encodedHand = encodedHand;
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public PokerHand getPokerHand() {
        return pokerHand;
    }

    public String getEncodedHand() {
        return encodedHand;
    }
}
