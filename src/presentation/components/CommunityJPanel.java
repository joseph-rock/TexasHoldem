package presentation.components;

import data.Card;

import java.util.ArrayList;

import java.awt.*;
import javax.swing.*;

/** Create a panel that displays 5 cardJLabelList and the current pot
 *
 *  1. Create 5 CardGUI objects and set as placeholder display
 *  2. Flop will switch first 3 into card display
 *  3. Same with turn and river
 *  4. Repeat
 */
public class CommunityJPanel extends JPanel {

    private ArrayList<CardJLabel> cardJLabelList;
    private JLabel currentPot;

    public CommunityJPanel() {
        GridBagConstraints gbc = new GridBagConstraints();

        this.setOpaque(false);
        this.setLayout(new GridBagLayout());

        cardJLabelList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cardJLabelList.add( new CardJLabel(false) );
            cardJLabelList.get(i).setPlaceHolder();

            gbc.gridx = i;
            this.add(cardJLabelList.get(i), gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 5;
        currentPot = new JLabel("Pot: 0");
        currentPot.setFont(new Font("Helvetica", Font.BOLD, 14));
        currentPot.setForeground(Color.black);
        this.add(currentPot, gbc);
    }

    /** Take in TexasHoldem community cardJLabelList array 
     *  Set CardGUI object with correct path
    */
    public void displayCards(ArrayList<Card> communityCards) {
        int idx = 0;
        for (Card card : communityCards) {
            getCardJLabel(idx).setAsCard(card);
            idx++;
        } 
    }  

    public CardJLabel getCardJLabel(int idx) {
        return cardJLabelList.get(idx);
    }
}
