package logic;

import data.*;

import java.util.ArrayList;
import java.util.Collections;

public class GameLogic {

    private final ArrayList<Player> players;
    private ArrayList<Card> communityCards;
    private Deck deck;

    public GameLogic(int numBots) {
        this.players = new ArrayList<>();
        this.communityCards = new ArrayList<>();
        this.deck = new Deck();
        addPlayers(numBots);
    }

    private void addPlayers(int numPlayers) {
        final int MAX_BOTS = 7;

        if (numPlayers < 1) {
            numPlayers = 1;
        } else if (numPlayers > MAX_BOTS) {
            numPlayers = MAX_BOTS;
        }

        for (int i = 0; i <= numPlayers; i++) {
            players.add(new Player());
        }
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public ArrayList<Card> getCommunityCards() {
        return this.communityCards;
    }

    public void dealPlayers() {
        for (Player player : this.players) {
            player.addCard( this.deck.dealCard() );
            player.addCard( this.deck.dealCard() );
        }
    }

    private void dealCommunity(int numCards) {
        for (int i = 0; i < numCards; i++) {
            this.communityCards.add( this.deck.dealCard() );
        }
    }

    public void flop() {
        dealCommunity(3);
    }

    public void turn() {
        dealCommunity(1);
    }

    public void river() {
        dealCommunity(1);
    }

    public ArrayList<Player> getWinners() {
        ArrayList<Player> winners = new ArrayList<>();
        String bestHand = "";

        for (Player player : this.players) {
            String playerHand = player.getEncodedHand(this.getCommunityCards());
            if (playerHand.compareTo(bestHand) > 0) {
                bestHand = playerHand;
                winners = new ArrayList<>();
                winners.add(player);
            } else if (playerHand.compareTo(bestHand) == 0) {
                winners.add(player);
            }
        }
        printPlayers();

        return winners;
    }

    private void printPlayers() {
        for(Player p : players) {
            System.out.printf("%s - %s\n", p.getName(), p.getHandType(this.getCommunityCards()));
        }
        System.out.println();
    }

    /** Reset all player hands, reset community cards, create new deck. */
    public void resetRound() {
        for (Player player : this.players) {
            player.newRound();
        }
        this.communityCards = new ArrayList<>();
        this.deck = new Deck();
    }
}
