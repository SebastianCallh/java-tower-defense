package se.liu.ida.tddd78.towerdefense.utils;

import se.liu.ida.tddd78.towerdefense.objects.Layout;
import se.liu.ida.tddd78.towerdefense.objects.Tile;
import se.liu.ida.tddd78.towerdefense.objects.TileType;

import java.util.*;

/**
 * Created by Seba on 2015-02-06.
 */
public class Pathfinder {
    public static Map<Tile, Tile> floodFill(Layout layout, int x, int y) {
        Map<Tile, Tile> path = new HashMap<Tile, Tile>();
        Queue<Tile> queue = new LinkedList<Tile>();
        queue.add(layout.getTile(x, y));

        while (!queue.isEmpty()) {
            Tile current = queue.remove();
            for (Tile tile : layout.getNeighbors(current.getPosition().x, current.getPosition().y)) {
                if (tile.getType() == TileType.PATH &&
                        !path.containsKey(tile)) {
                    queue.add(tile);
                    path.put(tile, current);
                }
            }
        }
        return path;
    }
}