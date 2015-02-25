package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.objects.Layout;
import se.liu.ida.tddd78.towerdefense.objects.Layout.Type;
import se.liu.ida.tddd78.towerdefense.objects.Theme;
import se.liu.ida.tddd78.towerdefense.objects.ThemeType;
import se.liu.ida.tddd78.towerdefense.objects.monsters.MonsterMover;
import se.liu.ida.tddd78.towerdefense.objects.projectiles.ProjectileMover;

import javax.swing.*;
import java.util.Calendar;

/**
 * Created by Seba on 2015-01-23.
 */
public final class Main {
    private final static int MS_PER_UPDATE = 30;
    private static Board board = null;
    private static JFrame frame;
    private static Painter painter;
    private static Collision collisionHandler;
    private static Input inputHandler;
    private static MonsterMover monsterMover;
    private static ProjectileMover projectileMover;
	private static GameLogic gameLogic;

    private Main() {}

    public static void main(String[] args) {
        board = new Board(Layout.get(Type.STANDARD), new Theme(ThemeType.GREEN_IS_GOOD));
        painter = new Painter(board);
        frame = new Frame("Java tower defense", board, painter);
        collisionHandler = new Collision(board);
        inputHandler = new Input(painter);
        projectileMover = new ProjectileMover(board);
        monsterMover = new MonsterMover(board);
	    gameLogic = new GameLogic(board, collisionHandler, inputHandler);

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
        while (inputHandler.actionsPerformed()) {
            System.out.println(inputHandler.getAction());
        }
    }

    private static void update() {
        board.update();
        monsterMover.move();
        projectileMover.move();
	    gameLogic.tick();
    }

    private static void render(double ex) {
        frame.repaint();
    }
}
