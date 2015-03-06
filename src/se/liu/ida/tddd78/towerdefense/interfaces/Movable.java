package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.objects.basic.Direction;

public interface Movable extends Positionable{
    public int getSpeed();

    public void move(Direction direction);
}