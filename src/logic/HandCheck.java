package logic;

import data.Card;

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
        ArrayList<Card> checkList = new ArrayList<>(cardList);
        Collections.sort(checkList);

        // Check for flush
        isFlush(checkList);

        // check for straight
        isStraight(checkList);

        // Check 4 of a kind

        // Check full  house

        // Check flush
    }

    /** 
     * If given two ArrayLists, combine into a single list and pass to
     *  main Hand.check().
     */
    public static void check(ArrayList<Card> playerCards, ArrayList<Card> communityCards) {
        ArrayList<Card> cardList = new ArrayList<>(playerCards);
        cardList.addAll(communityCards);
        check(cardList);
    }

    /** */
    public static Boolean isFlush(ArrayList<Card> checkList) {

        ArrayList<String> checkSuites = getSuiteList(checkList);
        HashSet<String> uniqueSuites = new HashSet<>(checkSuites);

        for (String suite : uniqueSuites) {
            if (Collections.frequency(checkSuites, suite) >= 5) {
                bestFlushHand(checkList, suite);
                return true;
            }
        }
        return false;
    }

    private static void bestFlushHand(ArrayList<Card> checkList, String suite) {
        checkList.removeIf(card -> !Objects.equals(card.suite, suite));
        while (checkList.size() > 5) {
            checkList.remove(0);
        }
    }

    /** */
    public static Boolean isStraight(ArrayList<Card> checkList) {

        // Create HashSet with each Card.value
        HashSet<Integer> valueSet = new HashSet<>(getValueList(checkList));

        // Straight has to be 5 cards or more
        if (valueSet.size() < 5) {
            return false;
        }

        // Manually check low straight (A, 2, 3, 4, 5)
        if (valueSet.contains(12) && valueSet.contains(0) && valueSet.contains(1) && valueSet.contains(2) && valueSet.contains(3)) {
            return true;
        }

        // Actually disgusting
        List<Integer> list = new ArrayList<>(valueSet);
        Collections.sort(list);

        for (int i = list.size() - 1; i >= 4; i--) {
            if (       list.get(i)   - list.get(i-1) == 1 
                    && list.get(i-1) - list.get(i-2) == 1
                    && list.get(i-2) - list.get(i-3) == 1
                    && list.get(i-3) - list.get(i-4) == 1) 
            {
                return true;
            }
        }

        return false;
    }
    
    private static void bestStraightHand(ArrayList<Card> checkList, int value) {
        // TODO: Make Work
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

    /** Convert list of Card objects to list of Card.suite objects */
    private static ArrayList<String> getSuiteList(ArrayList<Card> checkList) {
        ArrayList<String> suiteList = new ArrayList<>();
        for (Card card : checkList) {
            suiteList.add(card.suite);
        }
        return suiteList;
    }

    /** Convert list of Card objects to list of Card.valueAsString objects */
    private static ArrayList<String> getValueStringList(ArrayList<Card> checkList) {
        ArrayList<String> valueStringList = new ArrayList<>();
        for (Card card : checkList) {
            valueStringList.add(card.valueAsString);
        }
        return valueStringList;
    }

    /** Convert list of Card objects to list of Card.value objects */
    private static ArrayList<Integer> getValueList(ArrayList<Card> checkList) {
        ArrayList<Integer> suiteList = new ArrayList<>();
        for (Card card : checkList) {
            suiteList.add(card.value);
        }
        return suiteList;
    }
    
}
