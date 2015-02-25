package se.liu.ida.tddd78.towerdefense.objects;

import se.liu.ida.tddd78.towerdefense.interfaces.Paintable;
import se.liu.ida.tddd78.towerdefense.interfaces.Positionable;

/**
 * Created by Seba on 2015-02-14.
 */
public interface GameObject extends Positionable, Paintable {
    public int getSize();

    public void update();

    public boolean isRemoved();

    public void setRemoved(boolean removed);
}
