package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.Player;

/**
 * Created by Seba on 2015-03-05.
 */
public interface Command {
    void execute(Player player, Board board);
}
