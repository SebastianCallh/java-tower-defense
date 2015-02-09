package util;

import objects.Tile;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by Seba on 2015-02-06.
 */
public class Pathfinder {
    public static Map<objects.Tile, objects.Tile> pathToOneFromAll(Tile[][] grid, int startX, int startY) {
        Map<objects.Tile, objects.Tile> paths = new HashMap<objects.Tile, objects.Tile>();
        Queue<objects.Tile> queue = new LinkedList<objects.Tile>();
        queue.add(grid[startY][startX]);

        for (Tile[] tile : grid) {
            
        }

        return paths;
    }

    /*private static Board.Grid<Tile> getNeighbors(int[][] grid) {

    }*/
}