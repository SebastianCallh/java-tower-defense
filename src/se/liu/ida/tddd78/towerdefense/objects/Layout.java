package se.liu.ida.tddd78.towerdefense.objects;

import se.liu.ida.tddd78.towerdefense.exceptions.LayoutParseException;
import se.liu.ida.tddd78.towerdefense.objects.basic.Grid;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.tile.Tile;
import se.liu.ida.tddd78.towerdefense.objects.tile.TileType;
import se.liu.ida.tddd78.towerdefense.utils.Pathfinder;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Seba on 2015-02-10.
 */
public final class Layout {
    private static final Logger LOG = Logger.getLogger(Layout.class.getName());

    private Grid<Tile> grid;
    private Tile spawn;
    private Tile goal;
    private Map<Tile, Tile> path;

    public enum Type {
        STANDARD
    }

    public Tile getSpawn() {
        return this.spawn;
    }

    public Tile getGoal() {
        return this.goal;
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

    public Collection<Tile> getNeighbors(int x, int y) {
        return this.grid.getNeighbors(x, y);
    }

    public Map<Tile, Tile> getPath() {
        return this.path;
    }

    private Layout(Grid<Tile> grid, Tile spawn, Tile goal) {
        this.grid = grid;
        this.spawn = spawn;
        this.goal = goal;
        this.path = Pathfinder.floodFill(this, goal.getGridPosition());
    }

    private static final Map<Type, Layout> LAYOUT_TYPE_MAP = new EnumMap<>(Type.class);

    static {
        try {
            LAYOUT_TYPE_MAP.put(Type.STANDARD, readLayout("layouts/standard"));
        } catch (LayoutParseException e) {
            LOG.log(Level.SEVERE, "Failed to parse default layout", e);
            JOptionPane.showMessageDialog(null, "Unable to load standard map, please specify a valid map to load!");
            // TODO: Rework to not load the layout here!
            System.exit(1);
        }
    }

    public static Layout get(Type type) { return LAYOUT_TYPE_MAP.get(type); }

    //*Reads a grid text file (20*20 chars) for a grid of tiles*//
    private static Layout readLayout(String path) throws LayoutParseException {
        try {
            List<String> fileContent = Files.readAllLines(Paths.get(path));
            //TODO:Regex. Convert all int sizes to dimensions
            String header = fileContent.get(0);
            int x = 0, y = 0,
                    width = Integer.valueOf(header.substring(0, 2)),
                    height = Integer.valueOf(header.substring(2,4));
            Grid<Tile> grid = new Grid<>(Tile.class, width, height);
            Tile spawnTile = null, goalTile = null;

            for (String line : fileContent.subList(1, fileContent.size())) {
                for (char c: line.toCharArray()) {
                    Tile tile = new Tile(charToTileType(c), new Point(x, y));
                    if (tile.getType() == TileType.SPAWN) {
                        spawnTile = tile;
                    } else if (tile.getType() == TileType.GOAL) {
                        goalTile = tile;
                    }
                    grid.set(x, y, tile);
                    x++;
                }
                x = 0;
                y++;
            }
            if (spawnTile == null || goalTile == null) {
                throw new LayoutParseException("Spawn tile or goal tile missing");
            }
            return new Layout(grid, spawnTile, goalTile);
        } catch (IOException e) {
            System.out.println("Error reading layout file" + e.getMessage());
            return null;
        }
    }

    private static TileType charToTileType(char c) {
        switch (c) {
            case 'S':
                return TileType.SPAWN;
            case 'G':
                return TileType.GOAL;
            case 'P':
                return TileType.PATH;
            case 'B':
                return TileType.BLOCKED;
            default:
                throw new IllegalArgumentException("File contains unrecognized tiles");
        }
    }
}