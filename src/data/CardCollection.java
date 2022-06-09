package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

public class CardCollection {
    private ArrayList<Card> cards;
    private int handScore;

    public CardCollection() {
        this.cards = new ArrayList<>();
        this.handScore = -1;
    }

    public CardCollection(ArrayList<Card> cardList) {
        this.cards = new ArrayList<>(cardList);
        this.handScore = -1;
    }

    public void addCardToCollection(Card card) {
        this.cards.add(card);
    }

    public void addListToCollection(ArrayList<Card> cards) {
        this.cards.addAll(cards);
    }

    public void addCollection(CardCollection cards) {
        this.cards.addAll(cards.getCards());
    }

    public void sortCollection() {
        Collections.sort(this.cards);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Convert list of Card objects to list of Card.suite objects
     * */
    public ArrayList<String> getSuiteList() {
        ArrayList<String> suiteList = new ArrayList<>();
        for (Card card : cards) {
            suiteList.add(card.suite);
        }
        return suiteList;
    }

    /**
     * Convert list of Card objects to list of Card.valueAsString objects
     * */
    public ArrayList<String> getValueStringList() {
        ArrayList<String> valueStringList = new ArrayList<>();
        for (Card card : cards) {
            valueStringList.add(card.valueAsString);
        }
        return valueStringList;
    }

    /**
     * Convert list of Card objects to list of Card.value objects
     * */
    public ArrayList<Integer> getValueList() {
        ArrayList<Integer> valueList = new ArrayList<>();
        for (Card card : cards) {
            valueList.add(card.value);
        }
        return valueList;
    }

    public void removeSuitesNotEqualTo(String suite) {
        this.cards.removeIf(card -> !Objects.equals(card.suite, suite));
    }

    private void removeValueEqualTo(int value) {
        this.cards.removeIf(card -> Objects.equals(card.value, value));
    }

    public void removeValueInRange(int min, int max) {
        for (int i = min + 1; i < max; i++) {
            removeValueEqualTo(i);
        }
    }

    public void removeLowCards() {
        sortCollection();
        while (this.cards.size() > 5) {
            this.cards.remove(0);
        }
    }

    // 0 1 2
    public void removeDuplicateValueCards() {
        for (int i = cards.size() - 2; i >= 0; i--) {
            if ( cards.get(i).value == cards.get(i+1).value ) {
                cards.remove(i+1);
            }
        }
    }

    public void printCards() {
        for (Card card : cards) {
            System.out.println(card.valueAsString + " " + card.suite);
        }
        System.out.println();
    }
}
