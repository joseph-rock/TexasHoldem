package logic;

import data.Card;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HandCheckTest {
    Card h2 = new Card(0, "hearts", "two");
    Card h3 = new Card(1, "hearts", "three");
    Card h4 = new Card(2, "hearts", "four");
    Card h5 = new Card(3, "hearts", "five");
    Card h6 = new Card(4, "hearts", "six");
    Card h7 = new Card(5, "hearts", "seven");
    Card h8 = new Card(6, "hearts", "eight");
    Card h9 = new Card(7, "hearts", "nine");
    Card ht = new Card(8, "hearts", "ten");
    Card hj = new Card(9, "hearts", "jack");
    Card hq = new Card(10, "hearts", "queen");
    Card hk = new Card(11, "hearts", "king");
    Card ha = new Card(12, "hearts", "ace");

    Card d2 = new Card(0, "diamonds", "two");
    Card d3 = new Card(1, "diamonds", "three");
    Card d4 = new Card(2, "diamonds", "four");
    Card d5 = new Card(3, "diamonds", "five");
    Card d6 = new Card(4, "diamonds", "six");
    Card d7 = new Card(5, "diamonds", "seven");
    Card d8 = new Card(6, "diamonds", "eight");
    Card d9 = new Card(7, "diamonds", "nine");
    Card dt = new Card(8, "diamonds", "ten");
    Card dj = new Card(9, "diamonds", "jack");
    Card dq = new Card(10, "diamonds", "queen");
    Card dk = new Card(11, "diamonds", "king");
    Card da = new Card(12, "diamonds", "ace");

    @org.junit.jupiter.api.Test
    void checkStraightFlush() {
        ArrayList<Card> straightFlush = new ArrayList<>();
        straightFlush.add(h2);
        straightFlush.add(h3);
        straightFlush.add(h4);
        straightFlush.add(h5);
        straightFlush.add(h6);
        straightFlush.add(h7);
        straightFlush.add(h8);
        HandCheck.check(straightFlush);
    }

    @org.junit.jupiter.api.Test
    void checkRoyalFlush() {
        ArrayList<Card> straightFlush = new ArrayList<>();
        straightFlush.add(ha);
        straightFlush.add(hk);
        straightFlush.add(hq);
        straightFlush.add(hj);
        straightFlush.add(ht);
        straightFlush.add(d7);
        straightFlush.add(d8);
        HandCheck.check(straightFlush);
    }

    @org.junit.jupiter.api.Test
    void checkLowStraight() {
        ArrayList<Card> lowStraight = new ArrayList<>();
        lowStraight.add(h2);
        lowStraight.add(h3);
        lowStraight.add(d4);
        lowStraight.add(d5);
        lowStraight.add(d6);
        lowStraight.add(h7);
        lowStraight.add(h8);
        HandCheck.check(lowStraight);
    }

    @org.junit.jupiter.api.Test
    void checkLowestStraight() {
        ArrayList<Card> lowestStraight = new ArrayList<>();
        lowestStraight.add(ha);
        lowestStraight.add(h2);
        lowestStraight.add(d3);
        lowestStraight.add(d4);
        lowestStraight.add(d5);
        lowestStraight.add(h7);
        lowestStraight.add(h8);
        HandCheck.check(lowestStraight);
    }

    @org.junit.jupiter.api.Test
    void checkHighestStraight() {
        ArrayList<Card> highestStraight = new ArrayList<>();
        highestStraight.add(ha);
        highestStraight.add(hk);
        highestStraight.add(dq);
        highestStraight.add(dj);
        highestStraight.add(dt);
        highestStraight.add(h7);
        highestStraight.add(h8);
        HandCheck.check(highestStraight);
    }

    @org.junit.jupiter.api.Test
    void checkFlush() {
        ArrayList<Card> flush = new ArrayList<>();
        flush.add(h2);
        flush.add(d2);
        flush.add(h4);
        flush.add(d4);
        flush.add(h6);
        flush.add(h7);
        flush.add(h8);
        HandCheck.check(flush);
    }
}