package se.liu.ida.tddd78.towerdefense.entities.abstracts;

import se.liu.ida.tddd78.towerdefense.entities.basic.Point;

/**
 * Contains all required fields amd methods for a class to be placed on the board.
 */
public abstract class AbstractGameEntity implements GameEntity {
    private Point position;
    private int size;
    private boolean removed;

    protected AbstractGameEntity(Point position, int size) {
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