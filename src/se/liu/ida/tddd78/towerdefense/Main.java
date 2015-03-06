package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.objects.Layout;
import se.liu.ida.tddd78.towerdefense.objects.Layout.Type;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme;
import se.liu.ida.tddd78.towerdefense.objects.theme.ThemeType;
import se.liu.ida.tddd78.towerdefense.objects.character.CharacterFactory;
import se.liu.ida.tddd78.towerdefense.objects.character.CharacterType;

import javax.swing.*;
import java.util.Calendar;
import java.util.Queue;

/**
 * Created by Seba on 2015-01-23.
 */
public final class Main {
    private final static int MS_PER_UPDATE = 30;
    private Main() {}

    public static void main(String[] args) {
        Board board = new Board(Layout.get(Type.STANDARD),
                new Theme(ThemeType.GREEN_IS_GOOD),
                CharacterFactory.makeCharacter(CharacterType.PLAYER));

        Painter painter = new Painter(board);
        Game game = new Game(board, new Collision(board), new InputHandler(painter));
        JFrame frame = new Frame("Java tower defense", board, painter);

        double previous = Calendar.getInstance().getTimeInMillis();
        double delay = 0.0;
        while(true) {
            double current = Calendar.getInstance().getTimeInMillis();
            double elapsed = current - previous;
            previous = current;
            delay += elapsed;


            while (delay >= MS_PER_UPDATE) {
                //TODO: Figure out what exactly to do with this
                game.processInput();
                game.update();
                delay -= MS_PER_UPDATE;
            }

            //TODO: Account for different processing speeds delay / MS_PER_UPDATE
            frame.repaint();
        }
    }
}