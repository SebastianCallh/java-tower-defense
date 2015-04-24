package se.liu.ida.tddd78.towerdefense.objects.abstracts;

import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Contains all required fields and methods for a class to be moved on the board.
 */
public abstract class AbstractMovable extends AbstractGameObject implements Movable {
    private int speed;

    protected AbstractMovable(Point position, int size, int speed) {
        super(position, size);
        assert this.speed > 0;

        this.speed = speed;
    }

    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void move(double angle) {
        Point position = this.getPosition();
        this.setPosition(position.getX() + Math.cos(angle) * this.speed,
                position.getY() + Math.sin(angle) * this.speed);
    }
}