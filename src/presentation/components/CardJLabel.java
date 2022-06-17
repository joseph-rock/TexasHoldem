package presentation.components;

import data.Card;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CardJLabel extends JLabel{
    private static final String PATH_HEAD = "src/presentation/images/";
    private static final String NO_PLAYER_PATH = PATH_HEAD + "card_noplayer.png";
    private static final String PLACEHOLDER_PATH = PATH_HEAD + "card_placeholder.png";
    private static final String CARD_BACK_PATH = PATH_HEAD + "card_back.png";

    private ImageIcon cardPNG;

    public CardJLabel() {
        cardPNG = new ImageIcon(NO_PLAYER_PATH);
        this.setIcon(cardPNG);
    }

    public void setPlaceHolder() {
        cardPNG = new ImageIcon(PLACEHOLDER_PATH);
        this.setIcon(cardPNG);
    }

    public void setCardBack() {
        cardPNG = new ImageIcon(CARD_BACK_PATH);
        this.setIcon(cardPNG);
    }

    public void setAsCard(Card card){
        cardPNG = new ImageIcon(PATH_HEAD + card.getCardPath());
        this.setIcon(cardPNG);
    }
    
}
