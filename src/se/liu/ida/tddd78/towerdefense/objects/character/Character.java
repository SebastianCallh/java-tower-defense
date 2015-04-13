package se.liu.ida.tddd78.towerdefense.objects.character;

import se.liu.ida.tddd78.towerdefense.objects.abstracts.Movable;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;

/**
 * Represent a character on the board that is not a monster.
 */
public interface Character extends GameObject, Movable {
    CharacterType getType();
}
