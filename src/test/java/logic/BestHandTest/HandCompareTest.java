package logic.BestHandTest;

import data.*;
import enums.CardSuite;
import enums.CardValue;
import logic.BestHand;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandCompareTest {

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

    @org.junit.jupiter.api.Test
    void compareThreeHands() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.FOUR) );
        cards.add( new Card(CardSuite.SPADES, CardValue.FIVE) );
        cards.add( new Card(CardSuite.SPADES, CardValue.SIX) );
        cards.add( new Card(CardSuite.SPADES, CardValue.FOUR) );
        cards.add( new Card(CardSuite.HEARTS, CardValue.FIVE) );

        ArrayList<Card> p1 = new ArrayList<>(cards);
        p1.add( new Card(CardSuite.CLUBS, CardValue.TWO) );
        p1.add( new Card(CardSuite.SPADES, CardValue.THREE) );

        ArrayList<Card> p2 = new ArrayList<>(cards);
        p2.add( new Card(CardSuite.SPADES, CardValue.ACE) );
        p2.add( new Card(CardSuite.CLUBS, CardValue.FOUR) );

        ArrayList<Card> p3 = new ArrayList<>(cards);
        p3.add( new Card(CardSuite.HEARTS, CardValue.FOUR) );
        p3.add( new Card(CardSuite.CLUBS, CardValue.SEVEN) );

        assertTrue(BestHand.encode(p2).compareTo(BestHand.encode(p1)) > 0);
        assertTrue(BestHand.encode(p3).compareTo(BestHand.encode(p1)) > 0);
        assertEquals(0, BestHand.encode(p2).compareTo(BestHand.encode(p3)));
    }

    @org.junit.jupiter.api.Test
    void compareThreeHandsAlt() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add( new Card(CardSuite.HEARTS, CardValue.TEN) );
        cards.add( new Card(CardSuite.DIAMONDS, CardValue.NINE) );
        cards.add( new Card(CardSuite.CLUBS, CardValue.SEVEN) );
        cards.add( new Card(CardSuite.SPADES, CardValue.SIX) );
        cards.add( new Card(CardSuite.SPADES, CardValue.EIGHT) );

        ArrayList<Card> p1 = new ArrayList<>(cards);
        p1.add( new Card(CardSuite.DIAMONDS, CardValue.QUEEN) );
        p1.add( new Card(CardSuite.DIAMONDS, CardValue.EIGHT) );

        ArrayList<Card> p2 = new ArrayList<>(cards);
        p2.add( new Card(CardSuite.SPADES, CardValue.QUEEN) );
        p2.add( new Card(CardSuite.HEARTS, CardValue.ACE) );

        ArrayList<Card> p3 = new ArrayList<>(cards);
        p3.add( new Card(CardSuite.SPADES, CardValue.SEVEN) );
        p3.add( new Card(CardSuite.CLUBS, CardValue.JACK) );

        assertEquals(0, BestHand.encode(p2).compareTo(BestHand.encode(p1)));
        assertTrue(BestHand.encode(p3).compareTo(BestHand.encode(p1)) > 0);
        assertTrue(BestHand.encode(p3).compareTo(BestHand.encode(p2)) > 0);
    }
}
