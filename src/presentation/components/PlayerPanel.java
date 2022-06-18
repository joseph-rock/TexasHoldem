package presentation.components;

import data.Card;
import data.Player;
import enums.PlayerStatus;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerPanel {
    private JPanel rootPanel;
    private JPanel cardPanel;
    private JPanel nameDisplayPanel;
    private JPanel chipCountPanel;
    private JLabel nameLabel;
    private JLabel chipCountLabel;
    private JLabel statusDisplay;
    private JPanel mainPanel;

    CardLabel leftCard;
    CardLabel rightCard;

    public PlayerPanel() {
        statusDisplay.setText("");
        nameLabel.setText("");
        chipCountLabel.setText("");
    }

    private void createUIComponents() {
        cardPanel = new JPanel();
        leftCard = new CardLabel(false);
        rightCard = new CardLabel(false);
        cardPanel.add(leftCard, Component.CENTER_ALIGNMENT);
        cardPanel.add(rightCard, Component.CENTER_ALIGNMENT);
    }

    /** Set card display as 'card_placeholder.png' */
    public void setActivePlayer(Player player) {
        leftCard.setPlaceHolder();
        rightCard.setPlaceHolder();

        nameLabel.setText(player.getName());
        printChipCount(player.getChipCount());
    }

    /** Set card display as 'card_back.png' */
    public void displayCardBack() {
        leftCard.setCardBack();
        rightCard.setCardBack();
    }

    /** Set card display matching cards in hand */
    public void displayCards(ArrayList<Card> cards) {
        leftCard.setAsCard(cards.get(0));
        rightCard.setAsCard(cards.get(1));
    }

    public void displayFold() {
        leftCard.setPlaceHolder();
        rightCard.setPlaceHolder();

        statusDisplay.setText(PlayerStatus.FOLD.toString());
    }

    public void displayBigBlind(Player player, int amount) {
        statusDisplay.setText( String.format("%s %d", PlayerStatus.BIG_BLIND.toString(), amount) );
        printChipCount(player.getChipCount());
    }

    public void displaySmallBlind(Player player, int amount) {
        statusDisplay.setText( String.format("%s %d", PlayerStatus.SMALL_BLIND.toString(), amount) );
        printChipCount(player.getChipCount());
    }

    public void displayBet(Player player, int amount) {
        statusDisplay.setText( String.format("%s %d", PlayerStatus.BET.toString(), amount) );
        printChipCount(player.getChipCount());
    }

    public void displayCall(Player player, int amount) {
        statusDisplay.setText( String.format("%s %d", PlayerStatus.CALL.toString(), amount) );
        printChipCount(player.getChipCount());
    }

    public void displayAllIn(int amount) {
        statusDisplay.setText( String.format("%s %d", PlayerStatus.BET.toString(), amount) );
        chipCountLabel.setText(PlayerStatus.ALL_IN.toString());
    }

    public void displayWinner(Player player) {
        mainPanel.setOpaque(true);
        mainPanel.setBackground(new Color(57,200,20));
        statusDisplay.setText(String.format("%s - %s", PlayerStatus.WINNER.toString(), player.getHand().getHandType()));
        printChipCount(player.getChipCount());
    }

    private void printChipCount(int chips) {
        chipCountLabel.setText(String.format( "Chips: %d", chips) );
    }

    public String getName() {
        return nameLabel.getText();
    }

    public JPanel getRootPanel() {
        return this.rootPanel;
    }
}
