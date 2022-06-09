package data;

import java.util.*;

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

    public void addCard(Card card) {
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

    public int getHandScore() {
        return this.handScore;
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
        for (int value = min + 1; value < max; value++) {
            removeValueEqualTo(value);
        }
    }

    public void removeLowCards() {
        sortCollection();
        while (this.cards.size() > 5) {
            this.cards.remove(0);
        }
    }

    public void removeLowCardsNotIncludingPairs() {
        sortCollection();
        Map<Integer, Integer> valueFrequency = getValueFrequencyMap();

        // Remove low non-pair
        for ( int i = 0; i < cards.size(); i++ ) {
            if ( !valueFrequency.containsKey(cards.get(i).value) && cards.size() > 5) {
                this.cards.remove(cards.get(i));
                i--;
            }
        }

        // if more than 5 cards, remove low pair
        if (cards.size() > 5) {
            for (int i = 0; i < cards.size(); i++) {
                if (valueFrequency.get(cards.get(i).value) == 2 && cards.size() > 5) {
                    this.cards.remove(cards.get(i));
                    i--;
                }
            }
        }

        // if more than 5 cards, remove low 3 of a kind
        if (cards.size() > 5) {
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

    public void printCards() {
        for (Card card : cards) {
            System.out.println(card.valueAsString + " " + card.suite);
        }
        System.out.println();
    }
}
