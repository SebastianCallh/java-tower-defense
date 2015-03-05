package se.liu.ida.tddd78.towerdefense.interfaces;

public interface Movable extends Positionable{
    public int getSpeed();

    public void move(double dx, double dy);
}