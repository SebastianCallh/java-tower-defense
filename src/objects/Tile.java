package objects;

import abstracts.Paintable;

/**
 * Created by Seba on 2015-01-23.
 */
public class Tile extends Paintable {
    private Type type;
    private Point center;

    public enum Type {
        CLEAR, BLOCKED, GOAL, SPAWN
    }

    public Point getCenter() {
        return center;
    }

    public Type getType() {
        return type;
    }

    public Tile(Type type) {
        this.type = type;
    }
}
