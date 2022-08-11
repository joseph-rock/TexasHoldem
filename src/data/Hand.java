package data;

import enums.PokerHand;

import java.util.*;

public class Hand implements Comparable<Hand>{
    private ArrayList<Card> cards;
    private PokerHand pokerHand;
    private String encodedCards;

    public Hand() {
        this.cards = new ArrayList<>();
        this.pokerHand = PokerHand.INIT;
        this.encodedCards = "";
    }

    public void sortCards() {
        Collections.sort(this.cards);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addCardList(ArrayList<Card> cards) {
        this.cards.addAll(cards);
    }

    public void setCardList(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void setPokerHand(PokerHand pokerHand) {
        this.pokerHand = pokerHand;
    }

    public void setEncodedCards(String encodedCards) {
        this.encodedCards = encodedCards;
    }

    public PokerHand getPokerHand() {
        return this.pokerHand;
    }

    public String getEncodedCards() {
        return this.encodedCards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public ArrayList<String> getSuiteList() {
        ArrayList<String> suiteList = new ArrayList<>();
        for (Card card : cards) {
            suiteList.add(card.getSuiteString());
        }
        return suiteList;
    }

    public ArrayList<Integer> getValueList() {
        ArrayList<Integer> valueList = new ArrayList<>();
        for (Card card : cards) {
            valueList.add(card.getValueInt());
        }
        return valueList;
    }

    public Map<Integer, Integer> getCardValueFrequency() {
        HashMap<Integer, Integer> valueFrequencyMap = new HashMap<>();
        ArrayList<Integer> valueList = getValueList();

        for (int value : valueList) {
            int frequency = Collections.frequency(valueList, value);
            if (frequency != 1) {
                valueFrequencyMap.put(value, frequency);
            }
        }
        return valueFrequencyMap;
    }

    @Override
    public int compareTo(Hand o) {
        int c = Integer.compare(this.getPokerHand().getScore(), o.getPokerHand().getScore());
        if (c == 0) {
            return this.getEncodedCards().compareTo(o.getEncodedCards());
        }
        return c;
    }
}
