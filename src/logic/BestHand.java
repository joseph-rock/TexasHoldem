package logic;

import data.Card;
import data.Hand;
import enums.CardSuite;
import enums.CardValue;
import enums.PokerHand;

import java.util.*;

public class BestHand {

    public static Hand getBestHand(ArrayList<Card> playerCards, ArrayList<Card> communityCards) {
        Hand hand = new Hand();
        hand.addCardList(playerCards);
        hand.addCardList(communityCards);
        setBestHand(hand);
        return hand;
    }

    public static void setBestHand(Hand hand) {
        hand.sortCards();

        checkFlush(hand);
        checkStraight(hand);
        if (hand.getPokerHand() == PokerHand.INIT) {
            checkPair(hand);
            checkHighCard(hand);
        }
        setFiveBestCards(hand);
        encodeHand(hand);
    }

    public static void checkFlush(Hand hand) {
        ArrayList<String> suiteList = hand.getSuiteList();
        Set<String> uniqueSuites = new HashSet<>(suiteList);

        for (String suite : uniqueSuites) {
            if (Collections.frequency(suiteList, suite) >= 5) {
                removeSuitesExcept(suite, hand);
                hand.setPokerHand(PokerHand.FLUSH);
                return;
            }
        }
    }

    public static void checkStraight(Hand hand) {
        Set<Integer> uniqueValues = new HashSet<>(hand.getValueList());

        // Straight has to be 5 unique card values or more
        if (uniqueValues.size() < 5) {
            return;
        }

        // Manually check low straight (A, 2, 3, 4, 5)
        if (uniqueValues.contains(14) && uniqueValues.contains(2) && uniqueValues.contains(3) &&
                uniqueValues.contains(4) && uniqueValues.contains(5)) {
            removeValuesInRange(hand, 6, 14);
            removeDuplicateValueCards(hand);
            setStraightType(hand);
            return;
        }

        ArrayList<Integer> uniqueValueList = new ArrayList<>(uniqueValues);
        for (int i = uniqueValueList.size() - 1; i >= 4; i--) {
            if ( uniqueValueList.get(i) - uniqueValueList.get(i-4) == 4)
            {
                removeValuesInRange(hand, uniqueValueList.get(i) + 1, 15);
                removeValuesInRange(hand, 2, uniqueValueList.get(i-4));
                removeDuplicateValueCards(hand);
                setStraightType(hand);
                return;
            }
        }
    }

    public static void setStraightType(Hand hand) {
        if (hand.getPokerHand() == PokerHand.FLUSH) {
            hand.setPokerHand(PokerHand.STRAIGHT_FLUSH);
        } else {
            hand.setPokerHand(PokerHand.STRAIGHT);
        }
    }

    public static void checkPair(Hand hand) {
        Map<Integer, Integer> countList = hand.getCardValueFrequency();

        if (countList.size() == 0) {
            return;
        }

        if (countList.size() == 1 && countList.containsValue(2)) {
            hand.setPokerHand(PokerHand.PAIR);
            return;
        }

        if (countList.size() == 1 && countList.containsValue(3)) {
            hand.setPokerHand(PokerHand.THREE_OF_A_KIND);
            return;
        }

        if (countList.containsValue(4)) {
            hand.setPokerHand(PokerHand.FOUR_OF_A_KIND);
            return;
        }

        if (countList.containsValue(3)) {
            hand.setPokerHand(PokerHand.FULL_HOUSE);
            return;
        }

        if (countList.containsValue(2)) {
            hand.setPokerHand(PokerHand.TWO_PAIR);
        }
    }

    public static void checkHighCard(Hand hand) {
        if(hand.getPokerHand() == PokerHand.INIT) {
            hand.setPokerHand(PokerHand.HIGH_CARD);
        }
    }

    public static void setFiveBestCards(Hand hand) {
        ArrayList<Card> cards = hand.getCardList();
        cards.sort(Collections.reverseOrder());

        ArrayList<Card> bestHand = new ArrayList<>();
        Map<Integer, Integer> cardFrequencyMap = hand.getCardValueFrequency();

        // Creates best hand with 4 of a kind
        if (cardFrequencyMap.containsValue(4)) {
            for ( Card card : cards ) {
                if ( cardFrequencyMap.getOrDefault(card.getIntValue(), 0) == 4 ) {
                    bestHand.add(card);
                }
            }

            if (cards.get(0).getIntValue() == bestHand.get(0).getIntValue()) {
                bestHand.add(cards.get(4));
            } else {
                bestHand.add(cards.get(0));
            }
        }

        // Build best hand with three of a kind present
        else if ( cardFrequencyMap.containsValue(3) ) {

            // get highest three of a kind
            for ( Card card : cards ) {
                if ( bestHand.size() < 3 && cardFrequencyMap.getOrDefault(card.getIntValue(), 0) == 3 ) {
                    bestHand.add(card);
                }
            }

            // If more pairs exist, choose highest 2 cards
            if ( cardFrequencyMap.size() > 1 ) {
                for (Card card : cards) {
                    if (cardFrequencyMap.containsKey(card.getIntValue())
                            && card.getIntValue() != bestHand.get(0).getIntValue()
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
                if ( cardFrequencyMap.getOrDefault(card.getIntValue(), 0) == 2 && bestHand.size() < 4 ) {
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
            hand.setCardList(bestHand);
            hand.sortCards();
        } else {
            // remove duplicate or low cards
            hand.sortCards();
            removeDuplicateValueCards(hand);
            removeLowCards(hand);
        }
    }

    public static void encodeHand(Hand hand) {
        ArrayList<Integer> valueList = hand.getValueList();
        valueList.sort(Collections.reverseOrder());

        Map<Integer, Integer> valueFrequency = hand.getCardValueFrequency();
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
        } else {
            for (int value : valueList) {
                encodedHand.append(Integer.toHexString(value));
            }
        }

        hand.setEncodedCards(String.valueOf(encodedHand));

        // Checks for A-5 straight, A becomes low card
        if (hand.getEncodedCards().equals("e5432")) {
            hand.setEncodedCards("5432e");
        }
        if (hand.getEncodedCards().equals("edcba") && hand.getPokerHand() == PokerHand.STRAIGHT_FLUSH) {
            hand.setPokerHand(PokerHand.ROYAL_FLUSH);
        }
    }

    public static void removeSuitesExcept(String suite, Hand hand) {
        hand.getCardList().removeIf(card -> !Objects.equals(card.getSuite().toString(), suite));
    }

    public static void removeValuesInRange(Hand hand, int min, int max) {
        for (int value = min; value < max; value++) {
            int v = value;
            hand.getCardList().removeIf(card -> Objects.equals(card.getIntValue(), v));
        }
    }

    public static void removeLowCards(Hand hand) {
        while (hand.getCardList().size() > 5) {
            hand.getCardList().remove(0);
        }
    }

    public static void removeDuplicateValueCards(Hand hand) {
        ArrayList<Card> cards = hand.getCardList();
        for (int i = cards.size() - 2; i >= 0; i--) {
            if ( cards.get(i).getValue() == cards.get(i + 1).getValue()) {
                cards.remove(i+1);
            }
        }
    }

    // 2.0 Best Hand -------------------------------------------------------------------------------

    public static Boolean isPair(ArrayList<Card> cards) {
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        return cvf.size() == 1 && cvf.containsValue(2);
    }

    public static String encodePair(final ArrayList<Card> cards) {
        ArrayList<Card> bestHand = new ArrayList<>();
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        cards.sort(Collections.reverseOrder());

        for ( Card card : cards ) {
            if ( cvf.getOrDefault(card.getIntValue(), 0) == 2 && bestHand.size() < 4 ) {
                bestHand.add(card);
            }
        }
        for (Card card : cards) {
            if (bestHand.size() < 5 && !bestHand.contains(card)) {
                bestHand.add(card);
            }
        }

        return PokerHand.PAIR.getRank() + encodeCards(bestHand);
    }

    public static Boolean isTwoPair(ArrayList<Card> cards) {
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        return cvf.size() > 1 && cvf.containsValue(2) && !cvf.containsValue(4) && !cvf.containsValue(3);
    }

    public static String encodeTwoPair(final ArrayList<Card> cards) {
        ArrayList<Card> bestHand = new ArrayList<>();
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        cards.sort(Collections.reverseOrder());

        for ( Card card : cards ) {
            if ( cvf.getOrDefault(card.getIntValue(), 0) == 2 && bestHand.size() < 4 ) {
                bestHand.add(card);
            }
        }
        for (Card card : cards) {
            if (bestHand.size() < 5 && !bestHand.contains(card)) {
                bestHand.add(card);
            }
        }

        return PokerHand.TWO_PAIR.getRank() + encodeCards(bestHand);
    }

    public static Boolean isThreeOfAKind(ArrayList<Card> cards) {
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        return cvf.size() == 1 && cvf.containsValue(3);
    }

    public static String encodeThreeOfAKind(final ArrayList<Card> cards) {
        ArrayList<Card> bestHand = new ArrayList<>();
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        cards.sort(Collections.reverseOrder());

        for ( Card card : cards ) {
            if ( bestHand.size() < 3 && cvf.getOrDefault(card.getIntValue(), 0) == 3 ) {
                bestHand.add(card);
            }
        }

        for (Card card : cards) {
            if (bestHand.size() < 5 && !bestHand.contains(card)) {
                bestHand.add(card);
            }
        }

        return PokerHand.THREE_OF_A_KIND.getRank() + encodeCards(bestHand);
    }

    public static Boolean isFourOfAKind(ArrayList<Card> cards) {
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        return cvf.size() == 1 && cvf.containsValue(3);
    }

    public static String encodeFourOfAKind(final ArrayList<Card> cards) {
        ArrayList<Card> bestHand = new ArrayList<>();
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        cards.sort(Collections.reverseOrder());

        for ( Card card : cards ) {
            if ( cvf.getOrDefault(card.getIntValue(), 0) == 4 ) {
                bestHand.add(card);
            }
        }

        for (Card card : cards) {
            if (bestHand.size() < 5 && !bestHand.contains(card)) {
                bestHand.add(card);
            }
        }

        return PokerHand.FOUR_OF_A_KIND.getRank() + encodeCards(bestHand);
    }

    public static Boolean isFullHouse(ArrayList<Card> cards) {
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        return cvf.size() > 1 && cvf.containsValue(3) && !cvf.containsValue(4);
    }

    public static String encodeFullHouse(final ArrayList<Card> cards) {
        ArrayList<Card> bestHand = new ArrayList<>();
        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        cards.sort(Collections.reverseOrder());

        for ( Card card : cards ) {
            if ( bestHand.size() < 3 && cvf.getOrDefault(card.getIntValue(), 0) == 3 ) {
                bestHand.add(card);
            }
        }

        if ( cvf.size() > 1 ) {
            for (Card card : cards) {
                if (cvf.containsKey(card.getIntValue())
                        && card.getIntValue() != bestHand.get(0).getIntValue()
                        && bestHand.size() < 5) {
                    bestHand.add(card);
                }
            }
        }

        for (Card card : cards) {
            if (bestHand.size() < 5 && !bestHand.contains(card)) {
                bestHand.add(card);
            }
        }

        return PokerHand.FULL_HOUSE.getRank() + encodeCards(bestHand);
    }

    public static Boolean isFlush(ArrayList<Card> cards) {
        ArrayList<CardSuite> suiteList = getSuiteList(cards);

        for (CardSuite suite : CardSuite.values()) {
            if (Collections.frequency(suiteList, suite.toString()) >= 5) {
                return true;
            }
        }
        return false;
    }

    // TODO Be better
    public static Boolean isStraight(ArrayList<Card> cards) {
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

    public static Boolean isStraightFlush(final ArrayList<Card> cards) {
        ArrayList<Card> cardsCopy = new ArrayList<>(cards);
        ArrayList<CardSuite> suiteList = getSuiteList(cards);

        for (CardSuite suite : CardSuite.values()) {
            if (Collections.frequency(suiteList, suite.toString()) >= 5) {
                removeSuitesExcept(suite, cardsCopy);
                return isStraight(cardsCopy);
            }
        }
        return false;
    }

    public static String encodeCards(ArrayList<Card> cards) {
        ArrayList<Integer> valueList = getValueList(cards);
        valueList.sort(Collections.reverseOrder());

        Map<Integer, Integer> cvf = getCardValueFrequencyMap(cards);
        StringBuilder encodedHand = new StringBuilder();

        // Encodes pairs
        if (cvf.size() != 0) {
            for (int value : valueList) {
                if (cvf.getOrDefault(value, 0) == 4) {
                    encodedHand.append(Integer.toHexString(value));
                }
            }

            for (int value : valueList) {
                if (cvf.getOrDefault(value, 0) == 3) {
                    encodedHand.append(Integer.toHexString(value));
                }
            }

            for (int value : valueList) {
                if (cvf.getOrDefault(value, 0) == 2) {
                    encodedHand.append(Integer.toHexString(value));
                }
            }

            for (int value : valueList) {
                if (!cvf.containsKey(value)) {
                    encodedHand.append(Integer.toHexString(value));
                }
            }
        } else {
            for (int value : valueList) {
                encodedHand.append(Integer.toHexString(value));
            }
        }

        String returnEncodedHand = String.valueOf(encodedHand);

        // Checks for A-5 straight, A becomes low card
        if (returnEncodedHand.equals("e5432")) {
            returnEncodedHand = "5432e";
        }

        return returnEncodedHand;
    }

    // Land of helper functions -----------------------------------------------------------------
    public static ArrayList<CardSuite> getSuiteList(ArrayList<Card> cards) {
        ArrayList<CardSuite> suiteList = new ArrayList<>();
        for (Card card : cards) {
            suiteList.add(card.getSuite());
        }
        return suiteList;
    }

    public static void removeSuitesExcept(CardSuite suite, ArrayList<Card> cards) {
        cards.removeIf(card -> card.getSuite() == suite);
    }

    public static ArrayList<Integer> getValueList(ArrayList<Card> cards) {
        ArrayList<Integer> valueList = new ArrayList<>();
        for (Card card : cards) {
            valueList.add(card.getIntValue());
        }
        return valueList;
    }

    public static Map<Integer, Integer> getCardValueFrequencyMap(ArrayList<Card> cards) {
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
}
