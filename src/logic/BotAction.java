package logic;

import data.Card;
import data.Hand;
import data.Player;
import enums.PlayerAction;

public class BotAction {

    public static void chooseAction(Player bot) {
        Hand hand = bot.getHand();

        if ( isPair(hand) || isConsecutive(hand) || hasHighCard(hand) || sameSuite(hand) ) {
            bot.setLastAction(PlayerAction.CHECK);
        } else {
            bot.setLastAction(PlayerAction.FOLD);
            bot.fold();
        }
    }

    private static boolean isPair(Hand hand) {
        return hand.getCard(0).compareTo( hand.getCard(1) ) == 0;
    }

    private static boolean isConsecutive(Hand hand) {
        return hand.getCard(0).isConsecutive( hand.getCard(1) );
    }

    private static boolean hasHighCard(Hand hand) {
        for ( Card card : hand.getCards() ) {
            if (card.getValue() > 10) {
                return true;
            }
        }
        return false;
    }

    private static boolean sameSuite(Hand hand) {
        return hand.getCard(0).sameSuite( hand.getCard(1) );
    }
}
