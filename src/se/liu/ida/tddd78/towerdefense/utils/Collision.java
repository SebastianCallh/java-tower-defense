package se.liu.ida.tddd78.towerdefense.utils;

import se.liu.ida.tddd78.towerdefense.interfaces.Positionable;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Created by Seba on 2015-02-25.
 */
public final class Collision {

    private Collision() {}

    public static boolean isColliding(GameObject object1, GameObject object2) {
        return distanceBetween(object1, object2) <= object1.getSize() + object2.getSize();
    }

    public static double distanceBetween(Positionable object1, Positionable object2) {
        return distanceBetween(object1.getPosition(), object2.getPosition());
    }

    public static double distanceBetween(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.getY() - point2.getY(), 2) + Math.pow(point1.getX() - point2.getX(), 2));
    }
    public static double getAngle(Positionable object1, Positionable object2) {
        return getAngle(object1.getPosition(), object2.getPosition());
    }

    public static double getAngle(Point point1, Point point2) {
        return Math.atan2(point1.getY() - point2.getY(), point1.getX() - point2.getX());
    }
}