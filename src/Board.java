import se.liu.ida.tddd78.towerdefense.interfaces.Observer;
import se.liu.ida.tddd78.towerdefense.objects.*;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.monsters.MonsterFactory;
import se.liu.ida.tddd78.towerdefense.utils.Pathfinder;

import java.util.*;

/**
 * Created by Seba on 2015-01-23.
 */
public class Board {
    private Layout layout;
    private Theme theme;
    private Map<Tile, Tile> path;
    private List<AbstractGameObject> gameObjects = new ArrayList<AbstractGameObject>();
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

    public List<AbstractGameObject> getGameObjects() {
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

        AbstractGameObject monster = MonsterFactory.makeMonster(GameObjectType.MONSTER_SMALL);
        Point spawnPoint = this.layout.getSpawn().getPosition();
        monster.setPosition(spawnPoint.x * this.tileSize, spawnPoint.y * this.tileSize);
        this.gameObjects.add(monster);
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
