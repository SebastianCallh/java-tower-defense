package se.liu.ida.tddd78.towerdefense.objects;

import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

import java.awt.*;

/**
 * Created by Seba on 2015-02-12.
 */
public abstract class AbstractGameObject implements GameObject {
    private Point position;
    private int size;
    private double direction;
    private int speed;

    public Point getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position.x = x;
        this.position.y = y;
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

    public AbstractGameObject(Point position, int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Negative size not supported");
        }
        this.position = position;
        this.size = size;
    }
}
