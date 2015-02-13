package se.liu.ida.tddd78.towerdefense.objects.monsters;

import se.liu.ida.tddd78.towerdefense.interfaces.Positionable;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Created by Seba on 2015-02-12.
 */
public interface Monster {
    public int getHp();

    public void setHp(int hp);

    public Point getPosition();

    public void setPosition(int x, int y);
}
