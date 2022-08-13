package presentation;

import data.Card;
import data.Player;
import data.RankedPlayer;
import presentation.components.CommunityCardsPanel;
import presentation.components.PlayerPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JSeparator separator;
    private JButton dealButton;
    private JPanel rightPanel;
    private JPanel scoreboardPanel;
    private JScrollPane tableScrollPane;

    private JTable scoreboard;
    DefaultTableModel model;

    private ArrayList<PlayerPanel> playerPanels;
    private CommunityCardsPanel communityPanel;

    public GameGUI() {
        separator.setBackground(Color.decode("#005500"));
        separator.setForeground(Color.BLACK);
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

        initScoreboard();
    }

    private void initScoreboard() {
        scoreboardPanel = new JPanel();

        model = new DefaultTableModel();
        scoreboard = new JTable(model);
        scoreboard.setDefaultEditor(Object.class, null);
        scoreboard.setFocusable(false);
        scoreboard.setRowSelectionAllowed(false);
        scoreboard.setFont(new Font("Arial", Font.PLAIN, 14));
        scoreboard.setRowHeight(scoreboard.getRowHeight() + 10);

        model.addColumn("Rank");
        model.addColumn("Name");
        model.addColumn("Hand");
        model.addColumn("Encoding");

        JScrollPane sp = new JScrollPane(scoreboard);
        sp.setSize(new Dimension(100, 100));
        sp.setOpaque(false);
        scoreboardPanel.add(sp);
    }

    public void preflopScoreboard(ArrayList<Player> players) {
        int rank = 1;
        for(Player player : players) {
            model.addRow(new Object[]{rank, player.getName(), "", ""});
            rank++;
        }
    }

    public void updateScoreboard(ArrayList<RankedPlayer> players) {
        model.setRowCount(0);
        for(RankedPlayer player : players) {
            model.addRow(new Object[]{player.getRank(), player.getName(), player.getPokerHand(), player.getEncodedHand()});
        }
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

    public void dealBoard(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            getPlayerPanel(i).displayCards(players.get(i).getCards());
        }
    }

    public void showBotCards(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            getPlayerPanel(i).displayCards(players.get(i).getCards());
        }
    }

    public void displayWinner(ArrayList<Player> winners) {
        for (PlayerPanel playerPanel : playerPanels) {
            for (Player winner : winners) {
                if (winner.getName().equals(playerPanel.getName())) {
                    playerPanel.displayWinner();
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

    public JButton getDealButton() {
        return dealButton;
    }
}
