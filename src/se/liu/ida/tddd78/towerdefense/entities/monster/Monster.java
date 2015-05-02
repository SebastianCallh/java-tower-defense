package se.liu.ida.tddd78.towerdefense.entities.monster;

import se.liu.ida.tddd78.towerdefense.entities.abstracts.Movable;

/**
 * Represents a monster on the board.
 */
public interface Monster extends Movable
{
    void removeHealth(int health);

    int getDamage();

    MonsterType getType();
}
