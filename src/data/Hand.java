package data;

import enums.PokerHand;

import java.util.*;

public class Hand implements Comparable<Hand>{
    private ArrayList<Card> cards;
    private PokerHand handType;
    private String cardsEncoded;

    // TODO: Hand type and encoding should be a single thing
    public Hand() {
        this.cards = new ArrayList<>();
        this.handType = PokerHand.INIT;
        this.cardsEncoded = "";
    }

    public void sortCollection() {
        Collections.sort(this.cards);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addList(ArrayList<Card> cards) {
        this.cards.addAll(cards);
    }

    public void addHand(Hand cards) {
        this.cards.addAll(cards.getCards());
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void setHandType(PokerHand handType) {
        this.handType = handType;
    }

    public void setCardsEncoded(String cardsEncoded) {
        this.cardsEncoded = cardsEncoded;
    }

    public PokerHand getHandType() {
        return this.handType;
    }

    public String getCardsEncoded() {
        return this.cardsEncoded;
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

    public boolean isBetterHand(Hand opponentCards) {
        if (getHandType() == opponentCards.getHandType()) {
            return getCardsEncoded().compareToIgnoreCase(opponentCards.getCardsEncoded()) > 0 ;
        }
        return getHandType().getScore() > opponentCards.getHandType().getScore();
    }

    public boolean isDraw(Hand opponentCards) {
        return getHandType() == opponentCards.getHandType()
                && getCardsEncoded().equals(opponentCards.getCardsEncoded());
    }

    public void printCards() {
        for (Card card : cards) {
            System.out.println(card.getValueInt() + " " + card.getSuiteString());
        }
        System.out.println();
    }

    @Override
    public int compareTo(Hand o) {
        int c = Integer.compare(this.getHandType().getScore(), o.getHandType().getScore());
        if (c == 0) {
            return this.getCardsEncoded().compareTo(o.getCardsEncoded());
        }
        return c;
    }
}
