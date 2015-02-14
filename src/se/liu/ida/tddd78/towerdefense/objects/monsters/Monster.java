package se.liu.ida.tddd78.towerdefense.objects.monsters;

import se.liu.ida.tddd78.towerdefense.objects.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Created by Seba on 2015-02-12.
 */
public interface Monster extends GameObject {
    public int getHp();

    public void setHp(int hp);

    public Point getPosition();

    public void setPosition(int x, int y);

    public MonsterType getType();
}
