package presentation.components;

import data.Card;
import presentation.components.CardLabel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CommunityCardsPanel {
    private JPanel rootPanel;
    private JPanel mainPanel;
    private JPanel cardPanel;
    private JPanel potDisplayPanel;
    private JLabel potDisplayLabel;
    private JLabel messageLabel;
    private JPanel messagePanel;

    private final String POT_LABEL = "Current Pot:";

    private ArrayList<CardLabel> cardJLabelList;

    public CommunityCardsPanel() {
        potDisplayLabel.setText(String.format("%s 0", POT_LABEL));
    }

    private void createUIComponents() {
        cardPanel = new JPanel();
        cardJLabelList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cardJLabelList.add( new CardLabel(true) );
            cardPanel.add(cardJLabelList.get(i), Component.CENTER_ALIGNMENT);
        }
    }

    public void displayCards(ArrayList<Card> communityCards) {
        int idx = 0;
        for (Card card : communityCards) {
            getCardJLabel(idx).setAsCard(card);
            idx++;
        }
    }

    public void updatePot(int chips) {
        potDisplayLabel.setText(String.format("%s %d", POT_LABEL, chips));
    }

    public CardLabel getCardJLabel(int idx) {
        return cardJLabelList.get(idx);
    }

    public JPanel getRootPanel() {
        return this.rootPanel;
    }
}
