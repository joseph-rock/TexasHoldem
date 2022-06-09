package logic;

import data.Card;
import data.CardCollection;

import java.util.*;

/** Poker hands in order from best to worst:
 *      NAME                TIEBREAKER
 *
 *      Royal Flush -       Tie unbreakable
 *      Straight Flush -    High card
 *      Four of a Kind -    Best, only one can
 *                          occur per round
 *      Full House -        Highest three of a kind
 *      Flush -             High card
 *      Straight -          High card
 *      Three of a kind -   High card
 *      Two pair -          Highest pair, then second
 *                          highest pair, then 5th
 *      Pair -              Highest pair, then 3rd,
 *                          4th, then 5th
 *      High Card -         Highest card, then 2nd,
 *                          3rd, 4th, 5th
 */
public class HandCheck {

    /** 
     *  Hand.check() will take a single ArrayList of 5 or more cards, 
     *  and determine the coresponding poker hand.
     */
    public static void check(ArrayList<Card> cardList) {
        //ArrayList<Card> checkList = new ArrayList<>(cardList);

        CardCollection cards = new CardCollection(cardList);
        cards.sortCollection();

        // Check for flush
        if ( isFlush(cards) ) {
            // Check for straight
            if ( isStraight(cards) ) {
                System.out.println("Straight Flush!");
                cards.removeDuplicateValueCards();
            } else {
                System.out.println("Flush!");
            }
            cards.removeLowCards();
            cards.printCards();
            return;
        }

        // check for straight
        if ( isStraight(cards) ) {
            cards.removeDuplicateValueCards();
            cards.removeLowCards();
            System.out.println("Straight!");
            cards.printCards();
            return;
        }

        // Check 4 of a kind

        // Check full  house

        // Check flush

        cards.printCards();
    }

    /** 
     * If given two ArrayLists, combine into a single list and pass to
     *  main Hand.check().
     */
    public static void check(ArrayList<Card> playerCards, ArrayList<Card> communityCards) {
        ArrayList<Card> cardList = new ArrayList<>(playerCards);
        cardList.addAll( new ArrayList<>(communityCards) );
        check(cardList);
    }

    /** */
    public static Boolean isFlush(CardCollection cards) {

        ArrayList<String> checkSuites = cards.getSuiteList();
        HashSet<String> uniqueSuites = new HashSet<>(checkSuites);

        for (String suite : uniqueSuites) {
            if (Collections.frequency(checkSuites, suite) >= 5) {
                cards.removeSuitesNotEqualTo(suite);
                return true;
            }
        }
        return false;
    }

    /** */
    public static Boolean isStraight(CardCollection cards) {

        // Create HashSet with each Card.value
        ArrayList<Integer> checkValues = cards.getValueList();
        HashSet<Integer> uniqueValues = new HashSet<>(checkValues);

        // Straight has to be 5 unique card values or more
        if (uniqueValues.size() < 5) {
            return false;
        }

        // Manually check low straight (A, 2, 3, 4, 5)
        if (uniqueValues.contains(12) && uniqueValues.contains(0) && uniqueValues.contains(1) && uniqueValues.contains(2) && uniqueValues.contains(3)) {
            cards.removeValueInRange(4, 12);
            return true;
        }

        // Actually disgusting
        for (int i = checkValues.size() - 1; i >= 4; i--) {
            if ( checkValues.get(i)   - checkValues.get(i-1) == 1
                    && checkValues.get(i-1) - checkValues.get(i-2) == 1
                    && checkValues.get(i-2) - checkValues.get(i-3) == 1
                    && checkValues.get(i-3) - checkValues.get(i-4) == 1)
            {
                cards.removeValueInRange(checkValues.get(i), 13);
                cards.removeValueInRange(0, checkValues.get(i-4));
                return true;
            }
        }

        return false;
    }

    public static Boolean isPair(ArrayList<Card> checkList) {
        ArrayList<String> valueList = getValueStringList(checkList);
        HashSet<String> valueSet = new HashSet<>(valueList);

        // No pairs
        if (valueSet.size() == 7) {
            return false;
        }

        // Single pair
        if (valueSet.size() == 6) {
            return true;
        }
        
        // List of pair frequency
        ArrayList<Integer> countList = new ArrayList<>();
        for (String value : valueSet) {
            if (Collections.frequency(valueList, value) != 1) {
                countList.add(Collections.frequency(valueList, value));
            }
        }
        Collections.sort(countList);

        // Three of a kind
        if (countList.size() == 1 && countList.get(0) == 3) {
            System.out.println("three of a kind");
            return true;
        }

        // Four of a kind
        if (countList.get(countList.size()-1) == 4) {
            System.out.println("Four of a kind");
            return true;
        }

        // Two pair
        if (countList.get(countList.size()-1) == 2) {
            System.out.println("Two pair");
            return true;
        }

        // Full house
        if (countList.get(countList.size()-1) == 3) {
            System.out.println("Full house");
            return true;
        }
        
        return false;
    }

    /** Convert list of Card objects to list of Card.valueAsString objects */
    private static ArrayList<String> getValueStringList(ArrayList<Card> checkList) {
        ArrayList<String> valueStringList = new ArrayList<>();
        for (Card card : checkList) {
            valueStringList.add(card.valueAsString);
        }
        return valueStringList;
    }
    
}
