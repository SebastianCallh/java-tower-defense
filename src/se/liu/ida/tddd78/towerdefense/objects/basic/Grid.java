package se.liu.ida.tddd78.towerdefense.objects.basic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Seba on 2015-02-10.
 */

public class Grid<E> {
    private E[][] grid = null;

    public E get(int x, int y) {
        return this.grid[x][y];
    }

    public void set(int x, int y, E e) {
        this.grid[x][y] = e;
    }

    public int getWidth() {
        return this.grid.length;
    }

    public int getHeight() {
        return this.getWidth() > 0 ? this.grid[0].length : 0;
    }

    public Collection<E> getNeighbors(int x, int y) {
        Collection<E> neighbors = new ArrayList<>();
        neighbors.add(this.get(Math.min(this.getWidth() - 1, x + 1), y));
        neighbors.add(this.get(Math.max(0, x - 1), y));
        neighbors.add(this.get(x, Math.min(this.getHeight() - 1, y + 1)));
        neighbors.add(this.get(x, Math.max(0, y - 1)));
        return neighbors;
    }

    public Grid(Class<E> classType, int width, int height) {
        this.grid = (E[][]) Array.newInstance(classType, width, height);
    }
}

