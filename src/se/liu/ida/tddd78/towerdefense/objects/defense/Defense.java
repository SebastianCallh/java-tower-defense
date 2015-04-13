package se.liu.ida.tddd78.towerdefense.objects.defense;

import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;

/**
 * Represents a defense on the board.
 */
public interface Defense extends GameObject {
    DefenseType getType();

    int getCost();

}