package se.liu.ida.tddd78.towerdefense.abstracts;

import se.liu.ida.tddd78.towerdefense.interfaces.Movable;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Created by Seba on 2015-03-04.
 */
public abstract class AbstractMovable extends AbstractGameObject implements Movable {
    private int speed;

    public AbstractMovable(Point position, int size, int speed) {
        super(position, size);
        this.speed = speed;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void move(double dx, double dy) {
        Point pos = this.getPosition();
        this.setPosition(pos.x + dx, pos.y + dy);
    }
}