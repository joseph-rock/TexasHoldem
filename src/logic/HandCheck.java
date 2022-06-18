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
 * @version 1.0
 */
public class HandCheck {

    public static void check(CardCollection cards) {
        cards.sortCollection();

        if ( isFlush(cards) ) {
            if ( isStraight(cards) ) {
                cards.setHandType(PokerHand.STRAIGHT_FLUSH);
            } else {
                cards.setHandType(PokerHand.FLUSH);
            }
        } else if ( isStraight(cards) ) {
            cards.setHandType(PokerHand.STRAIGHT);
        } else {
            cards.setHandType(PokerHand.HIGH_CARD);
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

        ArrayList<Integer> uniqueValueList = new ArrayList<>(uniqueValues);
        for (int i = uniqueValueList.size() - 1; i >= 4; i--) {
            if ( uniqueValueList.get(i) - uniqueValueList.get(i-4) == 4)
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
        Map<Integer, Integer> countList = cards.mapCardValueFrequency();

        if (countList.size() == 0) {
            return;
        }

        if (countList.size() == 1 && countList.containsValue(2)) {
            cards.setHandType(PokerHand.PAIR);
            return;
        }

        if (countList.size() == 1 && countList.containsValue(3)) {
            cards.setHandType(PokerHand.THREE_OF_A_KIND);
            return;
        }

        if (countList.containsValue(4)) {
            cards.setHandType(PokerHand.FOUR_OF_A_KIND);
            return;
        }

        if (countList.containsValue(3)) {
            cards.setHandType(PokerHand.FULL_HOUSE);
            return;
        }

        if (countList.containsValue(2)) {
            cards.setHandType(PokerHand.TWO_PAIR);
        }
    }
}
