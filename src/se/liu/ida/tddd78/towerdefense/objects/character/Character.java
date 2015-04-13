package se.liu.ida.tddd78.towerdefense.objects.character;

import se.liu.ida.tddd78.towerdefense.objects.abstracts.Movable;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;

/**
 * Created by Seba on 2015-03-04.
 */
public interface Character extends GameObject, Movable {
    CharacterType getType();
}
