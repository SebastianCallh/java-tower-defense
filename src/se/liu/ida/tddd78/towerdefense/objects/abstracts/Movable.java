package se.liu.ida.tddd78.towerdefense.objects.abstracts;

import se.liu.ida.tddd78.towerdefense.interfaces.Positionable;

/**
 * Allows implementing class to be moved around on the board.
 */
public interface Movable extends Positionable {
    public int getSpeed();

    public void move(double angle);
}