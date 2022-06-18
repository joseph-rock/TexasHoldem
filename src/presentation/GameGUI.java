package presentation;

import data.Card;
import data.Player;
import logic.TexasHoldem;
import presentation.components.CommunityCardsPanel;
import presentation.components.PlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Layout
 *      num: Bot num
 *      CC : Community cards
 *      P  : Player
 *
 *      4 |  1  | 5
 *      3 | CC  | 2
 *      7 | P0  | 6
 *
 */
public class GameGUI {
    private JPanel rootPanel;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private JPanel buttonPanel;
    private JButton foldButton;
    private JButton checkButton;
    private JButton betButton;
    private JSlider betSlider;
    private JSeparator seperator;

    private ArrayList<PlayerPanel> playerPanels;
    private CommunityCardsPanel communityPanel;

    public GameGUI() {
        seperator.setBackground(Color.decode("#005500"));
        seperator.setForeground(Color.BLACK);
    }

    private void createUIComponents() {
        initPanels();
        topPanel = new JPanel();
        middlePanel = new JPanel();
        bottomPanel = new JPanel();

        topPanel.add( getPlayerPanelRoot(4) );
        topPanel.add( getPlayerPanelRoot(1) );
        topPanel.add( getPlayerPanelRoot(5) );

        middlePanel.add( getPlayerPanelRoot(3) );
        middlePanel.add( communityPanel.getRootPanel() );
        middlePanel.add( getPlayerPanelRoot(2) );

        bottomPanel.add( getPlayerPanelRoot(7) );
        bottomPanel.add( getPlayerPanelRoot(0) );
        bottomPanel.add( getPlayerPanelRoot(6) );
    }

    private void initPanels() {
        // Set fresh CommunityCardsPanel
        playerPanels = new ArrayList<>();
        communityPanel = new CommunityCardsPanel();

        // Set fresh PlayerPanels
        for (int i = 0; i < 8; i++) {
            PlayerPanel panel = new PlayerPanel();
            playerPanels.add( panel );
        }
    }

    public void setPlayerPanels(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            getPlayerPanel(i).setActivePlayer(players.get(i));
        }
    }

    public void resetBoard(ArrayList<Player> players){
        initPanels();
        setPlayerPanels(players);
    }

    public void dealBoard(ArrayList<Player> players) {
        Player main = players.get(0);

        // Draw player cards
        for (int i = 0; i < players.size(); i++) {
            getPlayerPanel(i).displayCardBack();
        }
        getPlayerPanel(0).displayCards(main.getHand().getCards());
    }

    public void showBotCards(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            getPlayerPanel(i).displayCards(players.get(i).getHand().getCards());
        }
    }

    public void displayWinner(ArrayList<Player> winners) {
        for (PlayerPanel playerPanel : playerPanels) {
            for (Player winner : winners) {
                if (winner.getName().equals(playerPanel.getName())) {
                    playerPanel.displayWinner(winner);
                }
            }
        }
    }

    public void updateCommunityCards(ArrayList<Card> cards) {
        communityPanel.displayCards(cards);
    }

    private PlayerPanel getPlayerPanel(int index) {
        return playerPanels.get(index);
    }

    private JPanel getPlayerPanelRoot(int index) {
        return playerPanels.get(index).getRootPanel();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JButton getCheckButton() {
        return checkButton;
    }
}
