package logic.BestHandTest;

import data.*;
import enums.PokerHand;
import enums.CardSuite;
import enums.CardValue;
import logic.BestHand;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HighCardTest {

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

        assertEquals(PokerHand.HIGH_CARD, BestHand.bestPokerHand(cards));
        assertEquals("0ea986", BestHand.encode(cards));
    }

}
