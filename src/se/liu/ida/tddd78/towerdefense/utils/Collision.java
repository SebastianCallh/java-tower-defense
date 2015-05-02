package se.liu.ida.tddd78.towerdefense.utils;

import se.liu.ida.tddd78.towerdefense.entities.abstracts.GameEntity;
import se.liu.ida.tddd78.towerdefense.entities.basic.Point;

/**
 * Contains methods for checking collision and distances of game entities.
 */
public final class Collision {

    private Collision() {}

    public static boolean isColliding(GameEntity object1, GameEntity object2) {
        return distanceBetween(object1, object2) <= object1.getSize() + object2.getSize();
    }

    public static double distanceBetween(GameEntity object1, GameEntity object2) {
        return distanceBetween(object1.getPosition(), object2.getPosition());
    }

    public static double distanceBetween(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.getY() - point2.getY(), 2) + Math.pow(point1.getX() - point2.getX(), 2));
    }
    public static double getAngleBetween(GameEntity object1, GameEntity object2) {
        return getAngleBetween(object1.getPosition(), object2.getPosition());
    }

    public static double getAngleBetween(Point point1, Point point2) {
        return Math.atan2(point1.getY() - point2.getY(), point1.getX() - point2.getX());
    }
}