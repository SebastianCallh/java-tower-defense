package se.liu.ida.tddd78.towerdefense.objects.basic;

import se.liu.ida.tddd78.towerdefense.objects.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.defenses.Defense;
import se.liu.ida.tddd78.towerdefense.objects.monsters.Monster;
import se.liu.ida.tddd78.towerdefense.objects.projectiles.Projectile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GameObjects
{
    private List<Monster> monsters;
    private List<Defense> defenses;
    private List<Projectile> projectiles;

    public GameObjects() {
        this.monsters = new ArrayList<Monster>();
        this.defenses = new ArrayList<Defense>();
        this.projectiles = new ArrayList<Projectile>();
    }

    public List<Monster> getMonsters() {
	return Collections.unmodifiableList(this.monsters);
    }

    public List<Defense> getDefenses() {
	return Collections.unmodifiableList(this.defenses);
    }

    public List<Projectile> getProjectiles() {
	return Collections.unmodifiableList(this.projectiles);
    }

    public List<GameObject> getAll() {
	List<GameObject> gameObjects = new ArrayList<GameObject>();
	gameObjects.addAll(this.monsters);
	gameObjects.addAll(this.defenses);
        gameObjects.addAll(this.projectiles);
	return Collections.unmodifiableList(gameObjects);
    }

    public void add(Monster monster) {
        this.monsters.add(monster);
    }

    public void add(Defense defense) {
        this.defenses.add(defense);
    }

    public void add(Projectile projectile) {
        this.projectiles.add(projectile);
    }

    public int size() {
        return this.getAll().size();
    }

	public void removeObsoleteObjects() {
		removeObsoleteObjects(this.monsters);
		removeObsoleteObjects(this.defenses);
		removeObsoleteObjects(this.projectiles);
	}

	private void removeObsoleteObjects(List<? extends GameObject> list) {
		Iterator<? extends GameObject> iterator = list.iterator();
		while (iterator.hasNext()) {
			GameObject gameObject = iterator.next();
			if (gameObject.isRemoved()) {
				iterator.remove();
			}
		}
	}
}
