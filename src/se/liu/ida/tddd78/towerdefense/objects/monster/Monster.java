package se.liu.ida.tddd78.towerdefense.objects.monster;

import se.liu.ida.tddd78.towerdefense.objects.abstracts.Movable;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;

/**
 * Represents a monster on the board.
 */
public interface Monster extends GameObject, Movable
{
    void removeHealth(int health);

    int getDamage();

    MonsterType getType();
}
