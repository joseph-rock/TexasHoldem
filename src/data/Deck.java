package data;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private final ArrayList<Card> cards;
    enum Value{two, three, four, five, six, seven, eight, nine, ten, jack, queen, king, ace}
    enum Suite{diamonds, hearts, spades, clubs}

    public Deck() {
        cards = new ArrayList<>();
        makeDeck();
    }

    private void makeDeck() {
        for (Suite suite : Suite.values()){
            for (Value value : Value.values()) {
                cards.add( new Card(value.ordinal(), suite.name(), value.name()) );
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
}
