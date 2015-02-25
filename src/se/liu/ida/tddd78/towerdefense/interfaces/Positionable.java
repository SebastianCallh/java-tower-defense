package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Created by Seba on 2015-02-09.
 */
public interface Positionable {
    public Point getPosition();

    public void setPosition(int x, int y);
}
