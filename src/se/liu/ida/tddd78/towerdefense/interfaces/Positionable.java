package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

import java.awt.*;

/**
 * Created by Seba on 2015-02-09.
 */

//TODO: Figure the inheritance hierarchy out.
public interface Positionable {

    public Point getPosition();

    public void setPosition(int x, int y);

    public Dimension getSize();
}
