import java.lang.reflect.Array;

/**
 * Created by Seba on 2015-01-23.
 */
public class Grid<E> {
    E[][] grid = null;

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

    public Grid(Class<E> c, int width, int height) {
        this.grid = (E[][])Array.newInstance(c, width, height);
    }
}
