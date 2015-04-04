package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.objects.Layout;
import se.liu.ida.tddd78.towerdefense.objects.Layout.Type;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme;
import se.liu.ida.tddd78.towerdefense.objects.theme.ThemeType;
import se.liu.ida.tddd78.towerdefense.ui.EconomyPanel;
import se.liu.ida.tddd78.towerdefense.ui.GameOverScreen;
import se.liu.ida.tddd78.towerdefense.ui.ScorePanel;
import se.liu.ida.tddd78.towerdefense.utils.Collision;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Calendar;

/**
 * Created by Seba on 2015-01-23.
 */
public final class Main {
    private final static int MS_PER_UPDATE = 15;
    private Main() {}

    public static void main(String[] args) {
        Player player = new Player();

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

        Frame frame = new Frame("Java tower defense");
        frame.addInnerComponent(painter, BorderLayout.CENTER);
        frame.addInnerComponent(scorePanel, BorderLayout.PAGE_START);
        frame.addInnerComponent(economyPanel, BorderLayout.PAGE_END);
        frame.addLayeredComponent(gameOverScreen, JLayeredPane.PALETTE_LAYER);
        frame.create();

        Timer updateTimer = new Timer(MS_PER_UPDATE, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.processInput();
                game.update();
                frame.repaint();
            }
        });
        updateTimer.setCoalesce(true);
        updateTimer.start();
    }
}