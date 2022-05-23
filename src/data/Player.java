package data;

import logic.RandomName;

import java.util.ArrayList;

public class Player {

    private String name;
    private int chipCount;
    private ArrayList<Card> hand;

    public Player() {
        this.name = RandomName.getName();
        this.chipCount = 500;
        this.hand  = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void resetHand() {
        this.hand = new ArrayList<>();
    }
    
    public ArrayList<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChipCount() {
        return chipCount;
    }

}
