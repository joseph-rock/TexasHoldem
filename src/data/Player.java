package data;

import enums.PokerHand;
import logic.BestHand;
import logic.RandomName;

import java.util.ArrayList;

public class Player{

    private String name;
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

    public String getEncodedHand(ArrayList<Card> communityCards) {
        ArrayList<Card> allCards = new ArrayList<>(this.cards);
        allCards.addAll(communityCards);
        return BestHand.encode(allCards);
    }

    public PokerHand getHandType(ArrayList<Card> communityCards) {
        ArrayList<Card> allCards = new ArrayList<>(this.cards);
        allCards.addAll(communityCards);
        return BestHand.getPokerHand(allCards);
    }

    public void newRound() {
        this.cards = new ArrayList<>();
    }

//    @Override
//    public int compareTo(Player o) {
//        return this.getEncodedHand().compareTo(o.getEncodedHand());
//    }
}
