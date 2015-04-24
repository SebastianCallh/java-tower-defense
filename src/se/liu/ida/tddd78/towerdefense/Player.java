package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.interfaces.Observer;
import se.liu.ida.tddd78.towerdefense.objects.basic.Timer;
import se.liu.ida.tddd78.towerdefense.objects.character.Character;
import se.liu.ida.tddd78.towerdefense.objects.character.CharacterFactory;
import se.liu.ida.tddd78.towerdefense.objects.character.CharacterType;
import se.liu.ida.tddd78.towerdefense.objects.defense.DefenseType;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of the players stats.
 */
public class Player {
    private static final int MINIMIUM_ACTION_INTERVAL = 500;

    private int lives;
    private int money;
    private Character character;
    private DefenseType selectedDefense;
    private Timer actionTimer;
    private List<Observer> scoreObservers;

    public int getLives() {
        return this.lives;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        assert money >= 0;

        this.money = money;
        notifyPlayerChanged();
    }

    public void removeMoney(int amount) {
        this.setMoney(this.money - amount);
    }

    public Character getCharacter() {
        return this.character;
    }

    public DefenseType getSelectedDefense() {
        return this.selectedDefense;
    }

    public void setSelectedDefense (DefenseType type) {
        this.selectedDefense = type;
        notifyPlayerChanged();
    }

    public boolean isReadyForAction() {
        return this.actionTimer.hasCompleted();
    }

    public void resetActionTimer() {
        this.actionTimer.reset();
    }

    public void setLives(int amount) {
        assert amount >= 0;

        this.lives = amount;
        notifyPlayerChanged();
    }

    public void removeLives(int amount) {
        this.setLives(this.lives - amount);
    }

    private void notifyPlayerChanged() {
        this.scoreObservers.forEach(Observer::onNotify);
    }

    public void addPlayerObserver(Observer observer) {
        this.scoreObservers.add(observer);
    }

    public Player() throws TypeNotSupportedException {
        this.money = 0;
        this.character = CharacterFactory.makeCharacter(CharacterType.PLAYER);
        this.selectedDefense = DefenseType.SMALL;
        this.actionTimer = new Timer(MINIMIUM_ACTION_INTERVAL);
        this.scoreObservers = new ArrayList<>();
    }
}