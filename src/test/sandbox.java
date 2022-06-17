package test;

import data.Card;
import data.Deck;
import data.Player;
import enums.Suite;
import enums.Value;
import presentation.CommunityCardsPanel;
import presentation.PlayerPanel;

import javax.swing.*;
import java.awt.*;

public class sandbox {

    public static void main(String[] args) {
        //drawPlayerPanel();
        drawCommunityPanel();
    }

    private static void drawPlayerPanel() {
        JFrame frame = new JFrame();
        JPanel main = new JPanel();
        main.setBackground(Color.pink);

        Deck deck = new Deck();
        Card c1 = deck.getCard(Suite.HEARTS, Value.TWO);
        Card c2 = deck.getCard(Suite.SPADES, Value.TWO);

        Player player = new Player();

        // Configure Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500,500));

        PlayerPanel panel = new PlayerPanel();
        panel.initActivePlayerDisplay(new Player());
        panel.displayCardBack();
        panel.displayBet(player, 40);
        panel.displayAllIn(150);
        panel.displayCards(c1, c2);
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
        main.setBackground(Color.pink);

        Deck deck = new Deck();
        Card c1 = deck.getCard(Suite.HEARTS, Value.TWO);
        Card c2 = deck.getCard(Suite.SPADES, Value.TWO);

        Player player = new Player();

        // Configure Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500,500));

        CommunityCardsPanel panel = new CommunityCardsPanel();
//        panel.initActivePlayerDisplay(new Player());
//        panel.displayCardBack();
//        panel.displayBet(player, 40);
//        panel.displayAllIn(150);
//        panel.displayCards(c1, c2);
//        panel.displayWinner(player);
//        panel.displayFold();
//        panel.displayAllIn(150);


        main.add(panel.getRootPanel());

        frame.add(main);
        frame.setVisible(true);
        //frame.pack();
    }
}
