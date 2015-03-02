package se.liu.ida.tddd78.towerdefense.objects.monsters;

import se.liu.ida.tddd78.towerdefense.interfaces.Moveable;
import se.liu.ida.tddd78.towerdefense.objects.GameObject;

/**
 * Created by Seba on 2015-02-12.
 */
public interface Monster extends GameObject, Moveable
{
    public int getHealth();

    public void setHealth(int health);

    public void removeHealth(int health);

    public boolean isAlive();

    public MonsterType getType();
}
