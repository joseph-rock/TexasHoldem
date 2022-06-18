package test.logic;

import data.*;
import enums.PokerHand;
import enums.CardSuite;
import enums.CardValue;
import data.Hand;
import logic.BestHand;

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
        BestHand.set(cards);
        assertEquals(PokerHand.STRAIGHT_FLUSH, cards.getHandType());
        assertEquals("87654", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.STRAIGHT_FLUSH, cards.getHandType());
        assertEquals("65432", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.STRAIGHT_FLUSH, cards.getHandType());
        assertEquals("a9876", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.ROYAL_FLUSH, cards.getHandType());
        assertEquals("edcba", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getHandType());
        assertEquals("87654", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getHandType());
        assertEquals("5432e", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getHandType());
        assertEquals("edcba", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getHandType());
        assertEquals("edcba", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getHandType());
        assertEquals("ba987", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.FLUSH, cards.getHandType());
        assertEquals("87643", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.FLUSH, cards.getHandType());
        assertEquals("87432", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.FLUSH, cards.getHandType());
        assertEquals("87432", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.PAIR, cards.getHandType());
        assertEquals("44976", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.TWO_PAIR, cards.getHandType());
        assertEquals("44229", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.TWO_PAIR, cards.getHandType());
        assertEquals("66449", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.THREE_OF_A_KIND, cards.getHandType());
        assertEquals("222a8", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.FULL_HOUSE, cards.getHandType());
        assertEquals("22255", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.FULL_HOUSE, cards.getHandType());
        assertEquals("55522", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.FULL_HOUSE, cards.getHandType());
        assertEquals("22266", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.FOUR_OF_A_KIND, cards.getHandType());
        assertEquals("2222a", cards.getCardsEncoded());
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
        BestHand.set(cards);
        assertEquals(PokerHand.HIGH_CARD, cards.getHandType());
        assertEquals("ea986", cards.getCardsEncoded());
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
        BestHand.set(highCard);

        Hand pair = new Hand();
        pair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        pair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.THREE) );
        pair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        pair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        pair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        pair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        pair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.NINE) );
        BestHand.set(pair);

        assertFalse(highCard.isBetterHand(pair));
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
        BestHand.set(highPair);

        Hand lowPair = new Hand();
        lowPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        lowPair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.THREE) );
        lowPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        lowPair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        lowPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        lowPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        lowPair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.NINE) );
        BestHand.set(lowPair);

        assertTrue(highPair.isBetterHand(lowPair));
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
        BestHand.set(highPair);

        Hand lowPair = new Hand();
        lowPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        lowPair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        lowPair.addCard( deck.getCard(CardSuite.SPADES, CardValue.FIVE) );
        lowPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        lowPair.addCard( deck.getCard(CardSuite.SPADES, CardValue.TEN) );
        lowPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        lowPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.ACE) );
        BestHand.set(lowPair);

        assertTrue(highPair.isBetterHand(lowPair));
    }

    @org.junit.jupiter.api.Test
    void checkBug() {
        Hand cards = new Hand();
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FIVE) );

        Hand p1 = new Hand();
        p1.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TWO) );
        p1.addCard( deck.getCard(CardSuite.SPADES, CardValue.THREE) );
        p1.addCollection(cards);
        BestHand.set(p1);
        p1.printCards();
        System.out.println(p1.getHandType());

        Hand p2 = new Hand();
        p2.addCard( deck.getCard(CardSuite.SPADES, CardValue.ACE) );
        p2.addCard( deck.getCard(CardSuite.CLUBS, CardValue.FOUR) );
        p2.addCollection(cards);
        BestHand.set(p2);
        p2.printCards();
        System.out.println(p2.getHandType());

        Hand p3 = new Hand();
        p3.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        p3.addCard( deck.getCard(CardSuite.CLUBS, CardValue.SEVEN) );
        p3.addCollection(cards);
        BestHand.set(p3);
        p3.printCards();

        assertTrue(p2.isBetterHand(p1));
        assertTrue(p3.isBetterHand(p1));
        assertTrue(p2.isDraw(p3));
    }
}