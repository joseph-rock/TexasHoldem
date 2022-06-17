package enums;

public enum Value {
    TWO("two"),
    THREE("three"),
    FOUR("four"),
    FIVE("five"),
    SIX("six"),
    SEVEN("seven"),
    EIGHT("eight"),
    NINE("nine"),
    TEN("ten"),
    JACK("jack"),
    QUEEN("queen"),
    KING("king"),
    ACE("ace");

    private String displayString;

    Value(String displayString) {
        this.displayString = displayString;
    }

    public String displayString() {
        return displayString;
    }

    @Override
    public String toString() {
        return displayString;
    }
}
