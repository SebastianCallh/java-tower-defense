package se.liu.ida.tddd78.towerdefense.objects.tile;

import se.liu.ida.tddd78.towerdefense.interfaces.Paintable;
import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Represents a square on the board.
 */
public class Tile implements Paintable {
    private TileType type;
    private Point position;

    public final static double TILE_SIZE = 40.0;

    public Point getTilePosition() {
        return position;
    }

    public Point getPosition() {
        return new Point(this.getTilePosition().x * TILE_SIZE,
                  this.getTilePosition().y * TILE_SIZE);
    }

    public Point getCenter() {
        Point position = this.getPosition();
        return new Point(position.x + TILE_SIZE / 2,
                position.y + TILE_SIZE / 2);
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
