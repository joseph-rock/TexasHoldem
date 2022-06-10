package data;

import java.util.*;

public class CardCollection {
    private ArrayList<Card> cards;
    private int handScore;
    private int handTieBreaker;

    public CardCollection() {
        this.cards = new ArrayList<>();
        this.handScore = -1;
        this.handTieBreaker = -1;
    }

    public CardCollection(ArrayList<Card> cardList) {
        this.cards = new ArrayList<>(cardList);
        this.handScore = -1;
        this.handTieBreaker = -1;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addListToCollection(ArrayList<Card> cards) {
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

    public int getHandScore() {
        return this.handScore;
    }

    public int getHandTieBreaker() {
        return this.handTieBreaker;
    }

    public void setHandScore(int score) {
        this.handScore = score;
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
     * Convert list of Card objects to list of Card.value objects
     * */
    public ArrayList<Integer> getValueList() {
        ArrayList<Integer> valueList = new ArrayList<>();
        for (Card card : cards) {
            valueList.add(card.value);
        }
        return valueList;
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

    public Map<Integer, Integer> getValueFrequencyMap() {
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

    public void removeSuitesExcept(String suite) {
        this.cards.removeIf(card -> !Objects.equals(card.suite, suite));
    }

    private void removeValueEqualTo(int value) {
        this.cards.removeIf(card -> Objects.equals(card.value, value));
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

    public void getBestHand() {
        cards.sort(Collections.reverseOrder());

        ArrayList<Card> bestHand = new ArrayList<>();
        Map<Integer, Integer> valueFrequency = getValueFrequencyMap();

        // Creates best hand with 4 of a kind
        if (valueFrequency.containsValue(4)) {
            for ( Card card : cards ) {
                if ( valueFrequency.getOrDefault(card.value, 0) == 4 ) {
                    bestHand.add(card);
                }
            }

            if (cards.get(0).value == bestHand.get(0).value) {
                bestHand.add(cards.get(4));
            }
            else {
                bestHand.add(cards.get(0));
            }
        }

        // Build best hand with three of a kind present
        else if ( valueFrequency.containsValue(3) ) {

            // get highest three of a kind
            for ( Card card : cards ) {
                if ( valueFrequency.getOrDefault(card.value, 0) == 3 && bestHand.size() < 3 ) {
                    bestHand.add(card);
                }
            }

            // get highest pair or three of a kind converted to pair
            for ( Card card : cards ) {
                if ( valueFrequency.containsKey(card.value) && card.value != bestHand.get(0).value
                        && bestHand.size() < 5 ) {
                    bestHand.add(card);
                }
            }

            // fill in with highest cards
            if (bestHand.size() < 5) {
                for (Card card : cards) {
                    if (bestHand.size() < 5 && !bestHand.contains(card)) {
                        bestHand.add(card);
                    }
                }
            }
        }

        // Build best hand with only pairs present
        else if ( valueFrequency.containsValue(2) ) {
            for ( Card card : cards ) {
                if ( valueFrequency.getOrDefault(card.value, 0) == 2 && bestHand.size() < 4 ) {
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
            if ( cards.get(i).value == cards.get(i+1).value ) {
                cards.remove(i+1);
            }
        }
    }

    public void encodeTieBreakerNoPairs() {
        ArrayList<Integer> valueList = getValueList();
        valueList.sort(Collections.reverseOrder());

        int total = 0;
        for (int value : valueList) {
            total = total * 10 + value;
        }

        // Actually disgusting
        if (total == 145432) {
            total = 5432;
        }

        this.handTieBreaker = total;
    }

    public void encodeTieBreakerWithPairs() {
        ArrayList<Integer> valueList = getValueList();
        valueList.sort(Collections.reverseOrder());

        Map<Integer, Integer> valueFrequency = getValueFrequencyMap();

        int total = 0;

        for (int value : valueList) {
            if ( valueFrequency.getOrDefault(value, 0) == 4 ) {
                total = total * 10 + value;
            }
        }

        for (int value : valueList) {
            if ( valueFrequency.getOrDefault(value, 0) == 3 ) {
                total = total * 10 + value;
            }
        }

        for (int value : valueList) {
            if ( valueFrequency.getOrDefault(value, 0) == 2 ) {
                total = total * 10 + value;
            }
        }

        for (int value : valueList) {
            if ( !valueFrequency.containsKey(value) ) {
                total = total * 10 + value;
            }
        }

        this.handTieBreaker = total;
    }

    public void printCards() {
        for (Card card : cards) {
            System.out.println(card.valueAsString + " " + card.suite);
        }
        System.out.println();
    }
}
