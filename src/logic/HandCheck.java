package logic;

import data.enums.PokerHand;

import java.util.*;

/** Poker hands in order from best to worst:
 *      NAME                TIEBREAKER
 *
 * 9     Royal Flush -       Tie unbreakable
 * 8     Straight Flush -    High card
 * 7     Four of a Kind -    Best, only one can
 *                          occur per round
 * 6     Full House -        Highest three of a kind
 * 5     Flush -             High card
 * 4     Straight -          High card
 * 3     Three of a kind -   High card
 * 2     Two pair -          Highest pair, then second
 *                          highest pair, then 5th
 * 1     Pair -              Highest pair, then 3rd,
 *                          4th, then 5th
 * 0     High Card -         Highest card, then 2nd,
 *                          3rd, 4th, 5th
 */
public class HandCheck {

    public static void check(CardCollection cards) {
        cards.sortCollection();

        // Check for flush
        if ( isFlush(cards) ) {
            // Check for straight flush
            if ( isStraight(cards) ) {
                cards.setHand(PokerHand.STRAIGHT_FLUSH);
            } else {
                cards.setHand(PokerHand.FLUSH);
            }
            cards.getBestHand();
            cards.encodeHand();
        }

        // check for straight
        else if ( isStraight(cards) ) {
            cards.setHand(PokerHand.STRAIGHT);
            cards.getBestHand();
            cards.encodeHand();
        }

        // check for pairs
        else if ( isPair(cards) ) {
            cards.getBestHand();
            cards.encodeHand();
        }

        // high card
        else {
            cards.getBestHand();
            cards.setHand(PokerHand.HIGH_CARD);
            cards.encodeHand();
        }
    }

    public static Boolean isFlush(CardCollection cards) {

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

    public static Boolean isStraight(CardCollection cards) {

        // Create Set with each Card.value
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

    public static Boolean isPair(CardCollection cards) {
        Map<Integer, Integer> countList = cards.getValueFrequencyMap();

        // No pairs, check first to optimize
        if (countList.size() == 0) {
            return false;
        }

        // Single pair
        if (countList.size() == 1 && countList.containsValue(2)) {
            cards.setHand(PokerHand.PAIR);
            return true;
        }

        // Three of a kind
        if (countList.size() == 1 && countList.containsValue(3)) {
            cards.setHand(PokerHand.THREE_OF_A_KIND);
            return true;
        }

        // Four of a kind
        if (countList.containsValue(4)) {
            cards.setHand(PokerHand.FOUR_OF_A_KIND);
            return true;
        }

        // Full house
        if (countList.containsValue(3)) {
            cards.setHand(PokerHand.FULL_HOUSE);
            return true;
        }

        // Two pair
        if (countList.containsValue(2)) {
            cards.setHand(PokerHand.TWO_PAIR);
            return true;
        }
        
        return false;
    }
    
}
