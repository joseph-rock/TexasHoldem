package logic;

import data.*;

import java.util.ArrayList;

public class TexasHoldem {

    private ArrayList<Player> players;
    private CardCollection communityCards;
    private Deck deck;
    private final int MAX_BOTS = 7;

    public TexasHoldem(int numBots, String playerName) {
        this.players = new ArrayList<>();
        this.communityCards = new CardCollection();
        this.deck = new Deck();
        addPlayers(numBots);
        getPlayer(0).setName(playerName);
    }

    private void addPlayers(int numPlayers) {
        if (numPlayers < 1) {
            numPlayers = 1;
        } else if (numPlayers > MAX_BOTS) {
            numPlayers = MAX_BOTS;
        }
        for (int i = 0; i <= numPlayers; i++) {
            players.add(new Player());
        }
    }

    public Player getPlayer(int idx) {
        return players.get(idx);
    }

    public int numberOfPlayers() {
        return players.size();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Card> getCommunityCards() {
        return communityCards.getCards();
    }

    public void dealPlayers() {
        for (Player player : players) {
            player.addCard(deck.dealCard());
            player.addCard(deck.dealCard());
        }
    }

    private void dealCommunity(int numCards) {
        for (int i = 0; i < numCards; i++) {
            communityCards.addCard(deck.dealCard());
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
        CardCollection bestHand = new CardCollection();

        for (Player player : players) {
            CardCollection playerHand = player.getHand();
            playerHand.addCollection(communityCards);
            HandCheck.check(playerHand);

            if (playerHand.isBetterHand(bestHand)) {
                bestHand = playerHand;
                winners = new ArrayList<>();
                winners.add(player);
            } else if (playerHand.isDraw(bestHand)) {
                winners.add(player);
            }
        }

        for (Player winner : winners) {
            System.out.println(winner.getName());
            winner.getHand().printCards();
        }

        return winners;
    }

    /** Reset all player hands, reset community cards, create new deck. */
    public void resetRound() {
        for (Player player : players) {
            player.resetHand();
        }
        this.communityCards = new CardCollection();
        this.deck = new Deck();
    }

}
