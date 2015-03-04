package se.liu.ida.tddd78.towerdefense.objects.monsters;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.AbstractGameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.tiles.Tile;

import java.awt.*;

/**
 * Created by Seba on 2015-01-24.
 */
public class BasicMonster extends AbstractGameObject implements Monster {
    private int health;
    private int speed;
    private double direction;
    private MonsterType type;

    public int getHealth() {
        return this.health;
    }

    @Override public int getSpeed() {
        return this.speed;
    }

    @Override public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void removeHealth(int health) {
        this.setHealth(this.getHealth() - health);
    }

    @Override
    public boolean isAlive() {
        return this.getHealth() > 0;
    }

    @Override
    public MonsterType getType() {
        return type;
    }

    public BasicMonster(int health, int size, int speed, MonsterType type) {
        super(new Point(0,0), size);
        this.health = health;
        this.speed = speed;
        this.type = type;
    }

    @Override
    public Painter getPainter() {
        return MonsterPainter.instanceFor(this);
    }

    @Override
    public void update(Board board) {
        if (this.isAlive()) {
            Point position = this.getPosition();
            Tile current = board.getTileUnderObject(this);
            Tile next = board.getPath().get(current);

            double angle = Math.atan2(next.getCenter().y - position.y,
                    next.getCenter().x - position.x);

            this.setPosition(position.x + Math.cos(angle) * this.getSpeed(),
                    position.y + Math.sin(angle) * this.getSpeed());
        } else {
            this.setRemoved(true);
        }
    }
}