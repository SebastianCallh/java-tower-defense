package se.liu.ida.tddd78.towerdefense.interfaces;

import se.liu.ida.tddd78.towerdefense.Board;

/**
 * Created by Seba on 2015-03-05.
 */
public interface Command {
    public void execute(Board board);
}
