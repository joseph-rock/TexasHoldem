package data;

import enums.CardSuite;
import enums.CardValue;

public class Card implements Comparable<Card>{

    private final CardValue value;
    private final CardSuite suite;

    public Card(CardValue value, CardSuite suite){
        this.value = value;
        this.suite = suite;
    }

    public CardValue getValue() {
        return value;
    }

    public CardSuite getSuite() {
        return suite;
    }

    public int getValueInt() {
        return value.toInt();
    }

    public String getSuiteString() {
        return suite.toString();
    }

    /** Generate image filename as "suite_value.png" */
    public String getCardPath(){
        return suite.toString() + "_" + value.toString() + ".png";
    }

    @Override public int compareTo(Card o) {
        return Integer.compare(this.value.toInt(), o.getValueInt());
    }
}
