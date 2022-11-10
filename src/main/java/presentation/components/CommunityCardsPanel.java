package presentation.components;

import data.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CommunityCardsPanel {
    private JPanel rootPanel;
    private JPanel mainPanel;
    private JPanel cardPanel;

    private ArrayList<CardLabel> cardJLabelList;

    public CommunityCardsPanel() {
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

    public CardLabel getCardJLabel(int idx) {
        return cardJLabelList.get(idx);
    }

    public JPanel getRootPanel() {
        return this.rootPanel;
    }
}
