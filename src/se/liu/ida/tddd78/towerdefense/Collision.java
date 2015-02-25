package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.objects.GameObject;
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
            double relativeX = Tile.TILE_SIZE / Math.max(object.getPosition().x % Tile.TILE_SIZE, 1);
            double relativeY = Tile.TILE_SIZE / Math.max(object.getPosition().y % Tile.TILE_SIZE, 1);
            return (relativeX > 1 / 3 && relativeX < 2 / 3 &&
                    relativeY > 1 / 3 && relativeY < 2 / 3);
        }
        return false;
    }

    public boolean isColliding(GameObject object, Tile tile) {
        return tile == board.getTileUnderObject(object);
    }

    public boolean isColliding(GameObject object1, GameObject object2) {
        return Math.sqrt(Math.pow(object1.getPosition().y - object2.getPosition().y, 2)
                + Math.pow(object1.getPosition().x - object2.getPosition().x, 2))
                <= object1.getSize() + object2.getSize();
    }
}