package se.liu.ida.tddd78.towerdefense.objects.abstracts;

import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Contains all required fields amd methods for a class to be placed on the board.
 */
public abstract class AbstractGameObject implements GameObject {
    private Point position;
    private int size;
    private boolean removed;

    protected AbstractGameObject(Point position, int size) {
        assert size > 0;

        this.position = position;
        this.size = size;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(double x, double y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    @Override
    public void setPosition(Point position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isRemoved() {
        return this.removed;
    }

    @Override
    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}