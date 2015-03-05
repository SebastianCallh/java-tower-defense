package se.liu.ida.tddd78.towerdefense.objects.character;

import se.liu.ida.tddd78.towerdefense.interfaces.Movable;
import se.liu.ida.tddd78.towerdefense.objects.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.HorizontalDirection;
import se.liu.ida.tddd78.towerdefense.objects.basic.VerticalDirection;

/**
 * Created by Seba on 2015-03-04.
 */
public interface Character extends GameObject, Movable {
    public CharacterType getType();

    public void setHorizontalDirection(HorizontalDirection direction);

    public void setVerticalDirection(VerticalDirection direction);
}
