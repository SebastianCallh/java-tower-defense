package se.liu.ida.tddd78.towerdefense.objects.monster;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.abstracts.AbstractMovable;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.tile.Tile;

/**
 * Created by Seba on 2015-01-24.
 */
public class BasicMonster extends AbstractMovable implements Monster {
    private int health;
    private int damage;
    private MonsterType type;

    public int getHealth() {
        return this.health;
    }

    @Override public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void removeHealth(int health) {
        this.setHealth(this.getHealth() - health);
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public boolean isAlive() {
        return this.getHealth() > 0;
    }

    @Override
    public MonsterType getType() {
        return type;
    }

    public BasicMonster(int health, int size, int speed, int damage, MonsterType type) {
        super(new Point(0,0), size, speed);
        this.health = health;
        this.damage = damage;
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