package logic;

import data.Card;
import enums.PokerHand;

import java.util.*;

public class CardCollection {
    private ArrayList<Card> cards;
    private PokerHand handType;
    private String cardsEncoded;

    public CardCollection() {
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

    public void addCollection(CardCollection cards) {
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
            suiteList.add(card.getSuite());
        }
        return suiteList;
    }

    public ArrayList<Integer> getValueList() {
        ArrayList<Integer> valueList = new ArrayList<>();
        for (Card card : cards) {
            valueList.add(card.getValue());
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

    public boolean isBetterHand(CardCollection opponentCards) {
        if (getHandType() == opponentCards.getHandType()) {
            return getCardsEncoded().compareToIgnoreCase(opponentCards.getCardsEncoded()) > 0 ;
        }
        return getHandType().getScore() > opponentCards.getHandType().getScore();
    }

    public boolean isDraw(CardCollection opponentCards) {
        return getHandType() == opponentCards.getHandType()
                && getCardsEncoded().equals(opponentCards.getCardsEncoded());
    }

    public void printCards() {
        for (Card card : cards) {
            System.out.println(card.getValueAsString() + " " + card.getSuite());
        }
        System.out.println();
    }
}
