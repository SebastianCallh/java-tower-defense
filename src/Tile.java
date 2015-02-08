/**
 * Created by Seba on 2015-01-23.
 */
public class Tile {
    private Type type;

    public enum Type {
        CLEAR, BLOCKED, GOAL, SPAWN
    }

    public Type getType() {
        return type;
    }

    public Tile(Type type) {
        this.type = type;
    }
}
