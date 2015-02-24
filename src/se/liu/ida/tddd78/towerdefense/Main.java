package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.objects.Layout;
import se.liu.ida.tddd78.towerdefense.objects.Layout.Type;
import se.liu.ida.tddd78.towerdefense.objects.Theme;
import se.liu.ida.tddd78.towerdefense.objects.ThemeType;

import javax.swing.*;
import java.util.Calendar;

/**
 * Created by Seba on 2015-01-23.
 */

public final class Main {
    private final static int MS_PER_UPDATE = 120;
    private static Board board = null;
    private static JFrame frame;
    private static Mover mover;

    private Main() {}

    public static void main(String[] args) {
        board = new Board(Layout.get(Type.STANDARD),
                new Theme(ThemeType.GREEN_IS_GOOD));

        mover = new Mover(board);
        frame = new Frame("Java tower defense", board);

        double previous = Calendar.getInstance().getTimeInMillis();
        double delay = 0.0;
        while(true) {
            double current = Calendar.getInstance().getTimeInMillis();
            double elapsed = current - previous;
            previous = current;
            delay += elapsed;

            processInput();

            while (delay >= MS_PER_UPDATE) {
                update();
                delay -= MS_PER_UPDATE;
            }

            render(delay / MS_PER_UPDATE);
        }
    }

    private static void processInput() {

    }

    private static void update() {
        board.update();
        mover.move();
    }

    private static void render(double ex) {
        frame.repaint();
    }
}
