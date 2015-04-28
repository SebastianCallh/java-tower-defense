package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.exceptions.*;
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
        Player player = createPlayer();
        if (player == null) return;

        Options options = createOptions();
        if (options == null) return;

        Board board = new Board(options.getLayout(),
                options.getTheme(),
                player.getCharacter());

        Painter painter = new Painter(board);

        Game game = new Game(board, player, new InputHandler(painter), new Spawner(), options);
        GameWindow gameWindow = createGameWindow(options, painter, game);

        startGameLoop(game, gameWindow);
    }

    private static void startGameLoop(final Game game, final GameWindow gameWindow) {
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

    private static Options createOptions() {
        try {
            return new Options();
        } catch (LayoutParseException e) {
            LOG.log(Level.SEVERE, "Error loading layout", e);
            return null;
        } catch (ThemeLoadException e) {
            LOG.log(Level.SEVERE, "Error loading theme", e);
            return null;
        }
    }

    private static Player createPlayer() {
        try {
            return new Player();
        } catch (TypeNotSupportedException e) {
            LOG.log(Level.SEVERE, "Misconfigured Player class. Default character type not supported", e);
            return null;
        }
    }

    private static GameWindow createGameWindow(Options options, Painter painter, Game game) {
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