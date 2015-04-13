package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Allows implementing class to be placed on the board.
 */
public interface Positionable {
    public Point getPosition();

    public void setPosition(double x, double y);

    public void setPosition(Point position);
}
