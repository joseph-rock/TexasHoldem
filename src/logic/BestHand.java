package logic;

import data.Card;
import data.Hand;
import enums.PokerHand;

import java.util.*;

/**
 * BestHand.set is designed to take a CardCollection object containing 5 to 7
 * Card objects and determine the best possible hand. It will:
 *      1) Reduce CardCollection.cards to the 5 best cards in the hand
 *      2) Set CardCollection.hand to the best possible PokerHand
 *      3) Set CardCollection.encodedCards to hex representation of the 5 best cards in order of importance
 * After running BestHand.set, CardCollection objects can then be compared using
 * CardCollection.isBetterHand and CardCollection.isDraw.
 *
 * @author Joseph Rock
 * @version 1.0
 */
public class BestHand {

    public static void set(Hand cards) {
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
            setPair(cards);
        }
        setFiveBestCards(cards);
        encodeHand(cards);
    }

    public static Hand calculate(ArrayList<Card> playerCards, ArrayList<Card> communityCards) {
        Hand hand = new Hand();
        hand.addList(playerCards);
        hand.addList(communityCards);
        hand.sortCollection();

        if ( isFlush(hand) ) {
            if ( isStraight(hand) ) {
                hand.setHandType(PokerHand.STRAIGHT_FLUSH);
            } else {
                hand.setHandType(PokerHand.FLUSH);
            }
        } else if ( isStraight(hand) ) {
            hand.setHandType(PokerHand.STRAIGHT);
        } else {
            hand.setHandType(PokerHand.HIGH_CARD);
            setPair(hand);
        }
        setFiveBestCards(hand);
        encodeHand(hand);

        return hand;
    }

    private static Boolean isFlush(Hand hand) {
        ArrayList<String> suiteList = hand.getSuiteList();
        HashSet<String> uniqueSuites = new HashSet<>(suiteList);

        for (String uniqueSuite : uniqueSuites) {
            if (Collections.frequency(suiteList, uniqueSuite) >= 5) {
                removeSuitesExcept(uniqueSuite, hand);
                return true;
            }
        }
        return false;
    }

    private static Boolean isStraight(Hand hand) {
        Set<Integer> uniqueValues = new HashSet<>(hand.getValueList());

        // Straight has to be 5 unique card values or more
        if (uniqueValues.size() < 5) {
            return false;
        }

        // Manually check low straight (A, 2, 3, 4, 5)
        if (uniqueValues.contains(14) && uniqueValues.contains(2) && uniqueValues.contains(3) &&
                uniqueValues.contains(4) && uniqueValues.contains(5)) {
            removeValueInRange(hand, 6, 14);
            removeDuplicateValueCards(hand);
            return true;
        }

        ArrayList<Integer> uniqueValueList = new ArrayList<>(uniqueValues);
        for (int i = uniqueValueList.size() - 1; i >= 4; i--) {
            if ( uniqueValueList.get(i) - uniqueValueList.get(i-4) == 4)
            {
                removeValueInRange(hand, uniqueValueList.get(i) + 1, 15);
                removeValueInRange(hand, 2, uniqueValueList.get(i-4));
                removeDuplicateValueCards(hand);
                return true;
            }
        }
        return false;
    }

    private static void setPair(Hand hand) {
        Map<Integer, Integer> countList = hand.getCardValueFrequency();

        if (countList.size() == 0) {
            return;
        }

        if (countList.size() == 1 && countList.containsValue(2)) {
            hand.setHandType(PokerHand.PAIR);
            return;
        }

        if (countList.size() == 1 && countList.containsValue(3)) {
            hand.setHandType(PokerHand.THREE_OF_A_KIND);
            return;
        }

        if (countList.containsValue(4)) {
            hand.setHandType(PokerHand.FOUR_OF_A_KIND);
            return;
        }

        if (countList.containsValue(3)) {
            hand.setHandType(PokerHand.FULL_HOUSE);
            return;
        }

        if (countList.containsValue(2)) {
            hand.setHandType(PokerHand.TWO_PAIR);
        }
    }

    private static void setFiveBestCards(Hand hand) {
        ArrayList<Card> cards = hand.getCards();
        cards.sort(Collections.reverseOrder());

        ArrayList<Card> bestHand = new ArrayList<>();
        Map<Integer, Integer> cardFrequencyMap = hand.getCardValueFrequency();

        // Creates best hand with 4 of a kind
        if (cardFrequencyMap.containsValue(4)) {
            for ( Card card : cards ) {
                if ( cardFrequencyMap.getOrDefault(card.getValueInt(), 0) == 4 ) {
                    bestHand.add(card);
                }
            }

            if (cards.get(0).getValueInt() == bestHand.get(0).getValueInt()) {
                bestHand.add(cards.get(4));
            } else {
                bestHand.add(cards.get(0));
            }
        }

        // Build best hand with three of a kind present
        else if ( cardFrequencyMap.containsValue(3) ) {

            // get highest three of a kind
            for ( Card card : cards ) {
                if ( bestHand.size() < 3 && cardFrequencyMap.getOrDefault(card.getValueInt(), 0) == 3 ) {
                    bestHand.add(card);
                }
            }

            // If more pairs exist, choose highest 2 cards
            if ( cardFrequencyMap.size() > 1 ) {
                for (Card card : cards) {
                    if (cardFrequencyMap.containsKey(card.getValueInt())
                            && card.getValueInt() != bestHand.get(0).getValueInt()
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
                if ( cardFrequencyMap.getOrDefault(card.getValueInt(), 0) == 2 && bestHand.size() < 4 ) {
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
            hand.setCards(bestHand);
            hand.sortCollection();
        } else {
            // remove dupes or low cards
            hand.sortCollection();
            removeDuplicateValueCards(hand);
            removeLowCards(hand);
        }
    }

    private static void encodeHand(Hand hand) {
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

        hand.setCardsEncoded(String.valueOf(encodedHand));

        // Checks for A-5 straight, A becomes low card
        if (hand.getCardsEncoded().equals("e5432")) {
            hand.setCardsEncoded("5432e");
        }
        if (hand.getCardsEncoded().equals("edcba") && hand.getHandType() == PokerHand.STRAIGHT_FLUSH) {
            hand.setHandType(PokerHand.ROYAL_FLUSH);
        }
    }

    private static void removeSuitesExcept(String suite, Hand hand) {
        hand.getCards().removeIf(card -> !Objects.equals(card.getSuiteString(), suite));
    }

    private static void removeValueEqualTo(Hand hand, int value) {
        hand.getCards().removeIf(card -> Objects.equals(card.getValueInt(), value));
    }

    private static void removeValueInRange(Hand hand, int min, int max) {
        for (int value = min; value < max; value++) {
            removeValueEqualTo(hand, value);
        }
    }

    private static void removeLowCards(Hand hand) {
        while (hand.getCards().size() > 5) {
            hand.getCards().remove(0);
        }
    }

    private static void removeDuplicateValueCards(Hand hand) {
        for (int i = hand.getCards().size() - 2; i >= 0; i--) {
            if ( hand.getCard(i).getValueInt() == hand.getCard(i + 1).getValueInt()) {
                hand.getCards().remove(i+1);
            }
        }
    }
}
