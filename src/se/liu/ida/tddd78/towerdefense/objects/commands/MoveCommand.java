package se.liu.ida.tddd78.towerdefense.objects.commands;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.objects.basic.Direction;

/**
 * Created by Seba on 2015-03-05.
 */
public class MoveCommand implements Command {
    private Direction direction;
    public MoveCommand(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void execute(Board board) {
        board.getPlayer().move(this.direction);
    }
}
