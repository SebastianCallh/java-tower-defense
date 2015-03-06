package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.interfaces.Observer;
import se.liu.ida.tddd78.towerdefense.objects.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.Layout;
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
    private List<Observer> observers;
    private Character player;

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

    public Tile getTileUnderObject(GameObject object) {
        return this.getTile(Math.floorDiv((int)object.getPosition().x, (int)Tile.TILE_SIZE),
                Math.floorDiv((int)object.getPosition().y, (int)Tile.TILE_SIZE));
    }

    public GameObjects getGameObjects() {
        return this.gameObjects;
    }

    public Character getPlayer() {
        return this.player;
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

    public Board(Layout layout, Theme theme, Character player) {
        this.layout = layout;
        this.theme = theme;
        this.observers = new ArrayList<Observer>();
        this.player = player;
        player.setPosition(100, 100);
        this.getGameObjects().add(this.player);

        Defense defense = DefenseFactory.makeDefense(DefenseType.BIG);
        defense.setPosition(60, 160);
        this.getGameObjects().add(defense);
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    //*The method that runs every game-loop-update*//
    public void update() {
        for (GameObject object : this.getGameObjects().getAll()) {
            object.update(this);
        }
        this.getGameObjects().removeObsoleteObjects();
    }
}
