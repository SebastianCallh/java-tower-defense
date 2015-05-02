package se.liu.ida.tddd78.towerdefense.entities.basic;

import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.entities.abstracts.GameEntity;
import se.liu.ida.tddd78.towerdefense.entities.character.Character;
import se.liu.ida.tddd78.towerdefense.entities.defense.Defense;
import se.liu.ida.tddd78.towerdefense.entities.monster.Monster;
import se.liu.ida.tddd78.towerdefense.entities.projectile.Projectile;

import java.util.*;

/**
 * The base class of everything that can be placed on the board.
 */
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

    public Iterable<GameEntity> getAll() {
        List<GameEntity> gameEntities = new ArrayList<>();
        gameEntities.addAll(this.characters);
        gameEntities.addAll(this.monsters);
        gameEntities.addAll(this.defenses);
            gameEntities.addAll(this.projectiles);
        return Collections.unmodifiableList(gameEntities);
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

    private List<Command> removeObsoleteObjects(Iterable<? extends GameEntity> list) {
        List<Command> removeCommands = new ArrayList<>();
        Iterator<? extends GameEntity> iterator = list.iterator();
        while (iterator.hasNext()) {
            GameEntity gameEntity = iterator.next();
            if (gameEntity.isRemoved()) {
                removeCommands.add(gameEntity.remove());
                iterator.remove();
            }
        }
        return removeCommands;
    }
}