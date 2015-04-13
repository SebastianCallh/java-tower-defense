package se.liu.ida.tddd78.towerdefense.utils;

import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Contains methods for checking collision and distances of game objects.
 */
public final class Collision {

    private Collision() {}

    public static boolean isColliding(GameObject object1, GameObject object2) {
        return distanceBetween(object1, object2) <= object1.getSize() + object2.getSize();
    }

    public static double distanceBetween(GameObject object1, GameObject object2) {
        return distanceBetween(object1.getPosition(), object2.getPosition());
    }

    public static double distanceBetween(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.y - point2.y, 2) + Math.pow(point1.x - point2.x, 2));
    }
    public static double getAngle(GameObject object1, GameObject object2) {
        return getAngle(object1.getPosition(), object2.getPosition());
    }

    public static double getAngle(Point point1, Point point2) {
        return Math.atan2(point1.y - point2.y, point1.x - point2.x);
    }
}