import logic.Game;
import presentation.GameGUI;

import javax.swing.*;
import java.awt.*;

public class GameController {

    private JFrame rootFrame;
    private GameGUI gameGUI;
    private final Game game;
    private int stage;

    public GameController(int numBots) {
        this.game = new Game(numBots);
        initGameGUI();
        initFrame();
    }

    private void initGameGUI() {
        this.gameGUI = new GameGUI();
        this.gameGUI.setPlayerPanels( this.game.getPlayers() );
        this.gameGUI.defaultScoreboard( this.game.getPlayers() );

        this.gameGUI.getDealButton().addActionListener(e -> {
            cycleStreet();
        });
    }

    private void initFrame() {
        this.rootFrame = new JFrame();
        this.rootFrame.setMinimumSize(new Dimension(1000,800));
        this.rootFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.rootFrame.setLocationRelativeTo(null);
        this.rootFrame.getContentPane().setBackground(Color.decode("#0f6e14"));
        this.rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.rootFrame.add( this.gameGUI.getRootPanel() );
        this.rootFrame.setVisible(true);
    }

    public void startOfRound() {
        this.rootFrame.remove( this.gameGUI.getRootPanel() );
        this.game.resetRound();

        initGameGUI();
        this.rootFrame.add( this.gameGUI.getRootPanel() );
        this.rootFrame.repaint();
        this.rootFrame.setVisible(true);
    }

    public void dealBoard() {
        this.game.dealPlayers();
        this.gameGUI.dealBoard( this.game.getPlayers() );
    }

    public void flopBoard() {
        this.game.flop();
        this.gameGUI.updateCommunityCards( this.game.getCommunityCards() );
        this.gameGUI.updateScoreboard(game.rankedPlayerList());
    }

    public void turnBoard() {
        this.game.turn();
        this.gameGUI.updateCommunityCards( this.game.getCommunityCards() );
        this.gameGUI.updateScoreboard(game.rankedPlayerList());
    }

    public void riverBoard() {
        this.game.river();
        this.gameGUI.updateCommunityCards( this.game.getCommunityCards() );
        this.gameGUI.updateScoreboard(game.rankedPlayerList());
    }

    public void endOfRoundBoard() {
        this.gameGUI.showBotCards( this.game.getPlayers() );
        this.gameGUI.displayWinner( this.game.getWinners() );
        this.gameGUI.getDealButton().setText("Next");
    }

    public void cycleStreet() {
        switch (stage) {
            case 0 -> dealBoard();
            case 1 -> flopBoard();
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
}
