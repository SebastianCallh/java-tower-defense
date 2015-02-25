package se.liu.ida.tddd78.towerdefense.objects.tiles;

import se.liu.ida.tddd78.towerdefense.interfaces.Paintable;
import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Created by Seba on 2015-01-23.
 */
public class Tile implements Paintable {
    private TileType type;
    private Point position;

    public final static int TILE_SIZE = 40;

    public Point getTilePosition() {
        return position;
    }

    public Point getPosition() {
        return new Point(this.getTilePosition().x * TILE_SIZE,
                  this.getTilePosition().y * TILE_SIZE);
    }

    public Point getCenter() {
        return new Point(this.getPosition().x + TILE_SIZE / 2,
                this.getPosition().y + TILE_SIZE / 2);
    }

    public TileType getType() {
        return type;
    }

    public Tile(TileType type, Point position) {
        this.position = position;
        this.type = type;
    }


    @Override
    public Painter getPainter() {
        return TilePainter.instanceFor(this);
    }
}
