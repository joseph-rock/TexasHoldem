package data;

import enums.CardSuite;
import enums.CardValue;

public class Card implements Comparable<Card>{

    private final CardValue value;
    private final CardSuite suite;

    public Card(CardSuite suite, CardValue value){
        this.value = value;
        this.suite = suite;
    }

    public CardValue getValue() {
        return value;
    }

    public CardSuite getSuite() {
        return suite;
    }

    /** Generate image filename as "suite_value.png" */
    public String getCardPath(){
        return suite.toString() + "_" + value.toString() + ".png";
    }

    @Override public int compareTo(Card o) {
        return Integer.compare(this.getValue().toInt(), o.getValue().toInt());
    }
}
