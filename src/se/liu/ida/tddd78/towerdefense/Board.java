package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.interfaces.Observer;
import se.liu.ida.tddd78.towerdefense.objects.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.Layout;
import se.liu.ida.tddd78.towerdefense.objects.Theme;
import se.liu.ida.tddd78.towerdefense.objects.basic.GameObjects;
import se.liu.ida.tddd78.towerdefense.objects.defenses.Defense;
import se.liu.ida.tddd78.towerdefense.objects.defenses.DefenseFactory;
import se.liu.ida.tddd78.towerdefense.objects.defenses.DefenseType;
import se.liu.ida.tddd78.towerdefense.objects.monsters.Monster;
import se.liu.ida.tddd78.towerdefense.objects.monsters.MonsterFactory;
import se.liu.ida.tddd78.towerdefense.objects.monsters.MonsterType;
import se.liu.ida.tddd78.towerdefense.objects.tiles.Tile;

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

    public Board(Layout layout, Theme theme) {
        this.layout = layout;
        this.theme = theme;
        this.observers = new ArrayList<Observer>();

        //TEST STUFF
        Defense defense = DefenseFactory.makeDefense(DefenseType.SMALL);
        defense.setPosition(60, 60);
        Monster monster = MonsterFactory.makeMonster(MonsterType.SMALL);
        Tile spawn = this.layout.getSpawn();
        monster.setPosition(spawn.getPosition().x, spawn.getPosition().y);
        this.gameObjects.add(defense);
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
        for (GameObject object : this.getGameObjects().getAll()) {
            object.update();
        }
    }
}
