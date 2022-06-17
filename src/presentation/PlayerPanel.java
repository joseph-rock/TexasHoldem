package presentation;

import data.Card;
import data.Player;
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
    private JLabel winMessageLabel;
    private JPanel mainPanel;

    CardJLabel leftCard;
    CardJLabel rightCard;

    public PlayerPanel() {
        winMessageLabel.setText("");
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
        chipCountLabel.setText(Integer.toString(player.getChipCount()));
    }

    public void playerFold() {
        leftCard.setPlaceHolder();
        rightCard.setPlaceHolder();
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

    public void displayWinner() {
        winMessageLabel.setText("Winner!");
    }

    public JPanel getRootPanel(){ return this.rootPanel; }
}
