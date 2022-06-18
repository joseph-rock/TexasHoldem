package main;

import logic.TexasHoldem;
import presentation.GameGUI;

import javax.swing.*;
import java.awt.*;

public class GameController {

    private JFrame rootFrame;
    private GameGUI gameGUI;
    private TexasHoldem game;
    private int count;

    public GameController(int numBots, String playerName) {
        this.game = new TexasHoldem(numBots, playerName);

        initGameGUI();
        initFrame();
    }

    private void initGameGUI() {
        this.gameGUI = new GameGUI();
        this.gameGUI.setPlayerPanels(game.getPlayers());

        this.gameGUI.getCheckButton().addActionListener(e -> {
            cycleGameState();
        });
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
    }

    public void cycleGameState() {
        switch (count) {
            case 0 -> dealBoard();
            case 1 -> flopBoard();
            case 2 -> turnBoard();
            case 3 -> riverBoard();
            case 4 -> endOfRoundBoard();
            case 5 -> {
                startOfRound();
                count = -1;
            }
        }
        count++;
    }
}
