package data;

public class Card implements Comparable<Card>{

    final public int value;
    final public String suite;
    final public String valueAsString;

    public Card(int value, String suite, String valueAsString){
        this.value = value;
        this.suite = suite;
        this.valueAsString = valueAsString;
    }

    /** Generate image filename as "suite_value.png" */
    public String getCardPath(){
        return suite + "_" + valueAsString + ".png";
    }

    @Override public int compareTo(Card o) {
        return Integer.compare(this.value, o.value);
    }   
}
