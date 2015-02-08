import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seba on 2015-01-23.
 */
public class Level {
    private Grid<Tile> grid;
    private Theme theme;
    private List<Monster> monsters = new ArrayList<Monster>();

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


    public Level(int width, int height, Theme theme) {
        this.grid = this.createLayout(width, height);
        this.theme = theme;
        MonsterFactory factory = new MonsterFactory();
        this.monsters.add(factory.makeMonster(factory.getTypes()[0]));
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
        grid.set(0, height / 2, new Tile(Tile.Type.SPAWN));
        grid.set(width / 2, 0, new Tile(Tile.Type.GOAL));

        return grid;
    }
}
