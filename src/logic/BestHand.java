package logic;

import data.Card;
import enums.CardSuite;
import enums.CardValue;
import enums.PokerHand;

import java.util.*;

public class BestHand {

    public static PokerHand bestPokerHand(final ArrayList<Card> cards) {

        if(isRoyalFlush(cards)) {
            return PokerHand.ROYAL_FLUSH;
        } else if(isStraightFlush(cards)) {
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

        return switch (bestPokerHand(cards)) {
            case ROYAL_FLUSH, STRAIGHT_FLUSH -> encodeStraightFlush(cards);
            case FOUR_OF_A_KIND -> encodeFourOfAKind(cards);
            case FULL_HOUSE -> encodeFullHouse(cards);
            case FLUSH -> encodeFlush(cards);
            case STRAIGHT -> encodeStraight(cards);
            case THREE_OF_A_KIND -> encodeThreeOfAKind(cards);
            case TWO_PAIR -> encodeTwoPair(cards);
            case PAIR -> encodePair(cards);
            case HIGH_CARD -> encodeHighCard(cards);
        };

    }

    private static Boolean isPair(final ArrayList<Card> cards) {
        Map<Integer, Integer> fpv = frequencyOfPairedValues(cards);
        return fpv.size() == 1
                && fpv.containsValue(2);
    }

    private static Boolean isTwoPair(final ArrayList<Card> cards) {
        Map<Integer, Integer> fpv = frequencyOfPairedValues(cards);
        return fpv.size() > 1
                && fpv.containsValue(2)
                && !fpv.containsValue(3)
                && !fpv.containsValue(4);
    }

    private static Boolean isThreeOfAKind(final ArrayList<Card> cards) {
        Map<Integer, Integer> fpv = frequencyOfPairedValues(cards);
        return fpv.size() == 1
                && fpv.containsValue(3);
    }

    private static Boolean isFourOfAKind(final ArrayList<Card> cards) {
        Map<Integer, Integer> fpv = frequencyOfPairedValues(cards);
        return fpv.size() > 0
                && fpv.containsValue(4);
    }

    private static Boolean isFullHouse(final ArrayList<Card> cards) {
        Map<Integer, Integer> fpv = frequencyOfPairedValues(cards);
        return fpv.size() > 1
                && fpv.containsValue(3)
                && !fpv.containsValue(4);
    }

    private static Boolean isFlush(final ArrayList<Card> cards) {
        return flushSuite(cards).isPresent();
    }

    private static Boolean isStraight(final ArrayList<Card> cards) {
        return straightWindow(cards).isPresent();
    }

    private static Boolean isStraightFlush(final ArrayList<Card> cards) {
        if ( isFlush(cards) ) {
            return isStraight( bestFlushCards(cards) );
        }
        return false;
    }

    private static Boolean isRoyalFlush(final ArrayList<Card> cards) {
        return isStraightFlush(cards) && encodeStraightFlush(cards).equals("8edcba");
    }

    private static String encodePair(final ArrayList<Card> cards) {
        ArrayList<Card> copy = new ArrayList<>(cards);
        Map<Integer, Integer> fpv = frequencyOfPairedValues(cards);
        StringBuilder encoding = new StringBuilder();

        copy.sort(Collections.reverseOrder());
        for ( Card card : copy ) {
            if ( encoding.length() < 2
                    && fpv.getOrDefault(card.getIntValue(), 0) == 2 ) {
                encoding.append(Integer.toHexString(card.getIntValue()));
            }
        }

        return PokerHand.PAIR.getRank() + finalizeEncoding(copy, encoding);
    }

    private static String encodeTwoPair(final ArrayList<Card> cards) {
        ArrayList<Card> copy = new ArrayList<>(cards);
        Map<Integer, Integer> fpv = frequencyOfPairedValues(cards);
        StringBuilder encoding = new StringBuilder();

        copy.sort(Collections.reverseOrder());
        for ( Card card : copy ) {
            if ( encoding.length() < 4
                    && fpv.getOrDefault(card.getIntValue(), 0) == 2 ) {
                encoding.append(Integer.toHexString(card.getIntValue()));
            }
        }

        return PokerHand.TWO_PAIR.getRank() + finalizeEncoding(copy, encoding);
    }

    private static String encodeThreeOfAKind(final ArrayList<Card> cards) {
        ArrayList<Card> copy = new ArrayList<>(cards);
        Map<Integer, Integer> fpv = frequencyOfPairedValues(cards);
        StringBuilder encoding = new StringBuilder();

        copy.sort(Collections.reverseOrder());
        for ( Card card : copy ) {
            if ( encoding.length() < 3
                    && fpv.getOrDefault(card.getIntValue(), 0) == 3 ) {
                encoding.append(Integer.toHexString(card.getIntValue()));
            }
        }

        return PokerHand.THREE_OF_A_KIND.getRank() + finalizeEncoding(copy, encoding);
    }

    private static String encodeFourOfAKind(final ArrayList<Card> cards) {
        ArrayList<Card> copy = new ArrayList<>(cards);
        Map<Integer, Integer> fpv = frequencyOfPairedValues(cards);
        StringBuilder encoding = new StringBuilder();

        copy.sort(Collections.reverseOrder());
        for ( Card card : copy ) {
            if ( encoding.length() < 4
                    && fpv.getOrDefault(card.getIntValue(), 0) == 4 ) {
                encoding.append(Integer.toHexString(card.getIntValue()));
            }
        }

        return PokerHand.FOUR_OF_A_KIND.getRank() + finalizeEncoding(copy, encoding);
    }

    private static String encodeFullHouse(final ArrayList<Card> cards) {
        ArrayList<Card> copy = new ArrayList<>(cards);
        Map<Integer, Integer> fpv = frequencyOfPairedValues(cards);
        StringBuilder encoding = new StringBuilder();

        copy.sort(Collections.reverseOrder());
        for ( Card card : copy ) {
            if ( encoding.length() < 3
                    && fpv.getOrDefault(card.getIntValue(), 0) == 3 ) {
                encoding.append(Integer.toHexString(card.getIntValue()));
            }
        }

        for (Card card : copy) {
            if ( encoding.length() < 5
                    && !String.valueOf(encoding.charAt(0)).equals(Integer.toHexString(card.getIntValue()))
                    && fpv.containsKey(card.getIntValue())) {
                encoding.append(Integer.toHexString(card.getIntValue()));
            }
        }

        return PokerHand.FULL_HOUSE.getRank() + String.valueOf(encoding);
    }

    private static String encodeHighCard(final ArrayList<Card> cards) {
        ArrayList<Card> copy = new ArrayList<>(cards);
        copy.sort(Collections.reverseOrder());
        return PokerHand.HIGH_CARD.getRank() + finalizeEncoding(copy, new StringBuilder());
    }

    private static String encodeStraight(final ArrayList<Card> cards) {
        ArrayList<Card> bsc = bestStraightCards(cards);
        bsc.sort(Collections.reverseOrder());
        String finalizedEncoding = finalizeEncoding(bsc, new StringBuilder());

        // Checks for A-5 straight, A becomes low card
        if (finalizedEncoding.equals(Integer.toHexString( CardValue.ACE.toInt())
                + Integer.toHexString( CardValue.FIVE.toInt())
                + Integer.toHexString( CardValue.FOUR.toInt())
                + Integer.toHexString( CardValue.THREE.toInt())
                + Integer.toHexString( CardValue.TWO.toInt())))
        {
            finalizedEncoding = Integer.toHexString( CardValue.FIVE.toInt())
                    + Integer.toHexString( CardValue.FOUR.toInt())
                    + Integer.toHexString( CardValue.THREE.toInt())
                    + Integer.toHexString( CardValue.TWO.toInt())
                    + Integer.toHexString( CardValue.ACE.toInt());
        }

        return PokerHand.STRAIGHT.getRank() + finalizedEncoding;
    }

    private static String encodeFlush(final ArrayList<Card> cards) {
        ArrayList<Card> bfc = bestFlushCards(cards);
        bfc.sort(Collections.reverseOrder());
        return PokerHand.FLUSH.getRank() + finalizeEncoding(bfc, new StringBuilder());
    }

    private static String encodeStraightFlush(final ArrayList<Card> cards) {
        ArrayList<Card> bfc = bestFlushCards(cards);
        ArrayList<Card> straightFlushCards = bestStraightCards(bfc);
        String straightFlushEncoding = encodeStraight(straightFlushCards);
        return PokerHand.STRAIGHT_FLUSH.getRank() + straightFlushEncoding.substring(1);
    }

    private static String finalizeEncoding(ArrayList<Card> cards, StringBuilder encoding) {
        StringBuilder copy = new StringBuilder(encoding);
        for (Card card : cards) {
            if (copy.length() < 5 && !String.valueOf(copy).contains(Integer.toHexString(card.getIntValue()))) {
                copy.append(Integer.toHexString(card.getIntValue()));
            }
        }
        return String.valueOf(copy);
    }

    private static ArrayList<Card> bestStraightCards(final ArrayList<Card> cards) {
        ArrayList<Card> bestStraightCards = new ArrayList<>(cards);
        List<Integer> window = straightWindow(cards).orElse(new ArrayList<>());
        bestStraightCards.removeIf(card -> !window.contains(card.getIntValue()));
        return bestStraightCards;
    }

    private static ArrayList<Card> bestFlushCards(final ArrayList<Card> cards) {
        ArrayList<Card> bestFlushCards = new ArrayList<>(cards);
        Optional<CardSuite> suite = flushSuite(cards);
        suite.ifPresent(cardSuite -> bestFlushCards.removeIf(card -> card.getSuite() != cardSuite));
        return bestFlushCards;
    }

    private static ArrayList<CardSuite> suiteList(final ArrayList<Card> cards) {
        return new ArrayList<>(cards.stream().map(Card::getSuite).toList());
    }

    private static ArrayList<Integer> valueList(final ArrayList<Card> cards) {
        return new ArrayList<>(cards.stream().map(Card::getIntValue).toList());
    }

    private static Map<Integer, Integer> frequencyOfPairedValues(final ArrayList<Card> cards) {
        HashMap<Integer, Integer> frequencyOfPairedValues = new HashMap<>();
        ArrayList<Integer> vl = valueList(cards);

        for (int value : vl) {
            int frequency = Collections.frequency(vl, value);
            if (frequency != 1) {
                frequencyOfPairedValues.put(value, frequency);
            }
        }
        return frequencyOfPairedValues;
    }

    private static ArrayList<Integer> uniqueValuesList(final ArrayList<Card> cards) {
        Set<Integer> valueSet = new HashSet<>(valueList(cards));
        return new ArrayList<>(valueSet);
    }

    private static Optional<CardSuite> flushSuite(final ArrayList<Card> cards) {
        ArrayList<CardSuite> suiteList = suiteList(cards);
        for (CardSuite suite : CardSuite.values()) {
            if (Collections.frequency(suiteList, suite) >= 5) {
                return Optional.of(suite);
            }
        }
        return Optional.empty();
    }

    private static Optional<List<Integer>> straightWindow(final ArrayList<Card> cards) {
        ArrayList<Integer> uniqueValues = uniqueValuesList(cards);
        uniqueValues.sort(Collections.reverseOrder());

        List<Integer> lowStraight = Arrays.asList(
                CardValue.ACE.toInt(),
                CardValue.TWO.toInt(),
                CardValue.THREE.toInt(),
                CardValue.FOUR.toInt(),
                CardValue.FIVE.toInt());

        if (uniqueValues.containsAll(lowStraight)) {
            return Optional.of(lowStraight);
        }

        for (int val : uniqueValues) {
            List<Integer> window = Arrays.asList(val-2, val-1, val, val+1, val+2);
            if(uniqueValues.containsAll(window)){
                return Optional.of(window);
            }
        }

        return Optional.empty();
    }
}
