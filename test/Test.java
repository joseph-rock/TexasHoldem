import data.Card;
import data.Player;
import logic.TexasHoldem;
import logic.HandCheck;

import java.util.ArrayList;

public class Test {
    
    public static void main(String[] args) {
        Card pc1 = new Card(2, "Hearts", "two");
        Card pc2 = new Card(2, "Hearts", "two");

        Card cc1 = new Card(4, "Hearts", "four");
        Card cc2 = new Card(4, "Hearts", "four");
        Card cc3 = new Card(6, "Hearts", "six");
        Card cc4 = new Card(6, "Hearts", "six");
        Card cc5 = new Card(11, "Hearts", "king");

        ArrayList<Card> playerCards = new ArrayList<>();
        playerCards.add(pc1);
        playerCards.add(pc2);

        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(cc1);
        communityCards.add(cc2);
        communityCards.add(cc3);
        communityCards.add(cc4);
        communityCards.add(cc5);

        ArrayList<Card> checkList = combineLists(playerCards, communityCards);
        Boolean foo = HandCheck.isFlush(checkList);
        printList(checkList);

    }

    public static void printLog(TexasHoldem game) {
        ArrayList<Card> cardList;
        for (Player player : game.getPlayers()) {
            cardList = combineLists(player.getHand(), game.getCommunityCards());
            
            System.out.println(player.getName());
            System.out.println(String.format("Is a straight: %b", HandCheck.isStraight(cardList)));
            System.out.println(String.format("Is a flush: %b", HandCheck.isFlush(cardList)));
            System.out.println(String.format("Has at least one pair: %b\n", HandCheck.isPair(cardList)));
        }
        System.out.println();
    }

    public static ArrayList<Card> combineLists(ArrayList<Card> playerCards, ArrayList<Card> communityCards) {
        ArrayList<Card> cardList = new ArrayList<Card>(playerCards);
        cardList.addAll(communityCards);
        return cardList;
    }

    public static void printList(ArrayList<Card> checkList) {
        for (Card card : checkList) {
            System.out.println(card.valueAsString + " " + card.suite);
        }
    }
}
