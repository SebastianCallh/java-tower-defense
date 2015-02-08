import javax.swing.*;
import java.awt.*;

/**
 * Created by Seba on 2015-01-23.
 */
public class GraphicComponent extends JComponent {
    private final Dimension GRID_SIZE = new Dimension(700, 700);
    private Dimension TILE_SIZE;
    private Level level;

    public GraphicComponent(Level level) {
        this.level = level;
        this.TILE_SIZE = new Dimension((int)this.GRID_SIZE.getWidth() / level.getWidth(),
                (int)this.GRID_SIZE.getHeight() / level.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        paintTiles(g2d);
        paintMonsters(g2d);
    }

    private void paintMonsters(Graphics2D g2d) {
        for (int i = 0; i < this.level.getMonsterCount(); i++) {
            Monster monster = this.level.getMonster(i);
            g2d.setColor(this.level.getTheme().getMonsterStyle(monster.getType()));
            g2d.fillOval(150, 150, monster.getSize(), monster.getSize());
        }
    }

    private void paintTiles(Graphics2D g2d) {
        for (int x = 0; x < this.level.getWidth(); x++) {
            for (int y = 0; y < this.level.getHeight(); y++) {
                int width = (int)TILE_SIZE.getWidth();
                int height = (int)TILE_SIZE.getHeight();
                g2d.setColor(level.getTheme().getTileStyle(this.level.getTile(x, y).getType()));
                g2d.fillRect(x * width, y * height, width, height);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return GRID_SIZE;
    }
}
