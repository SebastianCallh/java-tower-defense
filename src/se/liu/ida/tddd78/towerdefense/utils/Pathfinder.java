package se.liu.ida.tddd78.towerdefense.utils;

import se.liu.ida.tddd78.towerdefense.objects.Layout;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.tile.Tile;
import se.liu.ida.tddd78.towerdefense.objects.tile.TileType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Contains methods for finding the shortest paths on a layout.
 */
public final class Pathfinder {
    private Pathfinder() {
    }

    public static Map<Tile, Tile> floodFill(Layout layout, Point point) {
        Map<Tile, Tile> path = new HashMap<>();
        Queue<Tile> queue = new LinkedList<>();
        queue.add(layout.getTile((int) point.getX(), (int) point.getY()));

        while (!queue.isEmpty()) {
            Tile current = queue.remove();
            layout.getNeighbors((int) current.getGridPosition().getX(), (int) current.getGridPosition().getY()).stream().filter(tile -> (tile.getType() == TileType.PATH ||
                    tile.getType() == TileType.SPAWN ||
                    tile.getType() == TileType.GOAL) &&
                    !path.containsKey(tile)).forEach(tile -> {
                queue.add(tile);
                path.put(tile, current);
            });
        }
        return path;
    }
}