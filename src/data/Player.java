package data;

import enums.PokerHand;
import logic.RandomName;

import java.util.ArrayList;

public class Player implements Comparable<Player>{

    private String name;
    private ArrayList<Card> cards;
    private Hand hand;

    public Player() {
        this.name = RandomName.getName();
        this.cards = new ArrayList<>();
        this.hand  = new Hand();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void newRound() {
        this.cards = new ArrayList<>();
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Hand getHand() {
        return hand;
    }

    public PokerHand getHandType() {
        return hand.getPokerHand();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public int compareTo(Player o) {
        return this.getHand().compareTo(o.getHand());
    }
}
