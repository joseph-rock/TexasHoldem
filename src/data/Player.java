package data;

import enums.PlayerAction;
import logic.RandomName;

public class Player {

    private String name;
    private int chipCount;
    private Hand hand;
    private int currentBet;

    private boolean folded;
    private boolean eliminated;
    private PlayerAction lastAction;

    public Player() {
        this.name = RandomName.getName();
        this.chipCount = 500;
        this.hand  = new Hand();
        this.folded = false;
        this.eliminated = false;
        this.lastAction = null;
    }

    public Player(String name) {
        this.name = name;
        this.chipCount = 500;
        this.hand  = new Hand();
        this.folded = false;
        this.lastAction = null;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public void newRound() {
        this.hand = new Hand();
        this.folded = false;
        this.lastAction = null;
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

    public boolean hasFolded() {
        return this.folded;
    }

    public void fold() {
        this.folded = true;
    }

    public void setEliminated() {
        this.eliminated = true;
    }

    public void setLastAction(PlayerAction action) {
        this.lastAction = action;
    }

    public PlayerAction getLastAction() {
        return this.lastAction;
    }
}
