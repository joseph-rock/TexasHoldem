package logic;

import data.Card;
import enums.CardSuite;
import enums.CardValue;
import enums.PokerHand;

import java.util.*;

public class BestHand {

    public static PokerHand getPokerHand(final ArrayList<Card> cards) {

        if(isStraightFlush(cards)) {
            return PokerHand.STRAIGHT_FLUSH;
        } else if (isFourOfAKind(cards)) {
            return PokerHand.FOUR_OF_A_KIND;
        } else if (isFullHouse(cards)) {
            return PokerHand.FULL_HOUSE;
        } else if (isFlush(cards)) {
            return PokerHand.FLUSH;
        } else if (isStraight(cards)) {
            return PokerHand.STRAIGHT;
        } else if (isThreeOfAKind(cards)) {
            return PokerHand.THREE_OF_A_KIND;
        } else if (isTwoPair(cards)) {
            return PokerHand.TWO_PAIR;
        } else if (isPair(cards)) {
            return PokerHand.PAIR;
        }

        return PokerHand.HIGH_CARD;
    }

    public static String encode(final ArrayList<Card> cards) {

        if(isStraightFlush(cards)) {
            return encodeStraightFlush(cards);
        } else if (isFourOfAKind(cards)) {
            return encodeFourOfAKind(cards);
        } else if (isFullHouse(cards)) {
            return encodeFullHouse(cards);
        } else if (isFlush(cards)) {
            return encodeFlush(cards);
        } else if (isStraight(cards)) {
            return encodeStraight(cards);
        } else if (isThreeOfAKind(cards)) {
            return encodeThreeOfAKind(cards);
        } else if (isTwoPair(cards)) {
            return encodeTwoPair(cards);
        } else if (isPair(cards)) {
            return encodePair(cards);
        }

        return encodeHighCard(cards);
    }

    private static Boolean isPair(final ArrayList<Card> cards) {
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        return cvf.size() == 1 && cvf.containsValue(2);
    }

    private static Boolean isTwoPair(final ArrayList<Card> cards) {
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        return cvf.size() > 1
                && cvf.containsValue(2)
                && !cvf.containsValue(3)
                && !cvf.containsValue(4);
    }

    private static Boolean isThreeOfAKind(final ArrayList<Card> cards) {
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        return cvf.size() == 1 && cvf.containsValue(3);
    }

    private static Boolean isFourOfAKind(final ArrayList<Card> cards) {
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        return cvf.size() == 1 && cvf.containsValue(4);
    }

    private static Boolean isFullHouse(final ArrayList<Card> cards) {
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        return cvf.size() > 1 && cvf.containsValue(3) && !cvf.containsValue(4);
    }

    private static Boolean isFlush(final ArrayList<Card> cards) {
        ArrayList<CardSuite> suiteList = getSuiteList(cards);

        for (CardSuite suite : CardSuite.values()) {
            if (Collections.frequency(suiteList, suite) >= 5) {
                return true;
            }
        }
        return false;
    }

    private static Boolean isStraight(final ArrayList<Card> cards) {
        Set<Integer> valueSet = new HashSet<>(getValueList(cards));
        ArrayList<Integer> uniqueValues = new ArrayList<>(valueSet);
        Collections.sort(uniqueValues);

        // Manually check low straight
        if (uniqueValues.contains(CardValue.ACE.toInt()) && uniqueValues.contains(CardValue.TWO.toInt())
                && uniqueValues.contains(CardValue.THREE.toInt()) && uniqueValues.contains(CardValue.FOUR.toInt())
                && uniqueValues.contains(CardValue.FIVE.toInt())) {
            return true;
        }

        for (int i = uniqueValues.size() - 1; i >= 4; i--) {
            if ( uniqueValues.get(i) - uniqueValues.get(i-4) == 4)
            {
                return true;
            }
        }

        return false;
    }

    private static Boolean isStraightFlush(final ArrayList<Card> cards) {
        ArrayList<CardSuite> suiteList = getSuiteList(cards);

        for (CardSuite suite : CardSuite.values()) {
            if (Collections.frequency(suiteList, suite) >= 5) {
                ArrayList<Card> bfc = bestFlushCards(cards);
                return isStraight(bfc);
            }
        }
        return false;
    }

    private static String encodePair(final ArrayList<Card> cards) {
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        cards.sort(Collections.reverseOrder());
        StringBuilder encodedHand = new StringBuilder();

        for ( Card card : cards ) {
            if ( encodedHand.length() < 2
                    && cvf.getOrDefault(card.getIntValue(), 0) == 2 ) {
                encodedHand.append(Integer.toHexString(card.getIntValue()));
            }
        }
        for (Card card : cards) {
            if (encodedHand.length() < 5
                    && !String.valueOf(encodedHand).contains(Integer.toHexString(card.getIntValue()))) {
                encodedHand.append(Integer.toHexString(card.getIntValue()));
            }
        }

        return PokerHand.PAIR.getRank() + String.valueOf(encodedHand);
    }

    private static String encodeTwoPair(final ArrayList<Card> cards) {
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        cards.sort(Collections.reverseOrder());
        StringBuilder encodedHand = new StringBuilder();

        for ( Card card : cards ) {
            if ( encodedHand.length() < 4
                    && cvf.getOrDefault(card.getIntValue(), 0) == 2 ) {
                encodedHand.append(Integer.toHexString(card.getIntValue()));
            }
        }
        for (Card card : cards) {
            if ( encodedHand.length() < 5
                    && !String.valueOf(encodedHand).contains(Integer.toHexString(card.getIntValue()))) {
                encodedHand.append(Integer.toHexString(card.getIntValue()));
            }
        }

        return PokerHand.TWO_PAIR.getRank() + String.valueOf(encodedHand);
    }

    private static String encodeThreeOfAKind(final ArrayList<Card> cards) {
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        cards.sort(Collections.reverseOrder());
        StringBuilder encodedHand = new StringBuilder();

        for ( Card card : cards ) {
            if ( encodedHand.length() < 3
                    && cvf.getOrDefault(card.getIntValue(), 0) == 3 ) {
                encodedHand.append(Integer.toHexString(card.getIntValue()));
            }
        }

        for (Card card : cards) {
            if ( encodedHand.length() < 5
                    && !String.valueOf(encodedHand).contains(Integer.toHexString(card.getIntValue()))) {
                encodedHand.append(Integer.toHexString(card.getIntValue()));
            }
        }

        return PokerHand.THREE_OF_A_KIND.getRank() + String.valueOf(encodedHand);
    }

    private static String encodeFourOfAKind(final ArrayList<Card> cards) {
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        cards.sort(Collections.reverseOrder());
        StringBuilder encodedHand = new StringBuilder();

        for ( Card card : cards ) {
            if ( encodedHand.length() < 4
                    && cvf.getOrDefault(card.getIntValue(), 0) == 4 ) {
                encodedHand.append(Integer.toHexString(card.getIntValue()));
            }
        }

        for (Card card : cards) {
            if ( encodedHand.length() < 5
                    && !String.valueOf(encodedHand).contains(Integer.toHexString(card.getIntValue()))) {
                encodedHand.append(Integer.toHexString(card.getIntValue()));
            }
        }

        return PokerHand.FOUR_OF_A_KIND.getRank() + String.valueOf(encodedHand);
    }

    private static String encodeFullHouse(final ArrayList<Card> cards) {
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        cards.sort(Collections.reverseOrder());
        StringBuilder encodedHand = new StringBuilder();

        for ( Card card : cards ) {
            if ( encodedHand.length() < 3
                    && cvf.getOrDefault(card.getIntValue(), 0) == 3 ) {
                encodedHand.append(Integer.toHexString(card.getIntValue()));
            }
        }

        for (Card card : cards) {
            if ( encodedHand.length() < 5
                    && !String.valueOf(encodedHand.charAt(0)).equals(Integer.toHexString(card.getIntValue()))
                    && cvf.containsKey(card.getIntValue())) {
                encodedHand.append(Integer.toHexString(card.getIntValue()));
            }
        }

        return PokerHand.FULL_HOUSE.getRank() + String.valueOf(encodedHand);
    }

    private static String encodeHighCard(final ArrayList<Card> cards) {
        cards.sort(Collections.reverseOrder());
        StringBuilder encodedHand = new StringBuilder();

        for (Card card : cards) {
            if ( encodedHand.length() < 5 ) {
                encodedHand.append(Integer.toHexString(card.getIntValue()));
            }
        }

        return PokerHand.HIGH_CARD.getRank() + String.valueOf(encodedHand);
    }

    private static String encodeStraight(final ArrayList<Card> cards) {
        ArrayList<Card> bsc = bestStraightCards(cards);

        bsc.sort(Collections.reverseOrder());
        StringBuilder encodedHand = new StringBuilder();

        for (Card card : bsc) {
            if ( encodedHand.length() < 5 ) {
                encodedHand.append(Integer.toHexString(card.getIntValue()));
            }
        }

        String returnEncodedHand = String.valueOf(encodedHand);

        // Checks for A-5 straight, A becomes low card
        if (returnEncodedHand.equals("e5432")) {
            returnEncodedHand = "5432e";
        }

        return PokerHand.STRAIGHT.getRank() + returnEncodedHand;
    }

    private static String encodeFlush(final ArrayList<Card> cards) {
        ArrayList<Card> bfc = bestFlushCards(cards);

        bfc.sort(Collections.reverseOrder());
        StringBuilder encodedHand = new StringBuilder();

        for (Card card : bfc) {
            if ( encodedHand.length() < 5 ) {
                encodedHand.append(Integer.toHexString(card.getIntValue()));
            }
        }

        return PokerHand.FLUSH.getRank() + String.valueOf(encodedHand);
    }

    private static String encodeStraightFlush(final ArrayList<Card> cards) {
        ArrayList<Card> bfc = bestFlushCards(cards);
        ArrayList<Card> straightFlushCards = bestStraightCards(bfc);

        straightFlushCards.sort(Collections.reverseOrder());
        StringBuilder encodedHand = new StringBuilder();

        for (Card card : straightFlushCards) {
            if ( encodedHand.length() < 5 ) {
                encodedHand.append(Integer.toHexString(card.getIntValue()));
            }
        }

        String returnEncodedHand = String.valueOf(encodedHand);

        // Checks for A-5 straight, A becomes low card
        if (returnEncodedHand.equals("e5432")) {
            returnEncodedHand = "5432e";
        }

        return PokerHand.STRAIGHT_FLUSH.getRank() + returnEncodedHand;
    }

    private static ArrayList<Card> bestStraightCards(final ArrayList<Card> cards) {
        ArrayList<Card> bsc = new ArrayList<>(cards);
        Set<Integer> valueSet = new HashSet<>(getValueList(cards));
        ArrayList<Integer> uniqueValues = new ArrayList<>(valueSet);
        Collections.sort(uniqueValues);

        // Manually check low straight
        if (uniqueValues.contains(CardValue.ACE.toInt()) && uniqueValues.contains(CardValue.TWO.toInt())
                && uniqueValues.contains(CardValue.THREE.toInt()) && uniqueValues.contains(CardValue.FOUR.toInt())
                && uniqueValues.contains(CardValue.FIVE.toInt())) {

            removeValuesInRange(bsc, 6, 14);
        } else {
            for (int i = uniqueValues.size() - 1; i >= 4; i--) {
                if (uniqueValues.get(i) - uniqueValues.get(i - 4) == 4) {
                    removeValuesInRange(bsc, uniqueValues.get(i) + 3, 15);
                    removeValuesInRange(bsc, 2, uniqueValues.get(i - 4));
                }
            }
        }

        removeDuplicateValueCards(bsc);

        return bsc;
    }

    private static ArrayList<Card> bestFlushCards(final ArrayList<Card> cards) {
        ArrayList<Card> bestFlushCards = new ArrayList<>(cards);
        ArrayList<CardSuite> suiteList = getSuiteList(cards);

        for (CardSuite suite : CardSuite.values()) {
            if (Collections.frequency(suiteList, suite) >= 5) {
                removeSuitesExcept(suite, bestFlushCards);
            }
        }

        return bestFlushCards;
    }

    private static ArrayList<CardSuite> getSuiteList(final ArrayList<Card> cards) {
        ArrayList<CardSuite> suiteList = new ArrayList<>();
        for (Card card : cards) {
            suiteList.add(card.getSuite());
        }
        return suiteList;
    }

    private static ArrayList<Integer> getValueList(final ArrayList<Card> cards) {
        ArrayList<Integer> valueList = new ArrayList<>();
        for (Card card : cards) {
            valueList.add(card.getIntValue());
        }
        return valueList;
    }

    private static Map<Integer, Integer> getCardValueFrequencyMap(final ArrayList<Card> cards) {
        HashMap<Integer, Integer> valueFrequencyMap = new HashMap<>();
        ArrayList<Integer> valueList = getValueList(cards);

        for (int value : valueList) {
            int frequency = Collections.frequency(valueList, value);
            if (frequency != 1) {
                valueFrequencyMap.put(value, frequency);
            }
        }
        return valueFrequencyMap;
    }

    private static void removeSuitesExcept(CardSuite suite, ArrayList<Card> cards) {
        cards.removeIf(card -> card.getSuite() != suite);
    }

    private static void removeValuesInRange(ArrayList<Card> cards, int min, int max) {
        for (int value = min; value < max; value++) {
            int v = value;
            cards.removeIf(card -> Objects.equals(card.getIntValue(), v));
        }
    }

    private static void removeDuplicateValueCards(ArrayList<Card> cards) {
        for (int i = cards.size() - 2; i >= 0; i--) {
            if ( cards.get(i).getValue() == cards.get(i + 1).getValue()) {
                cards.remove(i+1);
            }
        }
    }
}
