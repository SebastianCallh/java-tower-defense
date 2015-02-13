package se.liu.ida.tddd78.towerdefense.objects;

import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Created by Seba on 2015-01-23.
 */
public class Tile {
    private TileType type;
    private Point position;

    public final static int TILE_SIZE = 40;

    public Point getPosition() {
        return position;
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
}
