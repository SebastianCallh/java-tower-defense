package se.liu.ida.tddd78.towerdefense.utils;

import se.liu.ida.tddd78.towerdefense.objects.Layout;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.tiles.Tile;
import se.liu.ida.tddd78.towerdefense.objects.tiles.TileType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by Seba on 2015-02-06.
 */
public class Pathfinder {
    public static Map<Tile, Tile> floodFill(Layout layout, Point point) {
        Map<Tile, Tile> path = new HashMap<Tile, Tile>();
        Queue<Tile> queue = new LinkedList<Tile>();
        queue.add(layout.getTile((int)point.x, (int)point.y));

        while (!queue.isEmpty()) {
            Tile current = queue.remove();
            for (Tile tile : layout.getNeighbors((int)current.getTilePosition().x, (int)current.getTilePosition().y)) {
                if ((tile.getType() == TileType.PATH ||
                     tile.getType() == TileType.SPAWN ||
                     tile.getType() == TileType.GOAL) &&
                        !path.containsKey(tile)) {
                    queue.add(tile);
                    path.put(tile, current);
                }
            }
        }
        return path;
    }
}