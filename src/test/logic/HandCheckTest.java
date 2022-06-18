package test.logic;

import data.*;
import enums.PokerHand;
import enums.CardSuite;
import enums.CardValue;
import logic.CardCollection;
import logic.HandCheck;

import static org.junit.jupiter.api.Assertions.*;

class HandCheckTest {
    Deck deck = new Deck();

    @org.junit.jupiter.api.Test
    void checkStraightFlush() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.THREE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT_FLUSH, cards.getHandType());
        assertEquals("87654", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkStraightFlushWithTwoPair() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.THREE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.THREE) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT_FLUSH, cards.getHandType());
        assertEquals("65432", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkStraightFlushWithThreeOfAKind() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.EIGHT) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.NINE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.EIGHT) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT_FLUSH, cards.getHandType());
        assertEquals("a9876", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkRoyalFlush() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.ACE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.KING) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.QUEEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.JACK) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.ROYAL_FLUSH, cards.getHandType());
        assertEquals("edcba", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkLowStraight() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.THREE) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getHandType());
        assertEquals("87654", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkLowestStraight() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.ACE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.THREE) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getHandType());
        assertEquals("5432e", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkHighestStraight() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.ACE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.KING) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.QUEEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.JACK) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getHandType());
        assertEquals("edcba", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkStraightWithThreeOfAKind() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.ACE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.KING) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.QUEEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.JACK) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TEN) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.TEN) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getHandType());
        assertEquals("edcba", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkStraightWithTwoPair() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.EIGHT) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.NINE) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.TEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.JACK) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.JACK) );
        HandCheck.check(cards);
        assertEquals(PokerHand.STRAIGHT, cards.getHandType());
        assertEquals("ba987", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkFlush() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.THREE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.FLUSH, cards.getHandType());
        assertEquals("87643", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkFlushWithThreeOfAKind() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.THREE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.FLUSH, cards.getHandType());
        assertEquals("87432", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkFlushWithStraightPresent() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.THREE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.EIGHT) );
        HandCheck.check(cards);
        assertEquals(PokerHand.FLUSH, cards.getHandType());
        assertEquals("87432", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkPair() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.THREE) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.NINE) );
        HandCheck.check(cards);
        assertEquals(PokerHand.PAIR, cards.getHandType());
        assertEquals("44976", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkTwoPair() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.NINE) );
        HandCheck.check(cards);
        assertEquals(PokerHand.TWO_PAIR, cards.getHandType());
        assertEquals("44229", cards.getCardsEncoded());
    }

    // Fuck
    @org.junit.jupiter.api.Test
    void checkThreePairResultsInTwoPair() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.NINE) );
        HandCheck.check(cards);
        assertEquals(PokerHand.TWO_PAIR, cards.getHandType());
        assertEquals("66449", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkThreeOfAKind() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        HandCheck.check(cards);
        assertEquals(PokerHand.THREE_OF_A_KIND, cards.getHandType());
        assertEquals("222a8", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkFullHouse() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        HandCheck.check(cards);
        assertEquals(PokerHand.FULL_HOUSE, cards.getHandType());
        assertEquals("22255", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkReallyFullHouse() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        HandCheck.check(cards);
        assertEquals(PokerHand.FULL_HOUSE, cards.getHandType());
        assertEquals("55522", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkInvertedReallyFullHouse() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.SIX) );
        HandCheck.check(cards);
        assertEquals(PokerHand.FULL_HOUSE, cards.getHandType());
        assertEquals("22266", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkFourOfAKind() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        HandCheck.check(cards);
        assertEquals(PokerHand.FOUR_OF_A_KIND, cards.getHandType());
        assertEquals("2222a", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkHighCard() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.NINE) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        cards.addCard( deck.getCard(CardSuite.CLUBS, CardValue.ACE) );
        HandCheck.check(cards);
        assertEquals(PokerHand.HIGH_CARD, cards.getHandType());
        assertEquals("ea986", cards.getCardsEncoded());
    }

    @org.junit.jupiter.api.Test
    void checkPairBeatsHighCard() {
        CardCollection highCard = new CardCollection();
        highCard.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        highCard.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        highCard.addCard( deck.getCard(CardSuite.SPADES, CardValue.SIX) );
        highCard.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        highCard.addCard( deck.getCard(CardSuite.SPADES, CardValue.NINE) );
        highCard.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        highCard.addCard( deck.getCard(CardSuite.CLUBS, CardValue.ACE) );
        HandCheck.check(highCard);

        CardCollection pair = new CardCollection();
        pair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        pair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.THREE) );
        pair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        pair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        pair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        pair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        pair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.NINE) );
        HandCheck.check(pair);

        assertFalse(highCard.isBetterHand(pair));
    }

    @org.junit.jupiter.api.Test
    void checkHigherPairWins() {
        CardCollection highPair = new CardCollection();
        highPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        highPair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        highPair.addCard( deck.getCard(CardSuite.SPADES, CardValue.SIX) );
        highPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        highPair.addCard( deck.getCard(CardSuite.SPADES, CardValue.TEN) );
        highPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        highPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.ACE) );
        HandCheck.check(highPair);

        CardCollection lowPair = new CardCollection();
        lowPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        lowPair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.THREE) );
        lowPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        lowPair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        lowPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SIX) );
        lowPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.SEVEN) );
        lowPair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.NINE) );
        HandCheck.check(lowPair);

        assertTrue(highPair.isBetterHand(lowPair));
    }

    @org.junit.jupiter.api.Test
    void checkEqualPairHighKickerWins() {
        CardCollection highPair = new CardCollection();
        highPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        highPair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        highPair.addCard( deck.getCard(CardSuite.SPADES, CardValue.SIX) );
        highPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        highPair.addCard( deck.getCard(CardSuite.SPADES, CardValue.TEN) );
        highPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        highPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.ACE) );
        HandCheck.check(highPair);

        CardCollection lowPair = new CardCollection();
        lowPair.addCard( deck.getCard(CardSuite.HEARTS, CardValue.TWO) );
        lowPair.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        lowPair.addCard( deck.getCard(CardSuite.SPADES, CardValue.FIVE) );
        lowPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.EIGHT) );
        lowPair.addCard( deck.getCard(CardSuite.SPADES, CardValue.TEN) );
        lowPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TEN) );
        lowPair.addCard( deck.getCard(CardSuite.CLUBS, CardValue.ACE) );
        HandCheck.check(lowPair);

        assertTrue(highPair.isBetterHand(lowPair));
    }

    @org.junit.jupiter.api.Test
    void checkBug() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.FIVE) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.SIX) );
        cards.addCard( deck.getCard(CardSuite.SPADES, CardValue.FOUR) );
        cards.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FIVE) );

        CardCollection p1 = new CardCollection();
        p1.addCard( deck.getCard(CardSuite.CLUBS, CardValue.TWO) );
        p1.addCard( deck.getCard(CardSuite.SPADES, CardValue.THREE) );
        p1.addCollection(cards);
        HandCheck.check(p1);
        p1.printCards();
        System.out.println(p1.getHandType());

        CardCollection p2 = new CardCollection();
        p2.addCard( deck.getCard(CardSuite.SPADES, CardValue.ACE) );
        p2.addCard( deck.getCard(CardSuite.CLUBS, CardValue.FOUR) );
        p2.addCollection(cards);
        HandCheck.check(p2);
        p2.printCards();
        System.out.println(p2.getHandType());

        CardCollection p3 = new CardCollection();
        p3.addCard( deck.getCard(CardSuite.HEARTS, CardValue.FOUR) );
        p3.addCard( deck.getCard(CardSuite.CLUBS, CardValue.SEVEN) );
        p3.addCollection(cards);
        HandCheck.check(p3);
        p3.printCards();

        assertTrue(p2.isBetterHand(p1));
        assertTrue(p3.isBetterHand(p1));
        assertTrue(p2.isDraw(p3));
    }
}