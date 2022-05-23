package presentation;

import data.Card;
import data.Player;
import logic.HandCheck;
import logic.TexasHoldem;
import presentation.components.CommunityJPanel;
import presentation.components.PlayerJPanel;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class TexasHoldemGUI {

    JFrame frame;
    TexasHoldem game;
    int count = 0;

    ArrayList<PlayerJPanel> playerPanelList = new ArrayList<>();
    CommunityJPanel communityPanel = new CommunityJPanel();

    JPanel buttonPanel = new JPanel();
    JButton start = new JButton("Start");
    JButton fold = new JButton("Fold");
    JButton check = new JButton("Check");
    JButton call = new JButton("Call");
    JButton raise = new JButton("Raise");

    public TexasHoldemGUI(int numPlayers){
        frame = new JFrame();
        this.game = new TexasHoldem(numPlayers);

        // Configure Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1280,720));
        frame.setLayout(new GridBagLayout());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.decode("#0f6e14"));

        // Set Initial Game State
        initBoard();
        drawBoard();

        // Button panel
        buttonPanel.setLayout(new GridLayout(5,1));
        buttonPanel.add(start);
        buttonPanel.add(fold);
        buttonPanel.add(check);
        buttonPanel.add(call);
        buttonPanel.add(raise);
        frame.add(buttonPanel, getGBC(3, 4, 1, 1));
        start.addActionListener(e -> cycleGameState());

        frame.setVisible(true);
    }

    public void drawBoard() {
        // ** TOP ROW **
        frame.add(playerPanelList.get(7), getGBC(0, 0, 1, 1));
        frame.add(playerPanelList.get(1), getGBC(1, 0, 1, 2));
        frame.add(playerPanelList.get(4), getGBC(3, 0, 1, 1));

        // ** MIDDLE ROW **
        frame.add(playerPanelList.get(3), getGBC(0, 1, 2, 1));
        frame.add(playerPanelList.get(2), getGBC(3, 1, 2, 1));

        // ** BOTTOM ROW **
        frame.add(playerPanelList.get(6), getGBC(0, 3, 1, 1));
        frame.add(playerPanelList.get(0), getGBC(0, 4, 1, 4));
        frame.add(playerPanelList.get(5), getGBC(3, 3, 1, 1));

        // community cards
        communityPanel.displayCards(game.communityCards);
        frame.add(communityPanel, getGBC(1, 1, 2, 2));
        
        frame.revalidate();
    }

    public void initBoard() {
        // Set fresh CommunityPanel
        playerPanelList = new ArrayList<>();
        communityPanel = new CommunityJPanel();

        // Set fresh PlayerPanels
        for (int i = 0; i < 8; i++) {
            playerPanelList.add( new PlayerJPanel() );

            if (i < game.numberOfPlayers()) {
                playerPanelList.get(i).initActivePlayerDisplay(game.getPlayer(i));
            }
        }
    }

    public void resetBoard(){
        communityPanel.removeAll();
        for ( PlayerJPanel panel : playerPanelList ) {
            panel.removeAll();
        }
        game.resetRound();
        initBoard();
    }

    public void dealBoard() {
        game.dealPlayers();
        Player human = game.getPlayer(0);
        Card c1 = human.getHand().get(0);
        Card c2 = human.getHand().get(1);
        playerPanelList.get(0).displayCards(c1, c2);

        for (int i = 1; i < game.numberOfPlayers(); i++) {
            playerPanelList.get(i).displayCardBack();
        }
    }

    public GridBagConstraints getGBC(int xpos, int ypos, int height, int width) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20,20,20,20);
        gbc.gridx = xpos;
        gbc.gridy = ypos;
        gbc.gridheight = height;
        gbc.gridwidth = width;
        return gbc;
    }

    public void flopBoard() {
        game.flop();
    }

    public void turnBoard() {
        game.turn();
    }

    public void riverBoard() {
        game.river();
    }

    public void revealBotCards() {
        for (int i = 1; i < game.numberOfPlayers(); i++) {
            Player bot = game.getPlayer(i);
            Card c1 = bot.getHand().get(0);
            Card c2 = bot.getHand().get(1);
            playerPanelList.get(i).displayCards(c1, c2);
        }
    }

    public void cycleGameState() {
        switch (count) {
            case 0 -> dealBoard();
            case 1 -> flopBoard();
            case 2 -> turnBoard();
            case 3 -> riverBoard();
            case 4 -> {
                revealBotCards();
                printLog(game);
            }
            case 5 -> {
                resetBoard();
                count = -1;
            }
        }
        drawBoard();
        count++;
    }

    public static void printLog(TexasHoldem game) {
        ArrayList<Card> cardList;
        for (Player player : game.getPlayers()) {
            cardList = combineLists(player.getHand(), game.getCommunityCards());

            System.out.println(player.getName());
            System.out.printf("Is a straight: %b%n", HandCheck.isStraight(cardList));
            System.out.printf("Is a flush: %b%n", HandCheck.isFlush(cardList));
            System.out.printf("Has at least one pair: %b\n%n", HandCheck.isPair(cardList));
        }
        System.out.println();
    }

    public static ArrayList<Card> combineLists(ArrayList<Card> playerCards, ArrayList<Card> communityCards) {
        ArrayList<Card> cardList = new ArrayList<>(playerCards);
        cardList.addAll(communityCards);
        return cardList;
    }
}
