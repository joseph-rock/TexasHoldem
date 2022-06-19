package data;

import java.util.HashMap;
import java.util.Map;

public class PlayerSequence {

    private Map<Integer, Integer> clockwiseSequence;

    public PlayerSequence() {
        clockwiseSequence = new HashMap<>();
        clockwiseSequence.put(0, 7);
        clockwiseSequence.put(7, 3);
        clockwiseSequence.put(3, 4);
        clockwiseSequence.put(4, 1);
        clockwiseSequence.put(1, 5);
        clockwiseSequence.put(5, 2);
        clockwiseSequence.put(2, 6);
        clockwiseSequence.put(6, 0);
    }

    public int getNext(int current) {
        return clockwiseSequence.get(current);
    }
}
