package presentation.components;

import data.Card;
import data.Player;

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

    public void displayWinner(Player player) {
        mainPanel.setOpaque(true);
        mainPanel.setBackground(new Color(57,200,20));
        statusDisplay.setText(String.format("%s", player.getHand().getHandType()));
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
