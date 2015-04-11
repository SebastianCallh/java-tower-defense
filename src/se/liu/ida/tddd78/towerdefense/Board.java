package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.interfaces.GameObserver;
import se.liu.ida.tddd78.towerdefense.objects.Layout;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.theme.Theme;
import se.liu.ida.tddd78.towerdefense.objects.basic.GameObjects;
import se.liu.ida.tddd78.towerdefense.objects.character.Character;
import se.liu.ida.tddd78.towerdefense.objects.defense.Defense;
import se.liu.ida.tddd78.towerdefense.objects.defense.DefenseFactory;
import se.liu.ida.tddd78.towerdefense.objects.defense.DefenseType;
import se.liu.ida.tddd78.towerdefense.objects.tile.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Seba on 2015-01-23.
 */
public class Board {
    private Layout layout;
    private Theme theme;
    private GameObjects gameObjects = new GameObjects();
    private List<GameObserver> gameObservers;

    //TODO:Make size of window/board/tiles work solely out of the layout files size
    public final static int BOARD_SIZE = 400;

    public int getWidth() {
        return this.layout.getWidth();
    }

    public int getHeight() {
        return this.layout.getHeight();
    }

    public Tile getTile(int x, int y) {
        return this.layout.getTile(x, y);
    }

    public Tile getTileUnder(GameObject object) {
        return this.getTileUnder(object.getPosition());
    }

    public Tile getTileUnder(Point point) {
        return this.getTile(Math.floorDiv((int)point.x, (int)Tile.TILE_SIZE),
                Math.floorDiv((int)point.y, (int)Tile.TILE_SIZE));
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

    public Board(Layout layout, Theme theme, Character playerCharacter) {
        this.layout = layout;
        this.theme = theme;
        this.gameObservers = new ArrayList<>();
        this.reset(playerCharacter);
    }

    public void addObserver(GameObserver gameObserver) {
        this.gameObservers.add(gameObserver);
    }

    public void removeObserver(GameObserver gameObserver) {
        this.gameObservers.remove(gameObserver);
    }

    //*The method that runs every game-loop-update*//
    public void update() {
        for (GameObject object : this.getGameObjects().getAll()) {
            object.update(this);
        }
    }

    public void reset(Character playerCharacter) {
        this.gameObjects = new GameObjects();
        this.getGameObjects().add(playerCharacter);
    }
}
