package logic.BestHandTest;

import data.*;
import enums.PokerHand;
import enums.CardSuite;
import enums.CardValue;
import logic.BestHand;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StraightTest {
    @org.junit.jupiter.api.Test
    void checkLowStraight() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.THREE) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.SIX) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.EIGHT) );

        assertEquals(PokerHand.STRAIGHT, BestHand.bestPokerHand(cards));
        assertEquals("487654", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkLowestStraight() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.ACE) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.THREE) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.EIGHT) );

        assertEquals(PokerHand.STRAIGHT, BestHand.bestPokerHand(cards));
        assertEquals("45432e", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkHighestStraight() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.ACE) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.KING) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.QUEEN) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.JACK) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.TEN) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.EIGHT) );

        assertEquals(PokerHand.STRAIGHT, BestHand.bestPokerHand(cards));
        assertEquals("4edcba", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkStraightWithThreeOfAKind() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.ACE) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.KING) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.QUEEN) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.JACK) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.TEN) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.ACE) );
        cards.add( new Card(CardSuite.SPADES, CardValue.ACE) );

        assertEquals(PokerHand.STRAIGHT, BestHand.bestPokerHand(cards));
        assertEquals("4edcba", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkStraightWithTwoPair() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.SEVEN) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.EIGHT) );
        cards.add( new Card(CardSuite.SPADES, CardValue.NINE) );
        cards.add( new Card(CardSuite.SPADES, CardValue.TEN) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.NINE) );
        cards.add( new Card(CardSuite.SPADES, CardValue.JACK) );

        assertEquals(PokerHand.STRAIGHT, BestHand.bestPokerHand(cards));
        assertEquals("4ba987", BestHand.encode(cards));
    }
}
