package se.liu.ida.tddd78.towerdefense.objects.abstracts;

import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Contains all required fields and methods for a class to be moved on the board.
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
    public void move(double angle) {
        Point position = this.getPosition();
        this.setPosition(position.x + Math.cos(angle) * this.getSpeed(),
                position.y + Math.sin(angle) * this.getSpeed());
    }
}