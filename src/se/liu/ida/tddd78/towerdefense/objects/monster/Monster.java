package se.liu.ida.tddd78.towerdefense.objects.monster;

import se.liu.ida.tddd78.towerdefense.objects.abstracts.Movable;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;

/**
 * Created by Seba on 2015-02-12.
 */
public interface Monster extends GameObject, Movable
{

    void removeHealth(int health);

    int getDamage();

    boolean isAlive();

    MonsterType getType();
}
