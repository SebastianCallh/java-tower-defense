package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.interfaces.GameObserver;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles the drawing of all game objects and tiles on the board.
 */
public class Painter extends JComponent implements GameObserver {
    private static final Logger LOG = Logger.getLogger(Painter.class.getName());

    private static final int SCREEN_WIDTH_LARGEST = 4000;
    private static final int SCREEN_WIDTH_LARGER = 3200;
    private static final int SCREEN_WIDTH_LARGE = 2560;

    private Board board;
    private int scale;

    public Painter(Board board) {
        this.board = board;
        this.scale = this.getScaling(Toolkit.getDefaultToolkit().getScreenSize());
    }

    private int getScaling(Dimension screenSize) {
        int screenWidth = (int)screenSize.getWidth();
        switch (screenWidth) {
            case SCREEN_WIDTH_LARGEST:
                return 4;
            case SCREEN_WIDTH_LARGER:
                return 3;
            case SCREEN_WIDTH_LARGE:
                return 2;
            default:
                return 1;
        }
    }

    public int getScale() {
        return this.scale;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        paintTiles(g2d);
        paintGameObjects(g2d);
        this.setDoubleBuffered(true);
    }

    private void paintGameObjects(Graphics2D g2d) {
        for (GameObject gameObject : this.board.getGameObjects().getAll()) {
            try {
                gameObject.getPainter().paint(g2d, this.board.getTheme(), this.scale);
            } catch (TypeNotSupportedException e) {
                LOG.log(Level.WARNING, "Unable to paint object with unsupported type", e);
            }

        }
    }

    private void paintTiles(Graphics2D g2d) {
        for (int x = 0; x < this.board.getWidth(); x++) {
            for (int y = 0; y < this.board.getHeight(); y++) {
                try {
                    this.board.getTile(x, y).getPainter().paint(g2d, this.board.getTheme(), this.scale);
                } catch (TypeNotSupportedException e) {
                    LOG.log(Level.WARNING, "Unable to paint tile with unsupported type", e);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        super.getPreferredSize();

        return new Dimension(Board.BOARD_SIZE * this.scale,
                Board.BOARD_SIZE * this.scale);
    }

    @Override
    public void onNotify(Game game) {
        this.repaint( );
    }
}