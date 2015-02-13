import se.liu.ida.tddd78.towerdefense.interfaces.Observer;
import se.liu.ida.tddd78.towerdefense.objects.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.Tile;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Seba on 2015-01-23.
 */
public class Painter extends JComponent implements Observer {
    private Board board;

    public Painter(Board board) {
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        paintTiles(g2d);
        paintGameObjects(g2d);
    }

    private void paintGameObjects(Graphics2D g2d) {
        for (GameObject gameObject : this.board.getGameObjects()) {
            g2d.setColor(this.board.getTheme().getGameObjectStyle(gameObject.getObjectType()));
            g2d.fillOval(gameObject.getPosition().x,
                    gameObject.getPosition().y,
                    gameObject.getSize().height,
                    gameObject.getSize().height);
        }
    }

    private void paintTiles(Graphics2D g2d) {
        for (int x = 0; x < this.board.getWidth(); x++) {
            for (int y = 0; y < this.board.getHeight(); y++) {
                int width = (int) Tile.TILE_SIZE;
                int height = (int)Tile.TILE_SIZE;
                g2d.setColor(board.getTheme().getTileStyle(this.board.getTile(x, y).getType()));
                g2d.fillRect(x * width, y * height, width, height);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(board.BOARD_SIZE, board.BOARD_SIZE);
    }

    @Override
    public void onNotify() {
        this.repaint();
    }
}