package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.Game;

/**
 * Created by Seba on 2015-02-12.
 */
public interface GameObserver {
    public void onNotify(Game game);
}
