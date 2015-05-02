package se.liu.ida.tddd78.towerdefense.entities.abstracts;



/**
 * Allows implementing class to be moved around on the board.
 */
public interface Movable extends GameEntity{

    void move(double angle);
}