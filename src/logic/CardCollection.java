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

    public CardCollection(ArrayList<Card> cardList) {
        this.cards = new ArrayList<>(cardList);
        this.handType = PokerHand.INIT;
        this.cardsEncoded = "";
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

    public void sortCollection() {
        Collections.sort(this.cards);
    }

    public void setHandType(PokerHand handType) {
        this.handType = handType;
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

    /**
     * Convert list of Card objects to list of Card.suite objects
     * */
    public ArrayList<String> getSuiteList() {
        ArrayList<String> suiteList = new ArrayList<>();
        for (Card card : cards) {
            suiteList.add(card.getSuite());
        }
        return suiteList;
    }

    /**
     * Convert list of Card objects to list of Card.value objects
     * */
    public ArrayList<Integer> getValueList() {
        ArrayList<Integer> valueList = new ArrayList<>();
        for (Card card : cards) {
            valueList.add(card.getValue());
        }
        return valueList;
    }

    public Map<Integer, Integer> mapCardValueFrequency() {
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

    public Map<String, Integer> mapCardSuiteFrequency() {
        HashMap<String, Integer> valueFrequencyMap = new HashMap<>();
        ArrayList<String> suiteList = getSuiteList();

        for (String suite : suiteList) {
            int frequency = Collections.frequency(suiteList, suite);
            if (frequency != 1) {
                valueFrequencyMap.put(suite, frequency);
            }
        }
        return valueFrequencyMap;
    }

    public void removeSuitesExcept(String suite) {
        this.cards.removeIf(card -> !Objects.equals(card.getSuite(), suite));
    }

    private void removeValueEqualTo(int value) {
        this.cards.removeIf(card -> Objects.equals(card.getValue(), value));
    }

    public void removeValueInRange(int min, int max) {
        for (int value = min; value < max; value++) {
            removeValueEqualTo(value);
        }
    }

    private void removeLowCards() {
        sortCollection();
        while (this.cards.size() > 5) {
            this.cards.remove(0);
        }
    }

    // TODO de-clutter and optimize
    public void getBestHand() {
        cards.sort(Collections.reverseOrder());

        ArrayList<Card> bestHand = new ArrayList<>();
        Map<Integer, Integer> cardFrequencyMap = mapCardValueFrequency();

        // Creates best hand with 4 of a kind
        if (cardFrequencyMap.containsValue(4)) {
            for ( Card card : cards ) {
                if ( cardFrequencyMap.getOrDefault(card.getValue(), 0) == 4 ) {
                    bestHand.add(card);
                }
            }

            if (cards.get(0).getValue() == bestHand.get(0).getValue()) {
                bestHand.add(cards.get(4));
            }
            else {
                bestHand.add(cards.get(0));
            }
        }

        // Build best hand with three of a kind present
        else if ( cardFrequencyMap.containsValue(3) ) {

            // get highest three of a kind
            for ( Card card : cards ) {
                if ( bestHand.size() < 3 && cardFrequencyMap.getOrDefault(card.getValue(), 0) == 3 ) {
                    bestHand.add(card);
                }
            }

            // If more pairs exist, choose highest 2 cards
            if ( cardFrequencyMap.size() > 1 ) {
                for (Card card : cards) {
                    if (cardFrequencyMap.containsKey(card.getValue())
                            && card.getValue() != bestHand.get(0).getValue()
                            && bestHand.size() < 5) {
                        bestHand.add(card);
                    }
                }
            }

            // Fill in the rest
            for (Card card : cards) {
                if (bestHand.size() < 5 && !bestHand.contains(card)) {
                    bestHand.add(card);
                }
            }
        }

        // Build best hand with only pairs present
        else if ( cardFrequencyMap.containsValue(2) ) {
            for ( Card card : cards ) {
                if ( cardFrequencyMap.getOrDefault(card.getValue(), 0) == 2 && bestHand.size() < 4 ) {
                    bestHand.add(card);
                }
            }
            for (Card card : cards) {
                if (bestHand.size() < 5 && !bestHand.contains(card)) {
                    bestHand.add(card);
                }
            }
        }

        if (bestHand.size() != 0) {
            setCards(bestHand);
            sortCollection();
        } else {
            // remove dupes or low cards
            sortCollection();
            removeDuplicateValueCards();
            removeLowCards();
        }
    }

    public void removeDuplicateValueCards() {
        sortCollection();
        for (int i = cards.size() - 2; i >= 0; i--) {
            if ( cards.get(i).getValue() == cards.get(i + 1).getValue()) {
                cards.remove(i+1);
            }
        }
    }

    public void encodeHand() {
        ArrayList<Integer> valueList = getValueList();
        valueList.sort(Collections.reverseOrder());

        Map<Integer, Integer> valueFrequency = mapCardValueFrequency();
        StringBuilder encodedHand = new StringBuilder();

        // Encodes pairs
        if (valueFrequency.size() != 0) {
            for (int value : valueList) {
                if (valueFrequency.getOrDefault(value, 0) == 4) {
                    encodedHand.append(Integer.toHexString(value));
                }
            }

            for (int value : valueList) {
                if (valueFrequency.getOrDefault(value, 0) == 3) {
                    encodedHand.append(Integer.toHexString(value));
                }
            }

            for (int value : valueList) {
                if (valueFrequency.getOrDefault(value, 0) == 2) {
                    encodedHand.append(Integer.toHexString(value));
                }
            }

            for (int value : valueList) {
                if (!valueFrequency.containsKey(value)) {
                    encodedHand.append(Integer.toHexString(value));
                }
            }
        }

        // No pairs present
        else {
            for (int value : valueList) {
                encodedHand.append(Integer.toHexString(value));
            }
        }

        this.cardsEncoded = String.valueOf(encodedHand);

        // Checks for A-5 straight, A becomes low card
        if (getCardsEncoded().equals("e5432")) {
            this.cardsEncoded = "5432e";
        }
        if (getCardsEncoded().equals("edcba") && getHandType() == PokerHand.STRAIGHT_FLUSH) {
            this.handType = PokerHand.ROYAL_FLUSH;
        }
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
