package test.logic;

import data.Player;
import logic.TexasHoldem;
import presentation.PlayerPanel;

import javax.swing.*;
import java.awt.*;

public class sandbox {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel main = new JPanel();
        main.setBackground(Color.pink);

        // Configure Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500,500));

        PlayerPanel panel = new PlayerPanel();
        panel.initActivePlayerDisplay(new Player());
        panel.displayWinner();

        main.add(panel.getRootPanel());

        frame.add(main);
        frame.setVisible(true);
        //frame.pack();
    }
}
