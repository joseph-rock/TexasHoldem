package test.logic;

import data.*;
import enums.PokerHand;
import enums.CardSuite;
import enums.CardValue;
import data.Hand;
import logic.BestHand;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestHandTest {
    Deck deck = new Deck();

    @org.junit.jupiter.api.Test
    void checkStraightFlush() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.THREE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.EIGHT) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.STRAIGHT_FLUSH, cards.getPokerHand());
        assertEquals("87654", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkStraightFlushWithTwoPair() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.THREE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.THREE) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.STRAIGHT_FLUSH, cards.getPokerHand());
        assertEquals("65432", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkStraightFlushWithThreeOfAKind() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.EIGHT) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.NINE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.EIGHT) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.STRAIGHT_FLUSH, cards.getPokerHand());
        assertEquals("a9876", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkRoyalFlush() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.ACE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.KING) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.QUEEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.JACK) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.EIGHT) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.ROYAL_FLUSH, cards.getPokerHand());
        assertEquals("edcba", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkLowStraight() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.THREE) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.EIGHT) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getPokerHand());
        assertEquals("87654", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkLowestStraight() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.ACE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.THREE) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.EIGHT) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getPokerHand());
        assertEquals("5432e", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkHighestStraight() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.ACE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.KING) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.QUEEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.JACK) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.EIGHT) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getPokerHand());
        assertEquals("edcba", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkStraightWithThreeOfAKind() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.ACE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.KING) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.QUEEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.JACK) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TEN) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.TEN) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getPokerHand());
        assertEquals("edcba", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkStraightWithTwoPair() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.EIGHT) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.NINE) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.TEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.JACK) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.JACK) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getPokerHand());
        assertEquals("ba987", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkFlush() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.THREE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.EIGHT) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.FLUSH, cards.getPokerHand());
        assertEquals("87643", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkFlushWithThreeOfAKind() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.THREE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.EIGHT) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.FLUSH, cards.getPokerHand());
        assertEquals("87432", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkFlushWithStraightPresent() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.THREE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.EIGHT) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.FLUSH, cards.getPokerHand());
        assertEquals("87432", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkPair() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.THREE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.NINE) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.PAIR, cards.getPokerHand());
        assertEquals("44976", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkTwoPair() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.NINE) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.TWO_PAIR, cards.getPokerHand());
        assertEquals("44229", cards.getEncodedCards());
    }

    // Fuck
    @org.junit.jupiter.api.Test
    void checkThreePairResultsInTwoPair() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.NINE) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.TWO_PAIR, cards.getPokerHand());
        assertEquals("66449", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkThreeOfAKind() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.THREE_OF_A_KIND, cards.getPokerHand());
        assertEquals("222a8", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkFullHouse() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.FULL_HOUSE, cards.getPokerHand());
        assertEquals("22255", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkReallyFullHouse() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.FULL_HOUSE, cards.getPokerHand());
        assertEquals("55522", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkInvertedReallyFullHouse() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.SIX) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.FULL_HOUSE, cards.getPokerHand());
        assertEquals("22266", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkFourOfAKind() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.FOUR_OF_A_KIND, cards.getPokerHand());
        assertEquals("2222a", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkHighCard() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.NINE) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.ACE) );
        BestHand.setBestHand(cards);
        assertEquals(PokerHand.HIGH_CARD, cards.getPokerHand());
        assertEquals("ea986", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkPairBeatsHighCard() {
        Hand highCard = new Hand();
        highCard.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        highCard.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        highCard.addCard( deck.getCard(CardSuite.SPADES, CardValue.SIX) );
        highCard.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        highCard.addCard( deck.getCard(CardSuite.SPADES, CardValue.NINE) );
        highCard.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        highCard.addCard( deck.getCard(CardSuite.CLUBS, CardValue.ACE) );
        BestHand.setBestHand(highCard);

        Hand pair = new Hand();
        pair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        pair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.THREE) );
        pair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        pair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        pair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        pair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        pair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.NINE) );
        BestHand.setBestHand(pair);

        assertTrue(highCard.compareTo(pair) < 0);
    }

    @org.junit.jupiter.api.Test
    void checkHigherPairWins() {
        Hand highPair = new Hand();
        highPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        highPair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        highPair.addCard( deck.getCard(CardSuite.SPADES, CardValue.SIX) );
        highPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        highPair.addCard( deck.getCard(CardSuite.SPADES, CardValue.TEN) );
        highPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        highPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.ACE) );
        BestHand.setBestHand(highPair);

        Hand lowPair = new Hand();
        lowPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        lowPair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.THREE) );
        lowPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        lowPair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        lowPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        lowPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        lowPair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.NINE) );
        BestHand.setBestHand(lowPair);

        assertTrue(highPair.compareTo(lowPair) > 0);
    }

    @org.junit.jupiter.api.Test
    void checkEqualPairHighKickerWins() {
        Hand highPair = new Hand();
        highPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        highPair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        highPair.addCard( deck.getCard(CardSuite.SPADES, CardValue.SIX) );
        highPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        highPair.addCard( deck.getCard(CardSuite.SPADES, CardValue.TEN) );
        highPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        highPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.ACE) );
        BestHand.setBestHand(highPair);

        Hand lowPair = new Hand();
        lowPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        lowPair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        lowPair.addCard( deck.getCard(CardSuite.SPADES, CardValue.FIVE) );
        lowPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        lowPair.addCard( deck.getCard(CardSuite.SPADES, CardValue.TEN) );
        lowPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        lowPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.ACE) );
        BestHand.setBestHand(lowPair);

        assertTrue(highPair.compareTo(lowPair) > 0);
    }

    @org.junit.jupiter.api.Test
    void checkBug() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.add( deck.getCard(CardSuite.SPADES, CardValue.FIVE) );
        cards.add( deck.getCard(CardSuite.SPADES, CardValue.SIX) );
        cards.add( deck.getCard(CardSuite.SPADES, CardValue.FOUR) );
        cards.add( deck.getCard(CardSuite.HEARTS, CardValue.FIVE) );

        Hand p1 = new Hand();
        p1.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TWO) );
        p1.addCard( deck.getCard(CardSuite.SPADES, CardValue.THREE) );
        p1.addCardList(cards);
        BestHand.setBestHand(p1);

        Hand p2 = new Hand();
        p2.addCard( deck.getCard(CardSuite.SPADES, CardValue.ACE) );
        p2.addCard( deck.getCard(CardSuite.CLUBS, CardValue.FOUR) );
        p2.addCardList(cards);
        BestHand.setBestHand(p2);

        Hand p3 = new Hand();
        p3.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        p3.addCard( deck.getCard(CardSuite.CLUBS, CardValue.SEVEN) );
        p3.addCardList(cards);
        BestHand.setBestHand(p3);

        assertTrue(p2.compareTo(p1) > 0);
        assertTrue(p3.compareTo(p1) > 0);
        assertEquals(0, p2.compareTo(p3));
    }
}