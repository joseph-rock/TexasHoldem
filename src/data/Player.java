package data;

import enums.PokerHand;
import logic.BestHand;
import logic.RandomName;

import java.util.ArrayList;

public class Player{

    private final String name;
    private ArrayList<Card> cards;

    public Player() {
        this.name = RandomName.getName();
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public String encodeHand(ArrayList<Card> communityCards) {
        ArrayList<Card> allCards = new ArrayList<>(this.cards);
        allCards.addAll(communityCards);
        return BestHand.encode(allCards);
    }

    public PokerHand handType(ArrayList<Card> communityCards) {
        ArrayList<Card> allCards = new ArrayList<>(this.cards);
        allCards.addAll(communityCards);
        return BestHand.bestPokerHand(allCards);
    }

    public void newRound() {
        this.cards = new ArrayList<>();
    }
}
