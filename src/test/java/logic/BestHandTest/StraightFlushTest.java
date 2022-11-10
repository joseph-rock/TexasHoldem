package logic.BestHandTest;

import data.*;
import enums.PokerHand;
import enums.CardSuite;
import enums.CardValue;
import logic.BestHand;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StraightFlushTest {

    @org.junit.jupiter.api.Test
    void checkStraightFlush() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.THREE) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.FIVE) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SIX) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.EIGHT) );

        assertEquals(PokerHand.STRAIGHT_FLUSH, BestHand.bestPokerHand(cards));
        assertEquals("887654", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkStraightFlushWithHigherStraight() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.THREE) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.FIVE) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SIX) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.SEVEN) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.EIGHT) );

        assertEquals(PokerHand.STRAIGHT_FLUSH, BestHand.bestPokerHand(cards));
        assertEquals("865432", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkStraightFlushWithTwoPair() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.THREE) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.FIVE) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SIX) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.THREE) );

        assertEquals(PokerHand.STRAIGHT_FLUSH, BestHand.bestPokerHand(cards));
        assertEquals("865432", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkStraightFlushWithThreeOfAKind() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.SIX) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.EIGHT) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.NINE) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.TEN) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.EIGHT) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.EIGHT) );

        assertEquals(PokerHand.STRAIGHT_FLUSH, BestHand.bestPokerHand(cards));
        assertEquals("8a9876", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkRoyalFlush() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.ACE) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.KING) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.QUEEN) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.JACK) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.TEN) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.SEVEN) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.EIGHT) );

        assertEquals(PokerHand.ROYAL_FLUSH, BestHand.bestPokerHand(cards));
        assertEquals("8edcba", BestHand.encode(cards));
    }
}
