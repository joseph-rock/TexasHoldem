package presentation;

import data.Card;
import data.Player;
import data.enums.PlayerStatus;
import presentation.components.CardJLabel;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel {
    private JPanel rootPanel;
    private JPanel cardPanel;
    private JPanel nameDisplayPanel;
    private JPanel chipCountPanel;
    private JLabel nameLabel;
    private JLabel chipCountLabel;
    private JLabel statusDisplay;
    private JPanel mainPanel;

    CardJLabel leftCard;
    CardJLabel rightCard;

    public PlayerPanel() {
        statusDisplay.setText("");
        nameLabel.setText("");
        chipCountLabel.setText("");
    }

    private void createUIComponents() {
        cardPanel = new JPanel();
        leftCard = new CardJLabel();
        rightCard = new CardJLabel();
        cardPanel.add(leftCard, Component.CENTER_ALIGNMENT);
        cardPanel.add(rightCard, Component.CENTER_ALIGNMENT);
    }

    /** Set card display as 'card_placeholder.png' */
    public void initActivePlayerDisplay(Player player) {
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
    public void displayCards(Card c1, Card c2) {
        leftCard.setAsCard(c1);
        rightCard.setAsCard(c2);
    }

    public void displayFold() {
        leftCard.setPlaceHolder();
        rightCard.setPlaceHolder();

        statusDisplay.setText(PlayerStatus.FOLD.print());
    }

    public void displayBigBlind(Player player, int amount) {
        statusDisplay.setText( String.format("%s %d", PlayerStatus.BIG_BLIND.print(), amount) );
        printChipCount(player.getChipCount());
    }

    public void displaySmallBlind(Player player, int amount) {
        statusDisplay.setText( String.format("%s %d", PlayerStatus.SMALL_BLIND.print(), amount) );
        printChipCount(player.getChipCount());
    }

    public void displayBet(Player player, int amount) {
        statusDisplay.setText( String.format("%s %d", PlayerStatus.BET.print(), amount) );
        printChipCount(player.getChipCount());
    }

    public void displayCall(Player player, int amount) {
        statusDisplay.setText( String.format("%s %d", PlayerStatus.CALL.print(), amount) );
        printChipCount(player.getChipCount());
    }

    public void displayAllIn(int amount) {
        statusDisplay.setText( String.format("%s %d", PlayerStatus.BET.print(), amount) );
        chipCountLabel.setText(PlayerStatus.ALL_IN.print());
    }

    public void displayWinner(Player player) {
        mainPanel.setOpaque(true);
        mainPanel.setBackground(new Color(57,200,20));
        statusDisplay.setText(String.format("%s - %s", PlayerStatus.WINNER.print(), player.getHand().getHandDescription()));
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
