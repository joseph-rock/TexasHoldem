package data;

import data.enums.Suite;
import data.enums.Value;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private final ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        makeDeck();
    }

    private void makeDeck() {
        for (Suite suite : Suite.values()){
            for (Value value : Value.values()) {
                cards.add( new Card(value.ordinal() + 2, suite.toString(), value.toString()) );
            }
        } 
    }

    public Card dealCard() {
        Random r = new Random();
        int randomIndex = r.nextInt(cards.size());

        Card card = cards.get(randomIndex);
        cards.remove(randomIndex);

        return card;
    }

    public Card getCard(Suite suite, Value value) {
        Card card = new Card();

        for (Card c : cards) {
            if (c.getSuite().equals(suite.toString()) && c.getValueAsString().equals(value.toString())) {
                card = c;
                break;
            }
        }

        return card;
    }
}
