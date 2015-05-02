package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.entities.layout.Layout;
import se.liu.ida.tddd78.towerdefense.entities.abstracts.GameEntity;
import se.liu.ida.tddd78.towerdefense.entities.basic.Point;
import se.liu.ida.tddd78.towerdefense.entities.theme.Theme;
import se.liu.ida.tddd78.towerdefense.entities.basic.GameObjects;
import se.liu.ida.tddd78.towerdefense.entities.character.Character;
import se.liu.ida.tddd78.towerdefense.entities.tile.Tile;

import java.util.Map;

/**
 * Keeps track of all game entities, the map layout and the theme.
 */
public class Board {
    private Layout layout;
    private Theme theme;
    private GameObjects gameObjects = new GameObjects();

    public final static int BOARD_SIZE = 400;

    public Board(Layout layout, Theme theme, Character playerCharacter) {
        assert layout != null;
        assert theme != null;
        assert playerCharacter != null;

        this.layout = layout;
        this.theme = theme;
        this.reset(playerCharacter);
    }

    public int getWidth() {
        return this.layout.getWidth();
    }

    public int getHeight() {
        return this.layout.getHeight();
    }

    public Tile getTile(int x, int y) {
        return this.layout.getTile(x, y);
    }

    public Tile getTileUnder(GameEntity entity) {
        return this.getTileUnder(entity.getPosition());
    }

    public Tile getTileUnder(Point point) {
        return this.getTile(Math.floorDiv((int) point.getX(), (int)Tile.TILE_SIZE),
                Math.floorDiv((int) point.getY(), (int)Tile.TILE_SIZE));
    }

    public GameObjects getGameObjects() {
        return this.gameObjects;
    }


    public Theme getTheme() {
        return this.theme;
    }

    public Map<Tile, Tile> getPath() {
        return this.layout.getPath();
    }

    public Tile getSpawn() {
        return this.layout.getSpawn();
    }

    public Tile getGoal() {
        return this.layout.getGoal();
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    //*The method that runs every game-loop-update*//
    public void update() {
        for (GameEntity object : this.gameObjects.getAll()) {
            object.update(this);
        }
    }

    public void reset(Character playerCharacter) {
        this.gameObjects = new GameObjects();
        this.gameObjects.add(playerCharacter);
    }
}
