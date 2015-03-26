package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.objects.Layout;
import se.liu.ida.tddd78.towerdefense.objects.Layout.Type;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme;
import se.liu.ida.tddd78.towerdefense.objects.theme.ThemeType;
import se.liu.ida.tddd78.towerdefense.ui.EconomyPanel;
import se.liu.ida.tddd78.towerdefense.ui.ScorePanel;
import se.liu.ida.tddd78.towerdefense.utils.Collision;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Calendar;

/**
 * Created by Seba on 2015-01-23.
 */
public final class Main {
    private final static int MS_PER_UPDATE = 30;
    private Main() {}

    public static void main(String[] args) {
        Player player = new Player();

        Board board = new Board(Layout.get(Type.STANDARD),
                new Theme(ThemeType.GREEN_IS_GOOD),
                player.getCharacter());

        Painter painter = new Painter(board);
        ScorePanel scorePanel = new ScorePanel(painter.getScale());
        EconomyPanel economyPanel = new EconomyPanel(painter.getScale());

        Game game = new Game(board, player,
			     new InputHandler(painter), new Spawner());
        game.addScoreObserver(scorePanel);
        game.addScoreObserver(economyPanel);

        Frame frame = new Frame("Java tower defense", board);
        frame.add(painter, BorderLayout.CENTER);
        frame.add(scorePanel, BorderLayout.PAGE_START);
        frame.add(economyPanel, BorderLayout.PAGE_END);
        frame.create();

        double previous = System.currentTimeMillis();
        double delay = 0.0;
        while(true) {
            double current = System.currentTimeMillis();
            double elapsed = current - previous;
            previous = current;
            delay += elapsed;


            while (delay >= MS_PER_UPDATE) {
                game.processInput();
                game.update();
                delay -= MS_PER_UPDATE;
            }

            //TODO: Account for different processing speeds delay / MS_PER_UPDATE
            frame.repaint();
        }
    }
}