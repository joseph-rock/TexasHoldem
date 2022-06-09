package logic;

import data.*;

import java.util.ArrayList;

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
    }

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
    }
}