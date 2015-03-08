package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.interfaces.Observer;
import se.liu.ida.tddd78.towerdefense.objects.GameObject;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Seba on 2015-01-23.
 */
public class Painter extends JComponent implements Observer {
    private Board board;
    private int scale;

    public Painter(Board board) {
        this.board = board;
        this.scale = this.getScaling(Toolkit.getDefaultToolkit().getScreenSize());
    }

    private int getScaling(Dimension screenSize) {
        int screenWidth = (int)screenSize.getWidth();
        return screenWidth == 3200 ? 3 :
                screenWidth == 2560 ? 2 : 1;
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
            gameObject.getPainter().paint(g2d, this.board.getTheme(), this.scale);
        }
    }

    private void paintTiles(Graphics2D g2d) {
        for (int x = 0; x < this.board.getWidth(); x++) {
            for (int y = 0; y < this.board.getHeight(); y++) {
                this.board.getTile(x, y).getPainter().paint(g2d, this.board.getTheme(), this.scale);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int)(board.BOARD_SIZE * this.scale),
                (int)(board.BOARD_SIZE * this.scale));
    }

    @Override
    public void onNotify() {
        this.repaint();
    }
}