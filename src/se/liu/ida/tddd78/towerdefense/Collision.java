package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.objects.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.tiles.Tile;

/**
 * Created by Seba on 2015-02-25.
 */
public class Collision {
    private final Board board;

    public Collision(Board board) {
        this.board = board;
    }

    public boolean isAtCenter(GameObject object, Tile tile) {
        if (tile == this.board.getTileUnderObject(object)) {
            double relativeX = object.getPosition().x % Tile.TILE_SIZE / Tile.TILE_SIZE;
            double relativeY = object.getPosition().y % Tile.TILE_SIZE/ Tile.TILE_SIZE;
            return (relativeX > 1.0 / 3.0 && relativeX < 2.0 / 3.0 &&
                    relativeY > 1.0 / 3.0 && relativeY < 2.0 / 3.0);
        }
        return false;
    }

    public boolean isColliding(GameObject object, Tile tile) {
        return tile == board.getTileUnderObject(object);
    }

    public boolean isColliding(GameObject object1, GameObject object2) {
        return distanceBetween(object1, object2) <= object1.getSize() + object2.getSize();
    }

    public static double distanceBetween(GameObject object1, GameObject object2) {
        return distanceBetween(object1.getPosition(), object2.getPosition());
    }

    public static double distanceBetween(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.y - point2.y, 2) + Math.pow(point1.x - point2.x, 2));
    }
}