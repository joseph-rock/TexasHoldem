package data;

public class Card implements Comparable<Card>{

    private final int value;
    private final String suite;
    private final String valueAsString;

    public Card(int value, String suite, String valueAsString){
        this.value = value;
        this.suite = suite;
        this.valueAsString = valueAsString;
    }

    public int getValue() {
        return value;
    }

    public String getSuite() {
        return suite;
    }

    /** Generate image filename as "suite_value.png" */
    public String getCardPath(){
        return suite + "_" + valueAsString + ".png";
    }

    @Override public int compareTo(Card o) {
        return Integer.compare(this.value, o.getValue());
    }
}
