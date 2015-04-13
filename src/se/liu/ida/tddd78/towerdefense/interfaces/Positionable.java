package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Created by Seba on 2015-02-09.
 */
public interface Positionable {
    Point getPosition();

    void setPosition(double x, double y);

    void setPosition(Point position);
}
