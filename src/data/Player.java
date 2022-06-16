package data;

import logic.CardCollection;
import logic.RandomName;

public class Player {

    private String name;
    private int chipCount;
    private CardCollection hand;

    public Player() {
        this.name = RandomName.getName();
        this.chipCount = 500;
        this.hand  = new CardCollection();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public void resetHand() {
        this.hand = new CardCollection();
    }
    
    public CardCollection getHand() {
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
