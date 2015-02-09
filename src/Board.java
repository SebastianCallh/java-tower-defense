import factories.MonsterFactory;
import objects.Monster;
import objects.Theme;
import objects.Tile;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Seba on 2015-01-23.
 */
public class Board {
    private Grid<Tile> grid;
    private Graph<Tile> graph;
    private Theme theme;
    private Tile spawnTile;
    private Tile goalTile;
    private List<Monster> monsters = new ArrayList<Monster>();
    private MonsterFactory monsterFactory;

    public final static int TILE_SIZE = 40;
    public final static int GRID_SIZE = 800;

    public int getWidth() {
        return this.grid.getWidth();
    }

    public int getHeight() {
        return this.grid.getHeight();
    }

    public int getMonsterCount() {
        return this.monsters.size();
    }

    public Monster getMonster(int i) {
        return this.monsters.get(i);
    }

    public Theme getTheme() {
        return this.theme;
    }

    public Tile getTile(int x, int y) {
        return this.grid.get(x, y);
    }

    public Board(int width, int height, Theme theme) {
        this.grid = this.createLayout(width, height);
        this.monsterFactory= new MonsterFactory();
        this.theme = theme;

        Monster monster = this.monsterFactory.makeMonster(this.monsterFactory.getTypes()[0]);
        monster.setPosition(this.spawnTile.getCenter());
        this.monsters.add(monster);
    }

    private Grid<Tile> createLayout(int width, int height) {
        Grid<Tile> grid = new Grid<Tile>(Tile.class, width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x == width / 2 || y == height / 2 ||
                        (0 <= x && x < 40 && y == 15)) {
                    grid.set(x, y, new Tile(Tile.Type.CLEAR));
                } else {
                    grid.set(x, y, new Tile(Tile.Type.BLOCKED));
                }
            }
        }
        this.spawnTile = new Tile(Tile.Type.SPAWN);
        this.goalTile = new Tile(Tile.Type.GOAL);
        grid.set(0, height / 2, this.spawnTile);
        grid.set(width / 2, 0, this.goalTile);

        return grid;
    }

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
            this.grid = (E[][]) Array.newInstance(c, width, height);
        }
    }

    public class Graph<E> {
        Map<String, Node<E>> nodes;

        public void addNode(String edge, Node<E> node) {
            this.nodes.put(edge, node);
        }

        public class Node<E> {
            private E value;
            private List<String> edges;

            public Node(E value, List<String> edges) {
                this.value = value;
                this.edges = edges;
            }
        }
    }
}
