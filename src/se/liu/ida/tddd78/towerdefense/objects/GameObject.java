package se.liu.ida.tddd78.towerdefense.objects;

import se.liu.ida.tddd78.towerdefense.interfaces.Paintable;
import se.liu.ida.tddd78.towerdefense.interfaces.Positionable;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

import java.awt.*;

/**
 * Created by Seba on 2015-02-12.
 */
public abstract class GameObject implements Positionable, Paintable {
    private Point position;
    private GameObjectType type;
    private Dimension size;

    public Point getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position.x = x;
        this.position.y = y;
    }

    public Dimension getSize() {
        return this.size;
    }

    public GameObjectType getObjectType() {
        return this.type;
    }

    public GameObject(Point position, Dimension size, GameObjectType type) {
        if (size.getWidth() < 0 || size.getHeight() < 0) {
            throw new IllegalArgumentException("Negative size not supported");
        }
        this.position = position;
        this.size = size;
        this.type = type;
    }
}
