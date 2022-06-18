package data;

import logic.RandomName;

public class Player {

    private String name;
    private int chipCount;
    private Hand hand;

    public Player() {
        this.name = RandomName.getName();
        this.chipCount = 500;
        this.hand  = new Hand();
    }

    public Player(String name) {
        this.name = name;
        this.chipCount = 500;
        this.hand  = new Hand();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public void resetHand() {
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

    public void addChips(int num) {
        this.chipCount += num;
    }

    public void removeChips(int num) {
        this.chipCount -= num;
    }

    public int getChipCount() {
        return chipCount;
    }

}
