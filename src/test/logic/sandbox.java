package test.logic;

import data.Card;
import data.Deck;
import data.Player;
import data.enums.Suite;
import data.enums.Value;
import logic.TexasHoldem;
import presentation.PlayerPanel;

import javax.swing.*;
import java.awt.*;

public class sandbox {

    public static void main(String[] args) {
        drawPlayerPanel();
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
//        panel.displayWinner();
//        panel.displayFold();
        panel.displayAllIn(150);
        panel.displayCardBack();

        main.add(panel.getRootPanel());

        frame.add(main);
        frame.setVisible(true);
        //frame.pack();
    }
}
