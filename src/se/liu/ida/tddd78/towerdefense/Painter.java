package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.interfaces.GameObserver;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.tile.Tile;

import javax.swing.*;
import java.awt.*;

/**
 * Handles the drawing of all game objects and tiles on the board.
 */
public class Painter extends JComponent implements GameObserver {

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
            g2d.drawImage(this.board.getTheme().getSprite(gameObject.getType()),
                    ((int)gameObject.getPosition().getX() - gameObject.getSize()) * scale,
                    ((int)gameObject.getPosition().getY() - gameObject.getSize()) * scale,
                    gameObject.getSize() * 2 * scale,
                    gameObject.getSize() * 2 * scale,
                    null);
        }
    }

    private void paintTiles(Graphics2D g2d) {
        for (int x = 0; x < this.board.getWidth(); x++) {
            for (int y = 0; y < this.board.getHeight(); y++) {
                Tile tile = this.board.getTile(x, y);
                g2d.drawImage(this.board.getTheme().getSprite(tile.getType()),
                        (int)(tile.getPosition().getX()) * scale,
                        (int)(tile.getPosition().getY()) * scale,
                        (int) Tile.TILE_SIZE * scale,
                        (int) Tile.TILE_SIZE * scale,
                        null);
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