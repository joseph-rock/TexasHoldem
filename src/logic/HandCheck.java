package logic;

import enums.PokerHand;

import java.util.*;

/**
 * HandCheck.check is designed to take a CardCollection object containing 5 or
 * more Card objects and determine the best possible hand. It will:
 *      1) Set CardCollection.hand to the best PokerHand
 *      2) Set CardCollection.encodedCards to the 5 best cards in order of importance
 * After running HandCheck.check, CardCollection objects can then be compared using
 * CardCollection.isBetterHand and CardCollection.isDraw.
 *
 * @author Joseph Rock
 * @version 6.18.2022
 */
public class HandCheck {

    public static void check(CardCollection cards) {
        cards.sortCollection();

        if ( isFlush(cards) ) {
            if ( isStraight(cards) ) {
                cards.setHand(PokerHand.STRAIGHT_FLUSH);
            } else {
                cards.setHand(PokerHand.FLUSH);
            }
        } else if ( isStraight(cards) ) {
            cards.setHand(PokerHand.STRAIGHT);
        } else {
            cards.setHand(PokerHand.HIGH_CARD);
        }
        checkPair(cards);
        cards.getBestHand();
        cards.encodeHand();
    }

    private static Boolean isFlush(CardCollection cards) {

        ArrayList<String> checkSuites = cards.getSuiteList();
        HashSet<String> uniqueSuites = new HashSet<>(checkSuites);

        for (String suite : uniqueSuites) {
            if (Collections.frequency(checkSuites, suite) >= 5) {
                cards.removeSuitesExcept(suite);
                return true;
            }
        }
        return false;
    }

    private static Boolean isStraight(CardCollection cards) {
        Set<Integer> uniqueValues = new HashSet<>(cards.getValueList());

        // Straight has to be 5 unique card values or more
        if (uniqueValues.size() < 5) {
            return false;
        }

        // Manually check low straight (A, 2, 3, 4, 5)
        if (uniqueValues.contains(14) && uniqueValues.contains(2) && uniqueValues.contains(3) &&
                uniqueValues.contains(4) && uniqueValues.contains(5)) {
            cards.removeValueInRange(6, 14);
            cards.removeDuplicateValueCards();
            return true;
        }

        // Actually disgusting
        ArrayList<Integer> uniqueValueList = new ArrayList<>(uniqueValues);
        for (int i = uniqueValueList.size() - 1; i >= 4; i--) {
            if ( uniqueValueList.get(i)   - uniqueValueList.get(i-1) == 1
                    && uniqueValueList.get(i-1) - uniqueValueList.get(i-2) == 1
                    && uniqueValueList.get(i-2) - uniqueValueList.get(i-3) == 1
                    && uniqueValueList.get(i-3) - uniqueValueList.get(i-4) == 1)
            {
                cards.removeValueInRange(uniqueValueList.get(i) + 1, 15);
                cards.removeValueInRange(2, uniqueValueList.get(i-4));
                cards.removeDuplicateValueCards();
                return true;
            }
        }
        return false;
    }

    private static void checkPair(CardCollection cards) {
        Map<Integer, Integer> countList = cards.getValueFrequencyMap();

        // No pairs
        if (countList.size() == 0) {
            return;
        }

        // Single pair
        if (countList.size() == 1 && countList.containsValue(2)) {
            cards.setHand(PokerHand.PAIR);
            return;
        }

        // Three of a kind
        if (countList.size() == 1 && countList.containsValue(3)) {
            cards.setHand(PokerHand.THREE_OF_A_KIND);
            return;
        }

        // Four of a kind
        if (countList.containsValue(4)) {
            cards.setHand(PokerHand.FOUR_OF_A_KIND);
            return;
        }

        // Full house
        if (countList.containsValue(3)) {
            cards.setHand(PokerHand.FULL_HOUSE);
            return;
        }

        // Two pair
        if (countList.containsValue(2)) {
            cards.setHand(PokerHand.TWO_PAIR);
        }
    }
}
