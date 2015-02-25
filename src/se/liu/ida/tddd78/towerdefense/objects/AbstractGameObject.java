package se.liu.ida.tddd78.towerdefense.objects;

import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Created by Seba on 2015-02-12.
 */
public abstract class AbstractGameObject implements GameObject {
    private Point position;
    private int size;
    private double direction;
    private int speed;
	private boolean removed;

    public Point getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position.x = x;
        this.position.y = y;
    }

	@Override
	public void setPosition(Point position) {
		this.position.x = position.x;
		this.position.y = position.y;
	}

	public double getDirection() {
        return this.direction;
    }

    public int getSpeed() {
        return this.speed;
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

	public AbstractGameObject(Point position, int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Negative size not supported");
        }
        this.position = position;
        this.size = size;
    }
}
