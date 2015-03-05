package se.liu.ida.tddd78.towerdefense.objects.monster;

import se.liu.ida.tddd78.towerdefense.interfaces.Movable;
import se.liu.ida.tddd78.towerdefense.objects.GameObject;

/**
 * Created by Seba on 2015-02-12.
 */
public interface Monster extends GameObject, Movable
{
    public int getHealth();

    public void setHealth(int health);

    public void removeHealth(int health);

    public boolean isAlive();

    public MonsterType getType();
}