package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.exceptions.LayoutParseException;
import se.liu.ida.tddd78.towerdefense.exceptions.ThemeLoadException;
import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.ui.*;

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

        Player player = getPlayer();
        if (player == null) return;

        Options options = getOptions();
        if (options == null) return;

        Board board = new Board(options.getLayout(),
                options.getTheme(),
                player.getCharacter());

        Painter painter = new Painter(board);

        Game game = new Game(board, player, new InputHandler(painter), new Spawner(), options);
        GameWindow gameWindow = getGameWindow(options, painter, game);

        gameLoop(game, gameWindow);
    }

    private static void gameLoop(final Game game, final GameWindow gameWindow) {
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

    private static Options getOptions() {
        Options options;
        try {
            options = new Options();
        } catch (LayoutParseException e) {
            LOG.log(Level.SEVERE, "Error loading layout", e);
            return null;
        } catch (ThemeLoadException e) {
            LOG.log(Level.SEVERE, "Error loading theme", e);
            return null;
        }
        return options;
    }

    private static Player getPlayer() {
        Player player;
        try {
            player = new Player();
        } catch (TypeNotSupportedException e) {
            LOG.log(Level.SEVERE, "Misconfigured Player class. Default character type not supported", e);
            return null;
        }
        return player;
    }

    private static GameWindow getGameWindow(Options options, Painter painter, Game game) {
        int scale = painter.getScale();
        ScorePanel scorePanel = new ScorePanel(scale);
        EconomyPanel economyPanel = new EconomyPanel(scale);
        GameOverScreen gameOverScreen = new GameOverScreen(scale);
        MenuScreen menuScreen = new MenuScreen(scale);
        OptionsScreen optionsScreen = new OptionsScreen(scale);

        game.addScoreObserver(scorePanel);
        game.addScoreObserver(economyPanel);
        game.addScoreObserver(gameOverScreen);
        game.addScoreObserver(menuScreen);
        game.addScoreObserver(optionsScreen);

        gameOverScreen.addButtonClickListener(game);
        menuScreen.addButtonClickListener(options);
        menuScreen.addButtonClickListener(game);
        optionsScreen.addButtonClickListener(options);
        optionsScreen.addButtonClickListener(game);

        GameWindow gameWindow = new GameWindow("Java tower defense");
        gameWindow.addInnerComponent(painter, BorderLayout.CENTER);
        gameWindow.addInnerComponent(scorePanel, BorderLayout.PAGE_START);
        gameWindow.addInnerComponent(economyPanel, BorderLayout.PAGE_END);
        gameWindow.addLayeredComponent(gameOverScreen, JLayeredPane.PALETTE_LAYER);
        gameWindow.addLayeredComponent(menuScreen, 2);
        gameWindow.addLayeredComponent(optionsScreen, 3);
        gameWindow.create();
        return gameWindow;
    }
}