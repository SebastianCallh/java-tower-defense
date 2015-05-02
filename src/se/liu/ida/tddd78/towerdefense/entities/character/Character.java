package se.liu.ida.tddd78.towerdefense.entities.character;

import se.liu.ida.tddd78.towerdefense.entities.abstracts.Movable;

/**
 * Represent a character on the board that is not a monster.
 */
public interface Character extends Movable {
    CharacterType getType();
}
