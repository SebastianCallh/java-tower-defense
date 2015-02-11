package objects;

import abstracts.Positionable;

/**
 * Created by Seba on 2015-01-24.
 */
public class Monster extends Positionable {
    private int hp;
    private int size;
    private Type type;

    public enum Type {
        SMALL, BIG
    }

    public Type getType() {
        return this.type;
    }

    public int getHp() {
        return hp;
    }

    public int getSize() {
        return size;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Monster(int hp, int size, Type type) {
        super(new Point(0,0));
        this.hp = hp;
        this.size = size;
        this.type = type;
    }
}
