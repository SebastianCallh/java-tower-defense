package se.liu.ida.tddd78.towerdefense.objects.character;

import se.liu.ida.tddd78.towerdefense.interfaces.Movable;
import se.liu.ida.tddd78.towerdefense.objects.GameObject;

/**
 * Created by Seba on 2015-03-04.
 */
public interface Character extends GameObject, Movable {
    public CharacterType getType();
}
