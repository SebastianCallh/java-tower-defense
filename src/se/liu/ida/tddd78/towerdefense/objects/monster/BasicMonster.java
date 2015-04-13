package se.liu.ida.tddd78.towerdefense.objects.monster;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.AbstractMovable;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.commands.BountyCommand;
import se.liu.ida.tddd78.towerdefense.objects.tile.Tile;
import se.liu.ida.tddd78.towerdefense.utils.Collision;

/**
 * Created by Seba on 2015-01-24.
 */
public class BasicMonster extends AbstractMovable implements Monster {
    private int health;
    private int damage;
    private MonsterType type;
    private int bounty;

    @Override
    public void removeHealth(int health) {
        this.health -= health;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public MonsterType getType() {
        return type;
    }

    public BasicMonster(int health, int size, int speed, int damage, int bounty, MonsterType type) {
        super(new Point(0,0), size, speed);
        this.health = health;
        this.damage = damage;
        this.bounty = bounty;
        this.type = type;
    }

    @Override
    public Painter getPainter() {
        return MonsterPainter.instanceFor(this);
    }

    @Override
    public void update(Board board) {
        if (this.isAlive()) {
            Tile current = board.getTileUnder(this);
            Tile next = board.getPath().get(current);

            double angle = Collision.getAngle(next.getCenter(), this.getPosition());
            this.move(angle);

            } else {
            this.setRemoved(true);
        }
    }

    @Override
    public Command remove() {
        if (!this.isAlive()) {
            return new BountyCommand(this.bounty);
        } else {
            return null;
        }
    }
}