package logic;

import data.Card;
import data.Hand;
import enums.PokerHand;

import java.util.*;

public class BestHand {

    public static Hand generate(ArrayList<Card> playerCards, ArrayList<Card> communityCards) {
        Hand hand = new Hand();
        hand.addList(playerCards);
        hand.addList(communityCards);
        setBestHand(hand);
        return hand;
    }

    public static void setBestHand(Hand hand) {
        hand.sortCollection();

        checkFlush(hand);
        checkStraight(hand);
        checkPair(hand);
        checkHighCard(hand);

        setFiveBestCards(hand);
        encodeHand(hand);
    }

    private static void checkFlush(Hand hand) {
        ArrayList<String> suiteList = hand.getSuiteList();
        Set<String> uniqueSuites = new HashSet<>(suiteList);

        for (String suite : uniqueSuites) {
            if (Collections.frequency(suiteList, suite) >= 5) {
                removeSuitesExcept(suite, hand);
                hand.setHandType(PokerHand.FLUSH);
                return;
            }
        }
    }

    private static void checkStraight(Hand hand) {
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

    private static void setStraightType(Hand hand) {
        if (hand.getHandType() == PokerHand.FLUSH) {
            hand.setHandType(PokerHand.STRAIGHT_FLUSH);
        } else {
            hand.setHandType(PokerHand.STRAIGHT);
        }
    }

    private static void checkPair(Hand hand) {
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

    private static void checkHighCard(Hand hand) {
        if(hand.getHandType() == PokerHand.INIT) {
            hand.setHandType(PokerHand.HIGH_CARD);
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
            // remove duplicate or low cards
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

    private static void removeValuesInRange(Hand hand, int min, int max) {
        for (int value = min; value < max; value++) {
            int v = value;
            hand.getCards().removeIf(card -> Objects.equals(card.getValueInt(), v));
        }
    }

    private static void removeLowCards(Hand hand) {
        while (hand.getCards().size() > 5) {
            hand.getCards().remove(0);
        }
    }

    private static void removeDuplicateValueCards(Hand hand) {
        for (int i = hand.getCards().size() - 2; i >= 0; i--) {
            if ( hand.getCard(i).getValue() == hand.getCard(i + 1).getValue()) {
                hand.getCards().remove(i+1);
            }
        }
    }
}
