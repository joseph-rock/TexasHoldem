package test;

import data.Card;
import data.Deck;
import data.Player;
import enums.Suite;
import enums.Value;
import main.GameController;
import presentation.GameGUI;
import presentation.components.CommunityCardsPanel;
import presentation.components.PlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class sandbox {


    public static void main(String[] args) {
        //drawPlayerPanel();
        //drawCommunityPanel();
        //drawBoard();
        test();
    }

    private static void drawPlayerPanel() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500,500));

        JPanel main = new JPanel();
        main.setBackground(Color.pink);

        Deck deck = new Deck();
        Card c1 = deck.getCard(Suite.HEARTS, Value.TWO);
        Card c2 = deck.getCard(Suite.SPADES, Value.TWO);

        Player player = new Player();


        PlayerPanel panel = new PlayerPanel();
        panel.setActivePlayer(new Player());
        panel.displayCardBack();
        panel.displayBet(player, 40);
        panel.displayAllIn(150);
        //panel.displayCards(c1, c2);
        panel.displayWinner(player);
//        panel.displayFold();
//        panel.displayAllIn(150);

        main.add(panel.getRootPanel());

        frame.add(main);
        frame.setVisible(true);
        //frame.pack();
    }

    private static void drawCommunityPanel() {
        JFrame frame = new JFrame();
        JPanel main = new JPanel();
        CommunityCardsPanel panel = new CommunityCardsPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500,500));
        main.setBackground(Color.pink);

        Deck deck = new Deck();
        Card c1 = deck.getCard(Suite.HEARTS, Value.TWO);
        Card c2 = deck.getCard(Suite.SPADES, Value.TWO);
        Card c3 = deck.getCard(Suite.CLUBS, Value.TWO);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);

        panel.updatePot(150);
        panel.displayCards(cards);
        main.add(panel.getRootPanel());

        frame.add(main);
        frame.setVisible(true);
        //frame.pack();
    }

    private static void drawBoard() {
        JFrame frame = new JFrame();
        JPanel main = new JPanel();
        GameGUI gameGUI = new GameGUI();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(800,600));
        main.setBackground(Color.pink);

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            players.add(new Player());
        }

        gameGUI.setPlayerPanels(players);
        main.add(gameGUI.getRootPanel());

        frame.add(main);
        frame.setVisible(true);
        //frame.pack();
    }

    private static void test() {
        int players = 8;
        new GameController(players, "dskflj");
    }
}
