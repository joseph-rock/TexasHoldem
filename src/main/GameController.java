package main;

import data.PlayerSequence;
import enums.PlayerAction;
import logic.BotAction;
import logic.GameLogic;
import presentation.GameGUI;

import javax.swing.*;
import java.awt.*;

/**
 * No-Limit Texas Hold'em
 *
 * Definitions:
 *      Action - The betting phase
 *      Street - The phases where cards are dealt (pre-flop, flop, turn, river)
 *      Button - The 'dealer'. Action starts with the player to the left (clockwise) of the button
 *      Small blind - Player to the left (clockwise) of the button
 *      Big blind - Player to the left (clockwise) of the Small blind
 *      Under the Gun - Player that initiates the action pre-flop. Player to the left (clockwise) of the Big blind
 *      Heads up - When there are only two players remaining
 *
 * Pre-flop
 *      Under the Gun goes first
 *      Minimum opening raise must be at least twice the big blind
 *      Max is all-in
 *
 * Remaining Rounds
 *      Active player to the left of button goes first
 *
 * The Showdown
 *      Players reveal their cards. Winner wins
 *
 * Heads up
 *      Dealer places small blind
 *      Opponent places big blind
 */
public class GameController {

    private JFrame rootFrame;
    private GameGUI gameGUI;
    private final GameLogic game;
    private int stage;

    private final PlayerSequence sequence;

    public GameController(int numBots, String playerName) {
        this.game = new GameLogic(numBots, playerName);
        this.sequence = new PlayerSequence();

        initGameGUI();
        initFrame();
    }

    private void initGameGUI() {
        this.gameGUI = new GameGUI();
        this.gameGUI.setPlayerPanels(game.getPlayers());
        this.gameGUI.getDealButton().setEnabled(false);
        flipButtons();

        this.gameGUI.getDealButton().addActionListener(e -> {
            cycleStreet();
            flipButtons();
        });

        this.gameGUI.getCheckButton().addActionListener(e -> {
            cycleStreet();
        });
    }

    private void flipButtons() {
        if ( this.gameGUI.getDealButton().isEnabled() ) {
            this.gameGUI.getDealButton().setEnabled(false);

            this.gameGUI.getFoldButton().setEnabled(true);
            this.gameGUI.getCheckButton().setEnabled(true);
            this.gameGUI.getBetButton().setEnabled(true);
            this.gameGUI.getBetSlider().setEnabled(true);
            this.gameGUI.getBetSlider().setValue(0);
        } else {
            this.gameGUI.getDealButton().setEnabled(true);

            this.gameGUI.getFoldButton().setEnabled(false);
            this.gameGUI.getCheckButton().setEnabled(false);
            this.gameGUI.getBetButton().setEnabled(false);
            this.gameGUI.getBetSlider().setEnabled(false);
        }
    }

    private void initFrame() {
        this.rootFrame = new JFrame();
        this.rootFrame.setMinimumSize(new Dimension(1280,1000));
        this.rootFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.rootFrame.setLocationRelativeTo(null);
        this.rootFrame.getContentPane().setBackground(Color.decode("#0f6e14"));
        this.rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.rootFrame.add(gameGUI.getRootPanel());
        this.rootFrame.setVisible(true);
    }

    public void startOfRound() {
        this.rootFrame.remove(gameGUI.getRootPanel());
        this.game.resetRound();

        initGameGUI();
        flipButtons();
        this.rootFrame.add(gameGUI.getRootPanel());
        this.rootFrame.repaint();
        this.rootFrame.setVisible(true);
    }

    public void dealBoard() {
        game.dealPlayers();
        gameGUI.dealBoard(game.getPlayers());
    }

    public void flopBoard() {
        game.flop();
        gameGUI.updateCommunityCards(game.getCommunityCards());
    }

    public void turnBoard() {
        game.turn();
        gameGUI.updateCommunityCards(game.getCommunityCards());
    }

    public void riverBoard() {
        game.river();
        gameGUI.updateCommunityCards(game.getCommunityCards());
    }

    public void endOfRoundBoard() {
        gameGUI.showBotCards(game.getPlayers());
        gameGUI.displayWinner(game.getWinners());
        flipButtons();
        this.gameGUI.getDealButton().setText("Next");
    }

    public void cycleStreet() {
        switch (stage) {
            case 0 -> {
                dealBoard();
                action();
            }
            case 1 -> {
                flopBoard();
            }
            case 2 -> turnBoard();
            case 3 -> riverBoard();
            case 4 -> endOfRoundBoard();
            case 5 -> {
                startOfRound();
                stage = -1;
            }
        }
        stage++;
    }

    public void action(){
        PlayerSequence seq = new PlayerSequence();

        int dealer = 0;
        int next = seq.nextIndex(dealer, game.numPlayers());

        while( game.getPlayer(next).getLastAction() != PlayerAction.CHECK
                && game.getPlayer(next).getLastAction() != PlayerAction.CALL) {

            // Player action
            BotAction.chooseAction(game.getPlayer(next));
            gameGUI.updatePlayerCards(game.getPlayers());

            // Next player
            next = seq.nextIndex(next, game.numPlayers());
        }
    }
}
