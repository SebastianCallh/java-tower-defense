package se.liu.ida.tddd78.towerdefense.entities.defense;

import se.liu.ida.tddd78.towerdefense.entities.abstracts.GameEntity;

/**
 * Represents a defense on the board.
 */
public interface Defense extends GameEntity {
    DefenseType getType();

    int getCost();

}