package se.liu.ida.tddd78.towerdefense.objects.monsters;

import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.AbstractGameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

import java.awt.*;

/**
 * Created by Seba on 2015-01-24.
 */
public class BasicMonster extends AbstractGameObject implements Monster {
    private int hp;
    private int speed;
    private double direction;
    private MonsterType type;

    public int getHp() {
        return hp;
    }

    @Override public int getSpeed() {
        return this.speed;
    }

    @Override public double getDirection() {
        return this.direction;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public MonsterType getType() {
        return type;
    }

    public BasicMonster(int hp, int size, int speed, MonsterType type) {
        super(new Point(0,0), new Dimension(size, size));
        this.hp = hp;
        this.speed = speed;
        this.type = type;
    }

    @Override
    public Painter getPainter() {
        return MonsterPainter.instanceFor(this);
    }

    @Override public void update() {
    }
}
