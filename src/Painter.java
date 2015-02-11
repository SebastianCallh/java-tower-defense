import objects.Monster;
import objects.Tile;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Seba on 2015-01-23.
 */
public class Painter extends JComponent {
    private Board board;

    public Painter(Board board) {
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        paintTiles(g2d);
        paintMonsters(g2d);
    }
    
    private void paintMonsters(Graphics2D g2d) {
        for (int i = 0; i < this.board.getMonsterCount(); i++) {
            Monster monster = this.board.getMonster(i);
            g2d.setColor(this.board.getTheme().getMonsterStyle(monster.getType()));
            g2d.fillOval(150, 150, monster.getSize(), monster.getSize());
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
}
