package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.interfaces.Observer;
import se.liu.ida.tddd78.towerdefense.objects.basic.Timer;
import se.liu.ida.tddd78.towerdefense.objects.character.Character;
import se.liu.ida.tddd78.towerdefense.objects.character.CharacterFactory;
import se.liu.ida.tddd78.towerdefense.objects.character.CharacterType;
import se.liu.ida.tddd78.towerdefense.objects.defense.DefenseType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seba on 2015-03-06.
 */
public class Player {
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
        this.money = money;
        notifyScoreChanged();
    }

    public void removeMoney(int amount) {
        this.setMoney(this.getMoney() - amount);
    }

    public Character getCharacter() {
        return this.character;
    }

    public DefenseType getSelectedDefense() {
        return this.selectedDefense;
    }

    public boolean isReadyForAction() {
        return this.actionTimer.hasCompleted();
    }

    public void resetActionTimer() {
        this.actionTimer.reset();
    }

    public void setLives(int amount) {
        this.lives = amount;
        notifyScoreChanged();
    }

    public void removeLives(int amount) {
        this.setLives(this.getLives() - amount);
    }

    private void notifyScoreChanged() {
        for (Observer observer : this.scoreObservers) {
            observer.onNotify();
        }
    }

    public void addScoreObserver(Observer observer) {
        this.scoreObservers.add(observer);
    }

    public Player() {
        this.money = 0;
        this.character = CharacterFactory.makeCharacter(CharacterType.PLAYER);
        this.selectedDefense = DefenseType.SMALL;
        this.actionTimer = new Timer(500);
        this.scoreObservers = new ArrayList<>();
    }
}