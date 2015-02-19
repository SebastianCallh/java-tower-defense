package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.interfaces.Observer;
import se.liu.ida.tddd78.towerdefense.objects.*;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.defenses.DefenseFactory;
import se.liu.ida.tddd78.towerdefense.objects.defenses.DefenseType;
import se.liu.ida.tddd78.towerdefense.objects.tiles.Tile;
import se.liu.ida.tddd78.towerdefense.utils.Pathfinder;

import java.util.*;

/**
 * Created by Seba on 2015-01-23.
 */
public class Board {
    private Layout layout;
    private Theme theme;
    private Map<Tile, Tile> path;
    private List<GameObject> gameObjects = new ArrayList<GameObject>();
    private int tileSize;
    private List<Observer> observers;

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

    public int getTileSize() {
        return this.tileSize;
    }

    public int getMonsterCount() {
        return this.gameObjects.size();
    }

    public List<GameObject> getGameObjects() {
        return Collections.unmodifiableList(this.gameObjects);
    }

    public Theme getTheme() {
        return this.theme;
    }

    public Board(Layout layout, Theme theme) {
        this.layout = layout;
        this.theme = theme;
        this.tileSize = BOARD_SIZE / this.layout.getWidth();
        this.observers = new ArrayList<Observer>();
        Tile goal = layout.getGoal();
        this.path = Pathfinder.floodFill(layout,
                goal.getPosition().x,
                goal.getPosition().y);

        GameObject defense = DefenseFactory.makeDefense(DefenseType.SMALL);
        defense.setPosition(60, 60);
        this.gameObjects.add(defense);
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    //*The method that runs every game-loop-update*//
    public void update() {

    }
}
