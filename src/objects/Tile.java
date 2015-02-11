package objects;

import abstracts.Positionable;

/**
 * Created by Seba on 2015-01-23.
 */
public class Tile {
    private Type type;
    private Point position;

    public final static int TILE_SIZE = 40;

    public enum Type {
        PATH, BLOCKED, GOAL, SPAWN
    }

    public Point getPosition() {
        return position;
    }

    public Point getCenter() {
        return new Point(this.getPosition().x + TILE_SIZE / 2,
                this.getPosition().y + TILE_SIZE / 2);
    }

    public Type getType() {
        return type;
    }

    public Tile(Type type, Point position) {
        this.position = position;
        this.type = type;
    }
}
