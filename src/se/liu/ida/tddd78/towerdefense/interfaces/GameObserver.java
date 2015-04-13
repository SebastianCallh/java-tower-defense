package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.Game;

/**
 * Allows the implementing class to be notified upon changes in the game.
 */
public interface GameObserver {
    void onNotify(Game game);
}
