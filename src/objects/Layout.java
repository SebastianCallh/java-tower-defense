package objects;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Seba on 2015-02-10.
 */
public class Layout {
    private Grid<Tile> grid;
    private Tile spawnTile;
    private Tile goalTile;

    public enum Type {
        STANDARD
    }

    public Tile getSpawnTile() {
        return spawnTile;
    }

    public Tile getGoalTile() {
        return goalTile;
    }

    public int getWidth() {
        return this.grid.getWidth();
    }

    public int getHeight() {
        return this.grid.getHeight();
    }

    public Tile getTile(int x, int y) {
        return this.grid.get(x, y);
    }

    private Layout(Grid<Tile> grid, Tile spawnTile, Tile goalTile) {
        this.grid = grid;
        this.spawnTile = spawnTile;
        this.goalTile = goalTile;
    }

    private static Map<Type, Layout> layoutTypeMap = new HashMap<Type, Layout>() {{
        put(Type.STANDARD, readLayout("layouts/standard"));
    }};

    public static Type[] getTypes() { return Type.values(); }

    public static Layout get(Type type) { return layoutTypeMap.get(type); }

    //*Reads a grid text file (20*20 chars) for a grid of tiles*//
    private static Layout readLayout(String path) {
        try {
            List<String> fileContent = Files.readAllLines(Paths.get(path));
            //TODO:Regex. Convert all int sizes to dimensions
            String header = fileContent.get(0);
            int x = 0, y = 0,
                    width = Integer.valueOf(header.substring(0, 2)),
                    height = Integer.valueOf(header.substring(2,4));
            Grid<Tile> grid = new Grid<Tile>(Tile.class, width, height);
            Tile spawnTile = null, goalTile = null;

            for (String line : fileContent.subList(1, fileContent.size())) {
                for (char c: line.toCharArray()) {
                    Tile tile = new Tile(charToTileType(c), new Point(x, y));
                    if (tile.getType() == Tile.Type.SPAWN) {
                        spawnTile = tile;
                    } else if (tile.getType() == Tile.Type.GOAL) {
                        goalTile = tile;
                    }
                    grid.set(x, y, tile);
                    x++;
                }
                x = 0;
                y++;
            }
            if (spawnTile == null || goalTile == null) {
                throw new RuntimeException("Invalid format in layout file");
            }
            return new Layout(grid, spawnTile, goalTile);
        } catch (IOException e) {
            System.out.println("Error reading layout file" + e.getMessage());
            return null;
        }
    }

    private static Tile.Type charToTileType(char c) {
        switch (c) {
            case 'S':
                return Tile.Type.SPAWN;
            case 'G':
                return Tile.Type.GOAL;
            case 'P':
                return Tile.Type.PATH;
            default:
                return Tile.Type.BLOCKED;
        }
    }
}