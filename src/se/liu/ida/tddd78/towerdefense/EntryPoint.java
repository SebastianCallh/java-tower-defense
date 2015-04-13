package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.objects.Layout;
import se.liu.ida.tddd78.towerdefense.objects.Layout.Type;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme;
import se.liu.ida.tddd78.towerdefense.objects.theme.ThemeType;
import se.liu.ida.tddd78.towerdefense.ui.EconomyPanel;
import se.liu.ida.tddd78.towerdefense.ui.GameOverScreen;
import se.liu.ida.tddd78.towerdefense.ui.ScorePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Bootstraps the game and hosts the game loop.
 */
public final class EntryPoint {
    private static final Logger LOG = Logger.getLogger(EntryPoint.class.getName());

    private final static int MS_PER_UPDATE = 15;
    private EntryPoint() {}

    public static void main(String[] args) {
        Player player;
        try {
            player = new Player();
        } catch (TypeNotSupportedException e) {
            LOG.log(Level.SEVERE, "Misconfigured Player class. Default character type not supported", e);
            return;
        }

        Board board = new Board(Layout.get(Type.STANDARD),
                new Theme(ThemeType.GREEN_IS_GOOD),
                player.getCharacter());

        Painter painter = new Painter(board);

        int scale = painter.getScale();
        ScorePanel scorePanel = new ScorePanel(scale);
        EconomyPanel economyPanel = new EconomyPanel(scale);
        GameOverScreen gameOverScreen = new GameOverScreen(scale);
        gameOverScreen.setVisible(false);

        Game game = new Game(board, player,
			     new InputHandler(painter), new Spawner());
        game.addScoreObserver(scorePanel);
        game.addScoreObserver(economyPanel);
        game.addScoreObserver(gameOverScreen);

        gameOverScreen.addButtonClickListener(game);

        GameWindow gameWindow = new GameWindow("Java tower defense");
        gameWindow.addInnerComponent(painter, BorderLayout.CENTER);
        gameWindow.addInnerComponent(scorePanel, BorderLayout.PAGE_START);
        gameWindow.addInnerComponent(economyPanel, BorderLayout.PAGE_END);
        gameWindow.addLayeredComponent(gameOverScreen, JLayeredPane.PALETTE_LAYER);
        gameWindow.create();

        Timer updateTimer = new Timer(MS_PER_UPDATE, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.processInput();
                game.update();
                gameWindow.repaint();
            }
        });
        updateTimer.setCoalesce(true);
        updateTimer.start();
    }
}