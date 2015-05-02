package se.liu.ida.tddd78.towerdefense.objects.layout;

import se.liu.ida.tddd78.towerdefense.objects.basic.Grid;
import se.liu.ida.tddd78.towerdefense.objects.tile.Tile;
import se.liu.ida.tddd78.towerdefense.utils.Pathfinder;

import java.util.Collection;
import java.util.Map;

/**
 * Keeps track of the map layout and the pathable terrain.
 */
public final class Layout {
    private String name;
    private Grid<Tile> grid;
    private Tile spawn;
    private Tile goal;
    private Map<Tile, Tile> path;

    public Layout(String name, Grid<Tile> grid, Tile spawn, Tile goal) {
        assert name != null && !name.isEmpty();
        assert grid != null;
        assert spawn != null;
        assert goal != null;

        this.name = name;
        this.grid = grid;
        this.spawn = spawn;
        this.goal = goal;
        this.path = Pathfinder.floodFill(this, goal.getGridPosition());
    }

    public String getName() {
        return this.name;
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

}