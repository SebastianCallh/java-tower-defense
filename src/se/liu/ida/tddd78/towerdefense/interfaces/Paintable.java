package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.objects.GameObjectType;

import java.awt.*;

/**
 * Created by Seba on 2015-02-12.
 */
public interface Paintable {
    public GameObjectType getObjectType();
    public void paint(Graphics2D g2d);
}
