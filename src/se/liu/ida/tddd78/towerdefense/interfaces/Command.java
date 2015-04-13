package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.Player;

/**
 * Allows implementing class to be executed as a command by another class.
 */
public interface Command {
    void execute(Player player, Board board);
}
