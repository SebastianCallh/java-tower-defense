package se.liu.ida.tddd78.towerdefense.objects.monster;

import se.liu.ida.tddd78.towerdefense.objects.abstracts.Movable;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;

/**
 * Created by Seba on 2015-02-12.
 */
public interface Monster extends GameObject, Movable
{
    public int getHealth();

    public void setHealth(int health);

    public void removeHealth(int health);

    public int getDamage();

    public boolean isAlive();

    public MonsterType getType();
}
