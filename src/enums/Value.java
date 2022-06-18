package enums;

public enum Value {
    TWO   (2, "two"),
    THREE (3, "three"),
    FOUR  (4,"four"),
    FIVE  (5,"five"),
    SIX   (6,"six"),
    SEVEN (7,"seven"),
    EIGHT (8,"eight"),
    NINE  (9,"nine"),
    TEN   (10,"ten"),
    JACK  (11,"jack"),
    QUEEN (12,"queen"),
    KING  (13,"king"),
    ACE   (14,"ace");

    private final String displayString;
    private final int value;

    Value(int value, String displayString) {
        this.displayString = displayString;
        this.value = value;
    }

    public int toInt() {
        return value;
    }

    @Override
    public String toString() {
        return displayString;
    }
}
