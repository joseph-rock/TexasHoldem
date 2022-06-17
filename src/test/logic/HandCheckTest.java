package test.logic;

import data.*;
import data.enums.PokerHand;
import data.enums.Suite;
import data.enums.Value;
import logic.CardCollection;
import logic.HandCheck;

import static org.junit.jupiter.api.Assertions.*;

class HandCheckTest {
    Deck deck = new Deck();

    @org.junit.jupiter.api.Test
    void checkStraightFlush() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.THREE) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.FIVE) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SIX) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SEVEN) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT_FLUSH, cards.getHand());
        assertEquals("87654", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkStraightFlushWithTwoPair() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.THREE) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.FIVE) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SIX) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.CLUBS, Value.THREE) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT_FLUSH, cards.getHand());
        assertEquals("65432", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkStraightFlushWithThreeOfAKind() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SIX) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SEVEN) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.EIGHT) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.NINE) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TEN) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.EIGHT) );
        cards.addCard( deck.getCard(Suite.CLUBS, Value.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT_FLUSH, cards.getHand());
        assertEquals("a9876", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkRoyalFlush() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.ACE) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.KING) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.QUEEN) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.JACK) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TEN) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.SEVEN) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT_FLUSH, cards.getHand());
        assertEquals("edcba", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkLowStraight() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.THREE) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.FIVE) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.SIX) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SEVEN) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getHand());
        assertEquals("87654", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkLowestStraight() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.ACE) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.THREE) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.FIVE) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SEVEN) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getHand());
        assertEquals("5432e", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkHighestStraight() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.ACE) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.KING) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.QUEEN) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.JACK) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.TEN) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SEVEN) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getHand());
        assertEquals("edcba", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkStraightWithThreeOfAKind() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.ACE) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.KING) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.QUEEN) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.JACK) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.TEN) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TEN) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.TEN) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getHand());
        assertEquals("edcba", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkStraightWithTwoPair() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SEVEN) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.SEVEN) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.EIGHT) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.NINE) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.TEN) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.JACK) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.JACK) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getHand());
        assertEquals("ba987", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkFlush() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.THREE) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SIX) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SEVEN) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.FLUSH, cards.getHand());
        assertEquals("87643", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkFlushWithThreeOfAKind() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.THREE) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SEVEN) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.FLUSH, cards.getHand());
        assertEquals("87432", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkFlushWithStraightPresent() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.THREE) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.FIVE) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.SIX) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SEVEN) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.FLUSH, cards.getHand());
        assertEquals("87432", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkPair() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.THREE) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SIX) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SEVEN) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.NINE) );
        HandCheck.check(cards);
        assertEquals(PokerHand.PAIR, cards.getHand());
        assertEquals("44976", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkTwoPair() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SIX) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SEVEN) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.NINE) );
        HandCheck.check(cards);
        assertEquals(PokerHand.TWO_PAIR, cards.getHand());
        assertEquals("44229", cards.getEncodedCards());
    }

    // Fuck
    @org.junit.jupiter.api.Test
    void checkThreePairResultsInTwoPair() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.SIX) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.SIX) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.NINE) );
        HandCheck.check(cards);
        assertEquals(PokerHand.TWO_PAIR, cards.getHand());
        assertEquals("66449", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkThreeOfAKind() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.TWO) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.FIVE) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.SIX) );
        cards.addCard( deck.getCard(Suite.CLUBS, Value.EIGHT) );
        cards.addCard( deck.getCard(Suite.CLUBS, Value.TEN) );
        HandCheck.check(cards);
        assertEquals(PokerHand.THREE_OF_A_KIND, cards.getHand());
        assertEquals("222a8", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkFullHouse() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.TWO) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.FIVE) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.FIVE) );
        cards.addCard( deck.getCard(Suite.CLUBS, Value.EIGHT) );
        cards.addCard( deck.getCard(Suite.CLUBS, Value.TEN) );
        HandCheck.check(cards);
        assertEquals(PokerHand.FULL_HOUSE, cards.getHand());
        assertEquals("22255", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkReallyFullHouse() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.TWO) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.FIVE) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.FIVE) );
        cards.addCard( deck.getCard(Suite.CLUBS, Value.FIVE) );
        cards.addCard( deck.getCard(Suite.CLUBS, Value.TEN) );
        HandCheck.check(cards);
        assertEquals(PokerHand.FULL_HOUSE, cards.getHand());
        assertEquals("55522", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkInvertedReallyFullHouse() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.TWO) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.FIVE) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.FIVE) );
        cards.addCard( deck.getCard(Suite.CLUBS, Value.SIX) );
        cards.addCard( deck.getCard(Suite.CLUBS, Value.SIX) );
        HandCheck.check(cards);
        assertEquals(PokerHand.FULL_HOUSE, cards.getHand());
        assertEquals("22266", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkFourOfAKind() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.TWO) );
        cards.addCard( deck.getCard(Suite.CLUBS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.FIVE) );
        cards.addCard( deck.getCard(Suite.CLUBS, Value.EIGHT) );
        cards.addCard( deck.getCard(Suite.CLUBS, Value.TEN) );
        HandCheck.check(cards);
        assertEquals(PokerHand.FOUR_OF_A_KIND, cards.getHand());
        assertEquals("2222a", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkHighCard() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.SIX) );
        cards.addCard( deck.getCard(Suite.CLUBS, Value.EIGHT) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.NINE) );
        cards.addCard( deck.getCard(Suite.CLUBS, Value.TEN) );
        cards.addCard( deck.getCard(Suite.CLUBS, Value.ACE) );
        HandCheck.check(cards);
        assertEquals(PokerHand.HIGH_CARD, cards.getHand());
        assertEquals("ea986", cards.getEncodedCards());
    }

    @org.junit.jupiter.api.Test
    void checkPairBeatsHighCard() {
        CardCollection highCard = new CardCollection();
        highCard.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        highCard.addCard( deck.getCard(Suite.DIAMONDS, Value.FOUR) );
        highCard.addCard( deck.getCard(Suite.SPADES, Value.SIX) );
        highCard.addCard( deck.getCard(Suite.CLUBS, Value.EIGHT) );
        highCard.addCard( deck.getCard(Suite.SPADES, Value.NINE) );
        highCard.addCard( deck.getCard(Suite.CLUBS, Value.TEN) );
        highCard.addCard( deck.getCard(Suite.CLUBS, Value.ACE) );
        HandCheck.check(highCard);

        CardCollection pair = new CardCollection();
        pair.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        pair.addCard( deck.getCard(Suite.DIAMONDS, Value.THREE) );
        pair.addCard( deck.getCard(Suite.HEARTS, Value.FOUR) );
        pair.addCard( deck.getCard(Suite.DIAMONDS, Value.FOUR) );
        pair.addCard( deck.getCard(Suite.HEARTS, Value.SIX) );
        pair.addCard( deck.getCard(Suite.HEARTS, Value.SEVEN) );
        pair.addCard( deck.getCard(Suite.DIAMONDS, Value.NINE) );
        HandCheck.check(pair);

        assertFalse(highCard.isBetterHand(pair));
    }

    @org.junit.jupiter.api.Test
    void checkHigherPairWins() {
        CardCollection highPair = new CardCollection();
        highPair.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        highPair.addCard( deck.getCard(Suite.DIAMONDS, Value.FOUR) );
        highPair.addCard( deck.getCard(Suite.SPADES, Value.SIX) );
        highPair.addCard( deck.getCard(Suite.CLUBS, Value.EIGHT) );
        highPair.addCard( deck.getCard(Suite.SPADES, Value.TEN) );
        highPair.addCard( deck.getCard(Suite.CLUBS, Value.TEN) );
        highPair.addCard( deck.getCard(Suite.CLUBS, Value.ACE) );
        HandCheck.check(highPair);

        CardCollection lowPair = new CardCollection();
        lowPair.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        lowPair.addCard( deck.getCard(Suite.DIAMONDS, Value.THREE) );
        lowPair.addCard( deck.getCard(Suite.HEARTS, Value.FOUR) );
        lowPair.addCard( deck.getCard(Suite.DIAMONDS, Value.FOUR) );
        lowPair.addCard( deck.getCard(Suite.HEARTS, Value.SIX) );
        lowPair.addCard( deck.getCard(Suite.HEARTS, Value.SEVEN) );
        lowPair.addCard( deck.getCard(Suite.DIAMONDS, Value.NINE) );
        HandCheck.check(lowPair);

        assertTrue(highPair.isBetterHand(lowPair));
    }

    @org.junit.jupiter.api.Test
    void checkEqualPairHighKickerWins() {
        CardCollection highPair = new CardCollection();
        highPair.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        highPair.addCard( deck.getCard(Suite.DIAMONDS, Value.FOUR) );
        highPair.addCard( deck.getCard(Suite.SPADES, Value.SIX) );
        highPair.addCard( deck.getCard(Suite.CLUBS, Value.EIGHT) );
        highPair.addCard( deck.getCard(Suite.SPADES, Value.TEN) );
        highPair.addCard( deck.getCard(Suite.CLUBS, Value.TEN) );
        highPair.addCard( deck.getCard(Suite.CLUBS, Value.ACE) );
        HandCheck.check(highPair);

        CardCollection lowPair = new CardCollection();
        lowPair.addCard( deck.getCard(Suite.HEARTS, Value.TWO) );
        lowPair.addCard( deck.getCard(Suite.DIAMONDS, Value.FOUR) );
        lowPair.addCard( deck.getCard(Suite.SPADES, Value.FIVE) );
        lowPair.addCard( deck.getCard(Suite.CLUBS, Value.EIGHT) );
        lowPair.addCard( deck.getCard(Suite.SPADES, Value.TEN) );
        lowPair.addCard( deck.getCard(Suite.CLUBS, Value.TEN) );
        lowPair.addCard( deck.getCard(Suite.CLUBS, Value.ACE) );
        HandCheck.check(lowPair);

        assertTrue(highPair.isBetterHand(lowPair));
    }

    @org.junit.jupiter.api.Test
    void checkBug() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.DIAMONDS, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.FIVE) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.SIX) );
        cards.addCard( deck.getCard(Suite.SPADES, Value.FOUR) );
        cards.addCard( deck.getCard(Suite.HEARTS, Value.FIVE) );

        CardCollection p1 = new CardCollection();
        p1.addCard( deck.getCard(Suite.CLUBS, Value.TWO) );
        p1.addCard( deck.getCard(Suite.SPADES, Value.THREE) );
        p1.addCollection(cards);
        HandCheck.check(p1);
        p1.printCards();
        System.out.println(p1.getHand());

        CardCollection p2 = new CardCollection();
        p2.addCard( deck.getCard(Suite.SPADES, Value.ACE) );
        p2.addCard( deck.getCard(Suite.CLUBS, Value.FOUR) );
        p2.addCollection(cards);
        HandCheck.check(p2);
        p2.printCards();
        System.out.println(p2.getHand());

        CardCollection p3 = new CardCollection();
        p3.addCard( deck.getCard(Suite.HEARTS, Value.FOUR) );
        p3.addCard( deck.getCard(Suite.CLUBS, Value.SEVEN) );
        p3.addCollection(cards);
        HandCheck.check(p3);
        p3.printCards();

        assertTrue(p2.isBetterHand(p1));
        assertTrue(p3.isBetterHand(p1));
        assertTrue(p2.isDraw(p3));
    }
}