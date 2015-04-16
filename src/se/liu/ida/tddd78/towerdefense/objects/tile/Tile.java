package se.liu.ida.tddd78.towerdefense.objects.tile;

import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Represents a square on the board.
 */
public class Tile {
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

    //We're unsure why only this getType-method is upset with the interface
    public TileType getType() {
        return type;
    }

    public Tile(TileType type, Point gridPosition) {
        this.gridPosition = gridPosition;
        this.type = type;
    }
}
