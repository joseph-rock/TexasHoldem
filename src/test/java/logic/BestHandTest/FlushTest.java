package logic.BestHandTest;

import data.*;
import enums.PokerHand;
import enums.CardSuite;
import enums.CardValue;
import logic.BestHand;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlushTest {
    @org.junit.jupiter.api.Test
    void checkFlush() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.THREE) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SIX) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.EIGHT) );

        assertEquals(PokerHand.FLUSH, BestHand.bestPokerHand(cards));
        assertEquals("587643", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkFlushWithThreeOfAKind() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.THREE) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.SPADES, CardValue.FOUR) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.EIGHT) );

        assertEquals(PokerHand.FLUSH, BestHand.bestPokerHand(cards));
        assertEquals("587432", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkFlushWithStraightPresent() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.THREE) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.SPADES, CardValue.FIVE) );
        cards.add( new Card(CardSuite.SPADES, CardValue.SIX) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.EIGHT) );

        assertEquals(PokerHand.FLUSH, BestHand.bestPokerHand(cards));
        assertEquals("587432", BestHand.encode(cards));
    }
}
