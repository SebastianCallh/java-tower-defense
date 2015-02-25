package se.liu.ida.tddd78.towerdefense.objects;

import se.liu.ida.tddd78.towerdefense.interfaces.Paintable;
import se.liu.ida.tddd78.towerdefense.interfaces.Positionable;

import java.awt.*;

/**
 * Created by Seba on 2015-02-14.
 */
public interface GameObject extends Positionable, Paintable {
    public Dimension getSize();

    public void update();
}
