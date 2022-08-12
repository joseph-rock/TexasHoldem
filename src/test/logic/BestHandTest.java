package test.logic;

import data.*;
import enums.PokerHand;
import enums.CardSuite;
import enums.CardValue;
import logic.BestHand;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestHandTest {

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
        
        assertEquals(PokerHand.STRAIGHT_FLUSH, BestHand.getPokerHand(cards));
        assertEquals("887654", BestHand.encode(cards));
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
        
        assertEquals(PokerHand.STRAIGHT_FLUSH, BestHand.getPokerHand(cards));
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
        
        assertEquals(PokerHand.STRAIGHT_FLUSH, BestHand.getPokerHand(cards));
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
        
        assertEquals(PokerHand.STRAIGHT_FLUSH, BestHand.getPokerHand(cards));
        assertEquals("8edcba", BestHand.encode(cards));
    }

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
        
        assertEquals(PokerHand.STRAIGHT, BestHand.getPokerHand(cards));
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
        
        assertEquals(PokerHand.STRAIGHT, BestHand.getPokerHand(cards));
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
        
        assertEquals(PokerHand.STRAIGHT, BestHand.getPokerHand(cards));
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
        cards.add( new Card(CardSuite.HEARTS, CardValue.TEN) );
        cards.add( new Card(CardSuite.SPADES, CardValue.TEN) );
        
        assertEquals(PokerHand.STRAIGHT, BestHand.getPokerHand(cards));
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
        cards.add( new Card(CardSuite.HEARTS, CardValue.JACK) );
        cards.add( new Card(CardSuite.SPADES, CardValue.JACK) );
        
        assertEquals(PokerHand.STRAIGHT, BestHand.getPokerHand(cards));
        assertEquals("4ba987", BestHand.encode(cards));
    }

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
        
        assertEquals(PokerHand.FLUSH, BestHand.getPokerHand(cards));
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
        
        assertEquals(PokerHand.FLUSH, BestHand.getPokerHand(cards));
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
        
        assertEquals(PokerHand.FLUSH, BestHand.getPokerHand(cards));
        assertEquals("587432", BestHand.encode(cards));
    }

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
        
        assertEquals(PokerHand.PAIR, BestHand.getPokerHand(cards));
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
        assertEquals(PokerHand.TWO_PAIR, BestHand.getPokerHand(cards));
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
        
        assertEquals(PokerHand.TWO_PAIR, BestHand.getPokerHand(cards));
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
        
        assertEquals(PokerHand.THREE_OF_A_KIND, BestHand.getPokerHand(cards));
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
        
        assertEquals(PokerHand.FULL_HOUSE, BestHand.getPokerHand(cards));
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
        
        assertEquals(PokerHand.FULL_HOUSE, BestHand.getPokerHand(cards));
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
        
        assertEquals(PokerHand.FULL_HOUSE, BestHand.getPokerHand(cards));
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
        
        assertEquals(PokerHand.FOUR_OF_A_KIND, BestHand.getPokerHand(cards));
        assertEquals("72222a", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkHighCard() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.SPADES, CardValue.SIX) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.EIGHT) );
        cards.add( new Card(CardSuite.SPADES, CardValue.NINE) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.TEN) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.ACE) );
        
        assertEquals(PokerHand.HIGH_CARD, BestHand.getPokerHand(cards));
        assertEquals("0ea986", BestHand.encode(cards));
    }

    @org.junit.jupiter.api.Test
    void checkPairBeatsHighCard() {
        ArrayList<Card> highCard = new ArrayList<>();
        highCard.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        highCard.add( new Card(CardSuite.DIAMONDS, CardValue.FOUR) );
        highCard.add( new Card(CardSuite.SPADES, CardValue.SIX) );
        highCard.add( new Card(CardSuite.CLUBS, CardValue.EIGHT) );
        highCard.add( new Card(CardSuite.SPADES, CardValue.NINE) );
        highCard.add( new Card(CardSuite.CLUBS, CardValue.TEN) );
        highCard.add( new Card(CardSuite.CLUBS, CardValue.ACE) );
        String hc = BestHand.encode(highCard);

        ArrayList<Card> pair = new ArrayList<>();
        pair.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        pair.add( new Card(CardSuite.DIAMONDS, CardValue.THREE) );
        pair.add( new Card(CardSuite.HEARTS, CardValue.FOUR) );
        pair.add( new Card(CardSuite.DIAMONDS, CardValue.FOUR) );
        pair.add( new Card(CardSuite.HEARTS, CardValue.SIX) );
        pair.add( new Card(CardSuite.HEARTS, CardValue.SEVEN) );
        pair.add( new Card(CardSuite.DIAMONDS, CardValue.NINE) );
        String pc = BestHand.encode(pair);

        assertTrue(hc.compareTo(pc) < 0);
    }

    @org.junit.jupiter.api.Test
    void checkHigherPairWins() {
        ArrayList<Card> highPair = new ArrayList<>();
        highPair.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        highPair.add( new Card(CardSuite.DIAMONDS, CardValue.FOUR) );
        highPair.add( new Card(CardSuite.SPADES, CardValue.SIX) );
        highPair.add( new Card(CardSuite.CLUBS, CardValue.EIGHT) );
        highPair.add( new Card(CardSuite.SPADES, CardValue.TEN) );
        highPair.add( new Card(CardSuite.CLUBS, CardValue.TEN) );
        highPair.add( new Card(CardSuite.CLUBS, CardValue.ACE) );
        String hp = BestHand.encode(highPair);

        ArrayList<Card> lowPair = new ArrayList<>();
        lowPair.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        lowPair.add( new Card(CardSuite.DIAMONDS, CardValue.THREE) );
        lowPair.add( new Card(CardSuite.HEARTS, CardValue.FOUR) );
        lowPair.add( new Card(CardSuite.DIAMONDS, CardValue.FOUR) );
        lowPair.add( new Card(CardSuite.HEARTS, CardValue.SIX) );
        lowPair.add( new Card(CardSuite.HEARTS, CardValue.SEVEN) );
        lowPair.add( new Card(CardSuite.DIAMONDS, CardValue.NINE) );
        String lp = BestHand.encode(lowPair);

        assertTrue(hp.compareTo(lp) > 0);
    }

    @org.junit.jupiter.api.Test
    void checkEqualPairHighKickerWins() {
        ArrayList<Card> highPair = new ArrayList<>();
        highPair.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        highPair.add( new Card(CardSuite.DIAMONDS, CardValue.FOUR) );
        highPair.add( new Card(CardSuite.SPADES, CardValue.SIX) );
        highPair.add( new Card(CardSuite.CLUBS, CardValue.EIGHT) );
        highPair.add( new Card(CardSuite.SPADES, CardValue.TEN) );
        highPair.add( new Card(CardSuite.CLUBS, CardValue.TEN) );
        highPair.add( new Card(CardSuite.CLUBS, CardValue.ACE) );
        String hp = BestHand.encode(highPair);

        ArrayList<Card> lowPair = new ArrayList<>();
        lowPair.add( new Card(CardSuite.HEARTS, CardValue.TWO) );
        lowPair.add( new Card(CardSuite.DIAMONDS, CardValue.FOUR) );
        lowPair.add( new Card(CardSuite.SPADES, CardValue.FIVE) );
        lowPair.add( new Card(CardSuite.CLUBS, CardValue.EIGHT) );
        lowPair.add( new Card(CardSuite.SPADES, CardValue.TEN) );
        lowPair.add( new Card(CardSuite.CLUBS, CardValue.TEN) );
        lowPair.add( new Card(CardSuite.CLUBS, CardValue.ACE) );
        String lp = BestHand.encode(lowPair);

        assertTrue(hp.compareTo(lp) > 0);
    }

//    @org.junit.jupiter.api.Test
//    void checkBug() {
//        ArrayList<Card> cards = new ArrayList<>();
//        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FOUR) );
//        cards.add( new Card(CardSuite.SPADES, CardValue.FIVE) );
//        cards.add( new Card(CardSuite.SPADES, CardValue.SIX) );
//        cards.add( new Card(CardSuite.SPADES, CardValue.FOUR) );
//        cards.add( new Card(CardSuite.HEARTS, CardValue.FIVE) );
//
//        ArrayList<Card> p1 = new ArrayList<>();
//        p1.add( new Card(CardSuite.CLUBS, CardValue.TWO) );
//        p1.add( new Card(CardSuite.SPADES, CardValue.THREE) );
//        p1.addList(cards);
//        BestHand.encode(p1);
//
//        ArrayList<Card> p2 = new ArrayList<>();
//        p2.add( new Card(CardSuite.SPADES, CardValue.ACE) );
//        p2.add( new Card(CardSuite.CLUBS, CardValue.FOUR) );
//        p2.addList(cards);
//        BestHand.encode(p2);
//
//        ArrayList<Card> p3 = new ArrayList<>();
//        p3.add( new Card(CardSuite.HEARTS, CardValue.FOUR) );
//        p3.add( new Card(CardSuite.CLUBS, CardValue.SEVEN) );
//        p3.addList(cards);
//        BestHand.encode(p3);
//
//        assertTrue(p2.compareTo(p1) > 0);
//        assertTrue(p3.compareTo(p1) > 0);
//        assertEquals(0, p2.compareTo(p3));
//    }
}