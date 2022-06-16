package test.logic;

import data.*;
import logic.CardCollection;
import logic.HandCheck;

import static org.junit.jupiter.api.Assertions.*;

class HandCheckTest {
    Deck deck = new Deck();

    @org.junit.jupiter.api.Test
    void checkStraightFlush() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.two) );
        cards.addCard( deck.getCard(Suite.hearts, Value.three) );
        cards.addCard( deck.getCard(Suite.hearts, Value.four) );
        cards.addCard( deck.getCard(Suite.hearts, Value.five) );
        cards.addCard( deck.getCard(Suite.hearts, Value.six) );
        cards.addCard( deck.getCard(Suite.hearts, Value.seven) );
        cards.addCard( deck.getCard(Suite.hearts, Value.eight) );
        HandCheck.check(cards);
        assertEquals(8, cards.getHandScore());
        assertEquals("87654", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkStraightFlushWithTwoPair() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.two) );
        cards.addCard( deck.getCard(Suite.hearts, Value.three) );
        cards.addCard( deck.getCard(Suite.hearts, Value.four) );
        cards.addCard( deck.getCard(Suite.hearts, Value.five) );
        cards.addCard( deck.getCard(Suite.hearts, Value.six) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.two) );
        cards.addCard( deck.getCard(Suite.clubs, Value.three) );
        HandCheck.check(cards);
        assertEquals(8, cards.getHandScore());
        assertEquals("65432", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkStraightFlushWithThreeOfAKind() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.six) );
        cards.addCard( deck.getCard(Suite.hearts, Value.seven) );
        cards.addCard( deck.getCard(Suite.hearts, Value.eight) );
        cards.addCard( deck.getCard(Suite.hearts, Value.nine) );
        cards.addCard( deck.getCard(Suite.hearts, Value.ten) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.eight) );
        cards.addCard( deck.getCard(Suite.clubs, Value.eight) );
        HandCheck.check(cards);
        assertEquals(8, cards.getHandScore());
        assertEquals("a9876", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkRoyalFlush() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.ace) );
        cards.addCard( deck.getCard(Suite.hearts, Value.king) );
        cards.addCard( deck.getCard(Suite.hearts, Value.queen) );
        cards.addCard( deck.getCard(Suite.hearts, Value.jack) );
        cards.addCard( deck.getCard(Suite.hearts, Value.ten) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.seven) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.eight) );
        HandCheck.check(cards);
        assertEquals(8, cards.getHandScore());
        assertEquals("edcba", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkLowStraight() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.two) );
        cards.addCard( deck.getCard(Suite.hearts, Value.three) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.four) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.five) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.six) );
        cards.addCard( deck.getCard(Suite.hearts, Value.seven) );
        cards.addCard( deck.getCard(Suite.hearts, Value.eight) );
        HandCheck.check(cards);
        assertEquals(4, cards.getHandScore());
        assertEquals("87654", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkLowestStraight() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.ace) );
        cards.addCard( deck.getCard(Suite.hearts, Value.two) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.three) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.four) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.five) );
        cards.addCard( deck.getCard(Suite.hearts, Value.seven) );
        cards.addCard( deck.getCard(Suite.hearts, Value.eight) );
        HandCheck.check(cards);
        assertEquals(4, cards.getHandScore());
        assertEquals("5432e", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkHighestStraight() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.ace) );
        cards.addCard( deck.getCard(Suite.hearts, Value.king) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.queen) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.jack) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.ten) );
        cards.addCard( deck.getCard(Suite.hearts, Value.seven) );
        cards.addCard( deck.getCard(Suite.hearts, Value.eight) );
        HandCheck.check(cards);
        assertEquals(4, cards.getHandScore());
        assertEquals("edcba", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkStraightWithThreeOfAKind() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.ace) );
        cards.addCard( deck.getCard(Suite.hearts, Value.king) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.queen) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.jack) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.ten) );
        cards.addCard( deck.getCard(Suite.hearts, Value.ten) );
        cards.addCard( deck.getCard(Suite.spades, Value.ten) );
        HandCheck.check(cards);
        assertEquals(4, cards.getHandScore());
        assertEquals("edcba", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkStraightWithTwoPair() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.seven) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.seven) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.eight) );
        cards.addCard( deck.getCard(Suite.spades, Value.nine) );
        cards.addCard( deck.getCard(Suite.spades, Value.ten) );
        cards.addCard( deck.getCard(Suite.hearts, Value.jack) );
        cards.addCard( deck.getCard(Suite.spades, Value.jack) );
        HandCheck.check(cards);
        assertEquals(4, cards.getHandScore());
        assertEquals("ba987", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkFlush() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.two) );
        cards.addCard( deck.getCard(Suite.hearts, Value.three) );
        cards.addCard( deck.getCard(Suite.hearts, Value.four) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.four) );
        cards.addCard( deck.getCard(Suite.hearts, Value.six) );
        cards.addCard( deck.getCard(Suite.hearts, Value.seven) );
        cards.addCard( deck.getCard(Suite.hearts, Value.eight) );
        HandCheck.check(cards);
        assertEquals(5, cards.getHandScore());
        assertEquals("87643", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkFlushWithThreeOfAKind() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.two) );
        cards.addCard( deck.getCard(Suite.hearts, Value.three) );
        cards.addCard( deck.getCard(Suite.hearts, Value.four) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.four) );
        cards.addCard( deck.getCard(Suite.spades, Value.four) );
        cards.addCard( deck.getCard(Suite.hearts, Value.seven) );
        cards.addCard( deck.getCard(Suite.hearts, Value.eight) );
        HandCheck.check(cards);
        assertEquals(5, cards.getHandScore());
        assertEquals("87432", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkFlushWithStraightPresent() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.two) );
        cards.addCard( deck.getCard(Suite.hearts, Value.three) );
        cards.addCard( deck.getCard(Suite.hearts, Value.four) );
        cards.addCard( deck.getCard(Suite.spades, Value.five) );
        cards.addCard( deck.getCard(Suite.spades, Value.six) );
        cards.addCard( deck.getCard(Suite.hearts, Value.seven) );
        cards.addCard( deck.getCard(Suite.hearts, Value.eight) );
        HandCheck.check(cards);
        assertEquals(5, cards.getHandScore());
        assertEquals("87432", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkPair() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.two) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.three) );
        cards.addCard( deck.getCard(Suite.hearts, Value.four) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.four) );
        cards.addCard( deck.getCard(Suite.hearts, Value.six) );
        cards.addCard( deck.getCard(Suite.hearts, Value.seven) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.nine) );
        HandCheck.check(cards);
        assertEquals(1, cards.getHandScore());
        assertEquals("44976", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkTwoPair() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.two) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.two) );
        cards.addCard( deck.getCard(Suite.hearts, Value.four) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.four) );
        cards.addCard( deck.getCard(Suite.hearts, Value.six) );
        cards.addCard( deck.getCard(Suite.hearts, Value.seven) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.nine) );
        HandCheck.check(cards);
        assertEquals(2, cards.getHandScore());
        assertEquals("44229", cards.getHandTieBreaker());
    }

    // Fuck
    @org.junit.jupiter.api.Test
    void checkThreePairResultsInTwoPair() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.two) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.two) );
        cards.addCard( deck.getCard(Suite.hearts, Value.four) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.four) );
        cards.addCard( deck.getCard(Suite.hearts, Value.six) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.six) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.nine) );
        HandCheck.check(cards);
        assertEquals(2, cards.getHandScore());
        assertEquals("66449", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkThreeOfAKind() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.two) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.two) );
        cards.addCard( deck.getCard(Suite.spades, Value.two) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.five) );
        cards.addCard( deck.getCard(Suite.spades, Value.six) );
        cards.addCard( deck.getCard(Suite.clubs, Value.eight) );
        cards.addCard( deck.getCard(Suite.clubs, Value.ten) );
        HandCheck.check(cards);
        assertEquals(3, cards.getHandScore());
        assertEquals("222a8", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkFullHouse() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.two) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.two) );
        cards.addCard( deck.getCard(Suite.spades, Value.two) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.five) );
        cards.addCard( deck.getCard(Suite.spades, Value.five) );
        cards.addCard( deck.getCard(Suite.clubs, Value.eight) );
        cards.addCard( deck.getCard(Suite.clubs, Value.ten) );
        HandCheck.check(cards);
        assertEquals(6, cards.getHandScore());
        assertEquals("22255", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkReallyFullHouse() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.two) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.two) );
        cards.addCard( deck.getCard(Suite.spades, Value.two) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.five) );
        cards.addCard( deck.getCard(Suite.spades, Value.five) );
        cards.addCard( deck.getCard(Suite.clubs, Value.five) );
        cards.addCard( deck.getCard(Suite.clubs, Value.ten) );
        HandCheck.check(cards);
        assertEquals(6, cards.getHandScore());
        assertEquals("55522", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkInvertedReallyFullHouse() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.two) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.two) );
        cards.addCard( deck.getCard(Suite.spades, Value.two) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.five) );
        cards.addCard( deck.getCard(Suite.spades, Value.five) );
        cards.addCard( deck.getCard(Suite.clubs, Value.six) );
        cards.addCard( deck.getCard(Suite.clubs, Value.six) );
        HandCheck.check(cards);
        assertEquals(6, cards.getHandScore());
        assertEquals("22266", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkFourOfAKind() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.two) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.two) );
        cards.addCard( deck.getCard(Suite.spades, Value.two) );
        cards.addCard( deck.getCard(Suite.clubs, Value.two) );
        cards.addCard( deck.getCard(Suite.spades, Value.five) );
        cards.addCard( deck.getCard(Suite.clubs, Value.eight) );
        cards.addCard( deck.getCard(Suite.clubs, Value.ten) );
        HandCheck.check(cards);
        assertEquals(7, cards.getHandScore());
        assertEquals("2222a", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkHighCard() {
        CardCollection cards = new CardCollection();
        cards.addCard( deck.getCard(Suite.hearts, Value.two) );
        cards.addCard( deck.getCard(Suite.diamonds, Value.four) );
        cards.addCard( deck.getCard(Suite.spades, Value.six) );
        cards.addCard( deck.getCard(Suite.clubs, Value.eight) );
        cards.addCard( deck.getCard(Suite.spades, Value.nine) );
        cards.addCard( deck.getCard(Suite.clubs, Value.ten) );
        cards.addCard( deck.getCard(Suite.clubs, Value.ace) );
        HandCheck.check(cards);
        assertEquals(0, cards.getHandScore());
        assertEquals("ea986", cards.getHandTieBreaker());
    }

    @org.junit.jupiter.api.Test
    void checkPairBeatsHighCard() {
        CardCollection highCard = new CardCollection();
        highCard.addCard( deck.getCard(Suite.hearts, Value.two) );
        highCard.addCard( deck.getCard(Suite.diamonds, Value.four) );
        highCard.addCard( deck.getCard(Suite.spades, Value.six) );
        highCard.addCard( deck.getCard(Suite.clubs, Value.eight) );
        highCard.addCard( deck.getCard(Suite.spades, Value.nine) );
        highCard.addCard( deck.getCard(Suite.clubs, Value.ten) );
        highCard.addCard( deck.getCard(Suite.clubs, Value.ace) );
        HandCheck.check(highCard);

        CardCollection pair = new CardCollection();
        pair.addCard( deck.getCard(Suite.hearts, Value.two) );
        pair.addCard( deck.getCard(Suite.diamonds, Value.three) );
        pair.addCard( deck.getCard(Suite.hearts, Value.four) );
        pair.addCard( deck.getCard(Suite.diamonds, Value.four) );
        pair.addCard( deck.getCard(Suite.hearts, Value.six) );
        pair.addCard( deck.getCard(Suite.hearts, Value.seven) );
        pair.addCard( deck.getCard(Suite.diamonds, Value.nine) );
        HandCheck.check(pair);

        assertFalse(highCard.isBetterHand(pair));
    }

    @org.junit.jupiter.api.Test
    void checkHigherPairWins() {
        CardCollection highPair = new CardCollection();
        highPair.addCard( deck.getCard(Suite.hearts, Value.two) );
        highPair.addCard( deck.getCard(Suite.diamonds, Value.four) );
        highPair.addCard( deck.getCard(Suite.spades, Value.six) );
        highPair.addCard( deck.getCard(Suite.clubs, Value.eight) );
        highPair.addCard( deck.getCard(Suite.spades, Value.ten) );
        highPair.addCard( deck.getCard(Suite.clubs, Value.ten) );
        highPair.addCard( deck.getCard(Suite.clubs, Value.ace) );
        HandCheck.check(highPair);

        CardCollection lowPair = new CardCollection();
        lowPair.addCard( deck.getCard(Suite.hearts, Value.two) );
        lowPair.addCard( deck.getCard(Suite.diamonds, Value.three) );
        lowPair.addCard( deck.getCard(Suite.hearts, Value.four) );
        lowPair.addCard( deck.getCard(Suite.diamonds, Value.four) );
        lowPair.addCard( deck.getCard(Suite.hearts, Value.six) );
        lowPair.addCard( deck.getCard(Suite.hearts, Value.seven) );
        lowPair.addCard( deck.getCard(Suite.diamonds, Value.nine) );
        HandCheck.check(lowPair);

        assertTrue(highPair.isBetterHand(lowPair));
    }

    @org.junit.jupiter.api.Test
    void checkEqualPairHighKickerWins() {
        CardCollection highPair = new CardCollection();
        highPair.addCard( deck.getCard(Suite.hearts, Value.two) );
        highPair.addCard( deck.getCard(Suite.diamonds, Value.four) );
        highPair.addCard( deck.getCard(Suite.spades, Value.six) );
        highPair.addCard( deck.getCard(Suite.clubs, Value.eight) );
        highPair.addCard( deck.getCard(Suite.spades, Value.ten) );
        highPair.addCard( deck.getCard(Suite.clubs, Value.ten) );
        highPair.addCard( deck.getCard(Suite.clubs, Value.ace) );
        HandCheck.check(highPair);

        CardCollection lowPair = new CardCollection();
        lowPair.addCard( deck.getCard(Suite.hearts, Value.two) );
        lowPair.addCard( deck.getCard(Suite.diamonds, Value.four) );
        lowPair.addCard( deck.getCard(Suite.spades, Value.five) );
        lowPair.addCard( deck.getCard(Suite.clubs, Value.eight) );
        lowPair.addCard( deck.getCard(Suite.spades, Value.ten) );
        lowPair.addCard( deck.getCard(Suite.clubs, Value.ten) );
        lowPair.addCard( deck.getCard(Suite.clubs, Value.ace) );
        HandCheck.check(lowPair);

        assertTrue(highPair.isBetterHand(lowPair));
    }
}