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
        }
        checkPair(cards);
        setFiveBestCards(cards);
        encodeHand(cards);
    }

    public static Hand setHand(ArrayList<Card> playerCards, ArrayList<Card> communityCards) {
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
        }
        checkPair(hand);
        setFiveBestCards(hand);
        encodeHand(hand);

        return hand;
    }

    private static Boolean isFlush(Hand c) {
        ArrayList<String> checkSuites = c.getSuiteList();
        HashSet<String> uniqueSuites = new HashSet<>(checkSuites);

        for (String suite : uniqueSuites) {
            if (Collections.frequency(checkSuites, suite) >= 5) {
                removeSuitesExcept(c, suite);
                return true;
            }
        }
        return false;
    }

    private static Boolean isStraight(Hand c) {
        Set<Integer> uniqueValues = new HashSet<>(c.getValueList());

        // Straight has to be 5 unique card values or more
        if (uniqueValues.size() < 5) {
            return false;
        }

        // Manually check low straight (A, 2, 3, 4, 5)
        if (uniqueValues.contains(14) && uniqueValues.contains(2) && uniqueValues.contains(3) &&
                uniqueValues.contains(4) && uniqueValues.contains(5)) {
            removeValueInRange(c, 6, 14);
            removeDuplicateValueCards(c);
            return true;
        }

        ArrayList<Integer> uniqueValueList = new ArrayList<>(uniqueValues);
        for (int i = uniqueValueList.size() - 1; i >= 4; i--) {
            if ( uniqueValueList.get(i) - uniqueValueList.get(i-4) == 4)
            {
                removeValueInRange(c, uniqueValueList.get(i) + 1, 15);
                removeValueInRange(c, 2, uniqueValueList.get(i-4));
                removeDuplicateValueCards(c);
                return true;
            }
        }
        return false;
    }

    private static void checkPair(Hand c) {
        Map<Integer, Integer> countList = c.getCardValueFrequency();

        if (countList.size() == 0) {
            return;
        }

        if (countList.size() == 1 && countList.containsValue(2)) {
            c.setHandType(PokerHand.PAIR);
            return;
        }

        if (countList.size() == 1 && countList.containsValue(3)) {
            c.setHandType(PokerHand.THREE_OF_A_KIND);
            return;
        }

        if (countList.containsValue(4)) {
            c.setHandType(PokerHand.FOUR_OF_A_KIND);
            return;
        }

        if (countList.containsValue(3)) {
            c.setHandType(PokerHand.FULL_HOUSE);
            return;
        }

        if (countList.containsValue(2)) {
            c.setHandType(PokerHand.TWO_PAIR);
        }
    }

    private static void setFiveBestCards(Hand c) {
        ArrayList<Card> cards = c.getCards();
        cards.sort(Collections.reverseOrder());

        ArrayList<Card> bestHand = new ArrayList<>();
        Map<Integer, Integer> cardFrequencyMap = c.getCardValueFrequency();

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
            c.setCards(bestHand);
            c.sortCollection();
        } else {
            // remove dupes or low cards
            c.sortCollection();
            removeDuplicateValueCards(c);
            removeLowCards(c);
        }
    }

    private static void encodeHand(Hand c) {
        ArrayList<Integer> valueList = c.getValueList();
        valueList.sort(Collections.reverseOrder());

        Map<Integer, Integer> valueFrequency = c.getCardValueFrequency();
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

        c.setCardsEncoded(String.valueOf(encodedHand));

        // Checks for A-5 straight, A becomes low card
        if (c.getCardsEncoded().equals("e5432")) {
            c.setCardsEncoded("5432e");
        }
        if (c.getCardsEncoded().equals("edcba") && c.getHandType() == PokerHand.STRAIGHT_FLUSH) {
            c.setHandType(PokerHand.ROYAL_FLUSH);
        }
    }

    private static void removeSuitesExcept(Hand c, String suite) {
        c.getCards().removeIf(card -> !Objects.equals(card.getSuiteString(), suite));
    }

    private static void removeValueEqualTo(Hand c, int value) {
        c.getCards().removeIf(card -> Objects.equals(card.getValueInt(), value));
    }

    private static void removeValueInRange(Hand c, int min, int max) {
        for (int value = min; value < max; value++) {
            removeValueEqualTo(c, value);
        }
    }

    private static void removeLowCards(Hand c) {
        while (c.getCards().size() > 5) {
            c.getCards().remove(0);
        }
    }

    private static void removeDuplicateValueCards(Hand c) {
        for (int i = c.getCards().size() - 2; i >= 0; i--) {
            if ( c.getCard(i).getValueInt() == c.getCard(i + 1).getValueInt()) {
                c.getCards().remove(i+1);
            }
        }
    }
}
