package se.liu.ida.tddd78.towerdefense.objects.tile;

import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme;

import java.awt.*;

/**
 * Handles painting the tiles.
 */
public final class TilePainter implements Painter{
    private static TilePainter INSTANCE;

    private Tile tile;

    private TilePainter() {

    }

    public static TilePainter instanceFor(Tile tile) {
        if (INSTANCE == null) {
            INSTANCE = new TilePainter();
        }

        INSTANCE.setTile(tile);
        return INSTANCE;
    }

    @Override
    public void paint(Graphics2D g2d, Theme theme, int scale) {
        Theme.Element element;
        switch (tile.getType()) {
            case PATH:
                element = Theme.Element.TILE_PATH;
                break;
            case BLOCKED:
                element = Theme.Element.TILE_BLOCKED;
                break;
            case GOAL:
                element = Theme.Element.TILE_GOAL;
                break;
            case SPAWN:
                element = Theme.Element.TILE_SPAWN;
                break;
            default:
                throw new RuntimeException("Unrecognized tile type");
        }

        g2d.setColor(theme.getStyle(element));
        g2d.fillRect((int) tile.getPosition().x * scale,
                (int) tile.getPosition().y * scale,
                (int) Tile.TILE_SIZE * scale,
                (int) Tile.TILE_SIZE * scale);
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }
}
