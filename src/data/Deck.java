package data;

import enums.CardSuite;
import enums.CardValue;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private final ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        for (CardSuite suite : CardSuite.values()){
            for (CardValue value : CardValue.values()) {
                this.cards.add( new Card( value, suite) );
            }
        }
    }

    public Card dealCard() {
        Random r = new Random();
        int randomIndex = r.nextInt( this.cards.size() );

        Card card = this.cards.get(randomIndex);
        this.cards.remove(randomIndex);

        return card;
    }

    public Card getCard(CardSuite suite, CardValue value) {
        for (Card card : this.cards) {
            if ( card.getSuite() == suite && card.getValue() == value ) {
                return card;
            }
        }

        // TODO: Throw exception
        return null;
    }
}
