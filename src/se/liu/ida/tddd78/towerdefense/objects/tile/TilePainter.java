package se.liu.ida.tddd78.towerdefense.objects.tile;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme.Element;

import java.awt.*;

/**
 * Handles painting the tiles.
 */
public final class TilePainter implements Painter{
    private static final TilePainter INSTANCE = new TilePainter();

    private Tile tile;

    private TilePainter() {
        this.tile = null;
    }

    public static Painter instanceFor(Tile tile) {
        INSTANCE.tile = tile;
        return INSTANCE;
    }

    @Override
    public void paint(Graphics2D g2d, Theme theme, int scale) throws TypeNotSupportedException {
        Element element;
        switch (tile.getType()) {
            case PATH:
                element = Element.TILE_PATH;
                break;
            case BLOCKED:
                element = Element.TILE_BLOCKED;
                break;
            case GOAL:
                element = Element.TILE_GOAL;
                break;
            case SPAWN:
                element = Element.TILE_SPAWN;
                break;
            default:
                throw new TypeNotSupportedException("Unrecognized tile type");
        }

        g2d.setColor(theme.getStyle(element));
        g2d.fillRect((int) tile.getPosition().getX() * scale,
                (int) tile.getPosition().getY() * scale,
                (int) Tile.TILE_SIZE * scale,
                (int) Tile.TILE_SIZE * scale);
    }

}
