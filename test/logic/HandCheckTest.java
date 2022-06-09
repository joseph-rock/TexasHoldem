package logic;

import data.Card;
import data.Deck;
import data.Suite;
import data.Value;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HandCheckTest {
    Deck deck = new Deck();

    @org.junit.jupiter.api.Test
    void checkStraightFlush() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( deck.getCard(Suite.hearts, Value.two) );
        cards.add( deck.getCard(Suite.hearts, Value.three) );
        cards.add( deck.getCard(Suite.hearts, Value.four) );
        cards.add( deck.getCard(Suite.hearts, Value.five) );
        cards.add( deck.getCard(Suite.hearts, Value.six) );
        cards.add( deck.getCard(Suite.hearts, Value.seven) );
        cards.add( deck.getCard(Suite.hearts, Value.eight) );
        HandCheck.check(cards);
    }

    @org.junit.jupiter.api.Test
    void checkRoyalFlush() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( deck.getCard(Suite.hearts, Value.ace) );
        cards.add( deck.getCard(Suite.hearts, Value.king) );
        cards.add( deck.getCard(Suite.hearts, Value.queen) );
        cards.add( deck.getCard(Suite.hearts, Value.jack) );
        cards.add( deck.getCard(Suite.hearts, Value.ten) );
        cards.add( deck.getCard(Suite.diamonds, Value.seven) );
        cards.add( deck.getCard(Suite.diamonds, Value.eight) );
        HandCheck.check(cards);
    }

    @org.junit.jupiter.api.Test
    void checkLowStraight() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( deck.getCard(Suite.hearts, Value.two) );
        cards.add( deck.getCard(Suite.hearts, Value.three) );
        cards.add( deck.getCard(Suite.diamonds, Value.four) );
        cards.add( deck.getCard(Suite.diamonds, Value.five) );
        cards.add( deck.getCard(Suite.diamonds, Value.six) );
        cards.add( deck.getCard(Suite.hearts, Value.seven) );
        cards.add( deck.getCard(Suite.hearts, Value.eight) );
        HandCheck.check(cards);
    }

    @org.junit.jupiter.api.Test
    void checkLowestStraight() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( deck.getCard(Suite.hearts, Value.ace) );
        cards.add( deck.getCard(Suite.hearts, Value.two) );
        cards.add( deck.getCard(Suite.diamonds, Value.three) );
        cards.add( deck.getCard(Suite.diamonds, Value.four) );
        cards.add( deck.getCard(Suite.diamonds, Value.five) );
        cards.add( deck.getCard(Suite.hearts, Value.seven) );
        cards.add( deck.getCard(Suite.hearts, Value.eight) );
        HandCheck.check(cards);
    }

    @org.junit.jupiter.api.Test
    void checkHighestStraight() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( deck.getCard(Suite.hearts, Value.ace) );
        cards.add( deck.getCard(Suite.hearts, Value.king) );
        cards.add( deck.getCard(Suite.diamonds, Value.queen) );
        cards.add( deck.getCard(Suite.diamonds, Value.jack) );
        cards.add( deck.getCard(Suite.diamonds, Value.ten) );
        cards.add( deck.getCard(Suite.hearts, Value.seven) );
        cards.add( deck.getCard(Suite.hearts, Value.eight) );
        HandCheck.check(cards);
    }

    @org.junit.jupiter.api.Test
    void checkFlush() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( deck.getCard(Suite.hearts, Value.two) );
        cards.add( deck.getCard(Suite.hearts, Value.three) );
        cards.add( deck.getCard(Suite.hearts, Value.four) );
        cards.add( deck.getCard(Suite.diamonds, Value.four) );
        cards.add( deck.getCard(Suite.hearts, Value.six) );
        cards.add( deck.getCard(Suite.hearts, Value.seven) );
        cards.add( deck.getCard(Suite.hearts, Value.eight) );
        HandCheck.check(cards);
    }

    @org.junit.jupiter.api.Test
    void checkPair() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( deck.getCard(Suite.hearts, Value.two) );
        cards.add( deck.getCard(Suite.diamonds, Value.three) );
        cards.add( deck.getCard(Suite.hearts, Value.four) );
        cards.add( deck.getCard(Suite.diamonds, Value.four) );
        cards.add( deck.getCard(Suite.hearts, Value.six) );
        cards.add( deck.getCard(Suite.hearts, Value.seven) );
        cards.add( deck.getCard(Suite.diamonds, Value.nine) );
        HandCheck.check(cards);
    }

    @org.junit.jupiter.api.Test
    void checkTwoPair() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( deck.getCard(Suite.hearts, Value.two) );
        cards.add( deck.getCard(Suite.diamonds, Value.two) );
        cards.add( deck.getCard(Suite.hearts, Value.four) );
        cards.add( deck.getCard(Suite.diamonds, Value.four) );
        cards.add( deck.getCard(Suite.hearts, Value.six) );
        cards.add( deck.getCard(Suite.hearts, Value.seven) );
        cards.add( deck.getCard(Suite.diamonds, Value.nine) );
        HandCheck.check(cards);
    }

    @org.junit.jupiter.api.Test
    void checkThreeOfAKind() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( deck.getCard(Suite.hearts, Value.two) );
        cards.add( deck.getCard(Suite.diamonds, Value.two) );
        cards.add( deck.getCard(Suite.spades, Value.two) );
        cards.add( deck.getCard(Suite.diamonds, Value.five) );
        cards.add( deck.getCard(Suite.spades, Value.six) );
        cards.add( deck.getCard(Suite.clubs, Value.eight) );
        cards.add( deck.getCard(Suite.clubs, Value.ten) );
        HandCheck.check(cards);
    }
}