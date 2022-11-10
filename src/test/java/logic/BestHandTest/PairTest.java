package logic.BestHandTest;

import data.*;
import enums.PokerHand;
import enums.CardSuite;
import enums.CardValue;
import logic.BestHand;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {

    @org.junit.jupiter.api.Test
    void checkPair() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.THREE) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SIX) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.NINE) );
        
        assertEquals(PokerHand.PAIR, BestHand.bestPokerHand(cards));
        assertEquals("144976", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkTwoPair() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SIX) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SEVEN) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.NINE) );
        assertEquals(PokerHand.TWO_PAIR, BestHand.bestPokerHand(cards));
        assertEquals("244229", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkThreePairResultsInTwoPair() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.SIX) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.SIX) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.NINE) );
        
        assertEquals(PokerHand.TWO_PAIR, BestHand.bestPokerHand(cards));
        assertEquals("266449", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkThreeOfAKind() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.add( new Card(CardSuite.SPADES, CardValue.TWO) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.add( new Card(CardSuite.SPADES, CardValue.SIX) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.EIGHT) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.TEN) );
        
        assertEquals(PokerHand.THREE_OF_A_KIND, BestHand.bestPokerHand(cards));
        assertEquals("3222a8", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkFullHouse() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.add( new Card(CardSuite.SPADES, CardValue.TWO) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.add( new Card(CardSuite.SPADES, CardValue.FIVE) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.EIGHT) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.TEN) );
        
        assertEquals(PokerHand.FULL_HOUSE, BestHand.bestPokerHand(cards));
        assertEquals("622255", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkReallyFullHouse() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.add( new Card(CardSuite.SPADES, CardValue.TWO) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.add( new Card(CardSuite.SPADES, CardValue.FIVE) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.FIVE) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.TEN) );
        
        assertEquals(PokerHand.FULL_HOUSE, BestHand.bestPokerHand(cards));
        assertEquals("655522", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkInvertedReallyFullHouse() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.add( new Card(CardSuite.SPADES, CardValue.TWO) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FIVE) );
        cards.add( new Card(CardSuite.SPADES, CardValue.FIVE) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.SIX) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.SIX) );
        
        assertEquals(PokerHand.FULL_HOUSE, BestHand.bestPokerHand(cards));
        assertEquals("622266", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkFourOfAKind() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.add( new Card(CardSuite.SPADES, CardValue.TWO) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.TWO) );
        cards.add( new Card(CardSuite.SPADES, CardValue.FIVE) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.EIGHT) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.TEN) );
        
        assertEquals(PokerHand.FOUR_OF_A_KIND, BestHand.bestPokerHand(cards));
        assertEquals("72222a", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkFourOfAKindWithThreeOfAKindPresent() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.TWO) );
        cards.add( new Card(CardSuite.SPADES, CardValue.TWO) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.TWO) );
        cards.add( new Card(CardSuite.SPADES, CardValue.FIVE) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.FIVE) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FIVE) );

        assertEquals(PokerHand.FOUR_OF_A_KIND, BestHand.bestPokerHand(cards));
        assertEquals("722225", BestHand.encode(cards));
    }

}