package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Allows implementing class to be placed on the board.
 */
public interface Positionable {
    Point getPosition();

    void setPosition(double x, double y);

    void setPosition(Point position);
}
