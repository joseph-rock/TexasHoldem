package data;

import logic.RandomName;

public class Player {

    private String name;
    private Hand hand;

    public Player() {
        this.name = RandomName.getName();
        this.hand  = new Hand();
    }

    public Player(String name) {
        this.name = name;
        this.hand  = new Hand();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public void newRound() {
        this.hand = new Hand();
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
