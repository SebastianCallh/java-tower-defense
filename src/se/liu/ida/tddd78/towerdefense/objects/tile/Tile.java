package se.liu.ida.tddd78.towerdefense.objects.tile;

import se.liu.ida.tddd78.towerdefense.interfaces.Paintable;
import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Created by Seba on 2015-01-23.
 */
public class Tile implements Paintable {
    private TileType type;
    private Point gridPosition;

    public final static double TILE_SIZE = 40.0;

    public Point getGridPosition() {
        return gridPosition;
    }

    public Point getPosition() {
        return new Point(gridPosition.getX() * TILE_SIZE,
                  gridPosition.getY() * TILE_SIZE);
    }

    public Point getCenter() {
        Point position = this.getPosition();
        return new Point(position.getX() + TILE_SIZE / 2,
                position.getY() + TILE_SIZE / 2);
    }

    public TileType getType() {
        return type;
    }

    public Tile(TileType type, Point gridPosition) {
        this.gridPosition = gridPosition;
        this.type = type;
    }


    @Override
    public Painter getPainter() {
        return TilePainter.instanceFor(this);
    }
}
