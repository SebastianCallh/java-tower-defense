/**
 * Created by Seba on 2015-01-24.
 */
public class Monster {
    private Point position;
    private int hp;
    private int size;
    private Type type;

    public enum Type {
        SMALL, BIG
    }

    public Type getType() {
        return this.type;
    }

    public Point getPosition() {
        return position;
    }

    public int getHp() {
        return hp;
    }

    public int getSize() {
        return size;
    }

    public Monster(int hp, int size, Type type) {
        this.hp = hp;
        this.size = size;
        this.type = type;
    }
}
