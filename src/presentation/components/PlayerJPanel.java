//package presentation.components;
///**Each Card Panel has 2 cards, name, current chips */
//
//import data.Card;
//import data.Player;
//
//import java.awt.*;
//import javax.swing.*;
//
//public class PlayerJPanel extends JPanel {
//
//    private CardJLabel card1;
//    private CardJLabel card2;
//    private JLabel nameLabel;
//    private JLabel chipCountLabel;
//
//    private GridBagConstraints gbc;
//
//    private String name = "";
//    private String chipCount = "";
//
//    /** Set as blank panel and add empty card objects */
//    public PlayerJPanel() {
//        this.setOpaque(false);
//        this.setLayout(new GridBagLayout());
//
//        card1 = new CardJLabel(false);
//        card2 = new CardJLabel(false);
//
//        nameLabel = new JLabel(name);
//        nameLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
//        nameLabel.setForeground(Color.black);
//
//        chipCountLabel = new JLabel(chipCount);
//        chipCountLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
//        chipCountLabel.setForeground(Color.black);
//
//        gbc = new GridBagConstraints();
//
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridheight = 1;
//        gbc.gridwidth = 2;
//        this.add(nameLabel, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.gridheight = 1;
//        gbc.gridwidth = 1;
//        this.add(card1, gbc);
//
//        gbc.gridx = 1;
//        gbc.gridy = 1;
//        gbc.gridheight = 1;
//        gbc.gridwidth = 1;
//        this.add(card2, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        gbc.gridheight = 1;
//        gbc.gridwidth = 2;
//        this.add(chipCountLabel, gbc);
//    }
//
//    /** Set card display as 'card_placeholder.png' */
//    public void initActivePlayerDisplay(Player player) {
//        card1.setPlaceHolder();
//        card2.setPlaceHolder();
//
//        nameLabel.setText(player.getName());
//        chipCountLabel.setText(Integer.toString(player.getChipCount()));
//    }
//
//    public void playerFold() {
//        card1.setPlaceHolder();
//        card2.setPlaceHolder();
//    }
//
//    // TODO: Move default constructor to this method
//    public void resetPanel() {
//        this.removeAll();
//    }
//
//    /** Set card display as 'card_back.png' */
//    public void displayCardBack() {
//        card1.setCardBack();
//        card2.setCardBack();
//    }
//
//    /** Set card display matching cards in hand */
//    public void displayCards(Card c1, Card c2) {
//        card1.setAsCard(c1);
//        card2.setAsCard(c2);
//    }
//
//}
