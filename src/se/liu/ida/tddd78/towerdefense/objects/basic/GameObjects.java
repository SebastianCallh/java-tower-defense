package se.liu.ida.tddd78.towerdefense.objects.basic;

import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.character.Character;
import se.liu.ida.tddd78.towerdefense.objects.defense.Defense;
import se.liu.ida.tddd78.towerdefense.objects.monster.Monster;
import se.liu.ida.tddd78.towerdefense.objects.projectile.Projectile;

import java.util.*;

public class GameObjects
{
    private List<Character> characters;
    private List<Monster> monsters;
    private List<Defense> defenses;
    private List<Projectile> projectiles;

    public GameObjects() {
        this.characters = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.defenses = new ArrayList<>();
        this.projectiles = new ArrayList<>();
    }

    public Collection<Monster> getMonsters() {
        return Collections.unmodifiableList(this.monsters);
    }

    public List<Defense> getDefenses() {
        return Collections.unmodifiableList(this.defenses);
    }

    public Iterable<GameObject> getAll() {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.addAll(this.characters);
        gameObjects.addAll(this.monsters);
        gameObjects.addAll(this.defenses);
            gameObjects.addAll(this.projectiles);
        return Collections.unmodifiableList(gameObjects);
    }

    public void add(Character character) {
        this.characters.add(character);
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

    public List<Command> removeObsoleteObjects() {
        List<Command> removeCommands = removeObsoleteObjects(this.monsters);
        removeCommands.addAll(removeObsoleteObjects(this.defenses));
        removeCommands.addAll(removeObsoleteObjects(this.projectiles));
        return removeCommands;
    }

    private List<Command> removeObsoleteObjects(Iterable<? extends GameObject> list) {
        List<Command> removeCommands = new ArrayList<>();
        Iterator<? extends GameObject> iterator = list.iterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            if (gameObject.isRemoved()) {
                removeCommands.add(gameObject.remove());
                iterator.remove();
            }
        }
        return removeCommands;
    }
}