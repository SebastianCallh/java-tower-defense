package util;

import objects.Grid;
import objects.Layout;
import objects.Tile;

import java.util.*;

/**
 * Created by Seba on 2015-02-06.
 */
public class Pathfinder {
    public static Map<Tile, Tile> floodFill(Layout layout, int x, int y) {
        Map<Tile, Tile> path = new HashMap<objects.Tile, objects.Tile>();
        Queue<Tile> queue = new LinkedList<objects.Tile>();
        queue.add(layout.getTile(x, y));

        while (!queue.isEmpty()) {
            Tile current = queue.remove();
            for (Tile tile : layout.getNeighbors(current.getPosition().x, current.getPosition().y)) {
                if (tile.getType() == Tile.Type.PATH &&
                        !path.containsKey(tile)) {
                    queue.add(tile);
                    path.put(tile, current);
                }
            }
        }
        return path;
    }
}