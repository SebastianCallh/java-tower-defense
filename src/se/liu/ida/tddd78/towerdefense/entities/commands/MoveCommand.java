package se.liu.ida.tddd78.towerdefense.entities.commands;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.Player;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.entities.basic.Direction;

import java.util.EnumMap;
import java.util.Map;

/**
 * Moves the players character in a direction upon execution.
 */
public class MoveCommand implements Command {
    private double angle;
    //SOUTH/WEST inverted since positive values move you down on the screen, not up
    private static final Map<Direction, Double> ANGLE_MAP = new EnumMap<>(Direction.class);

    static {
        ANGLE_MAP.put(Direction.EAST, 0.0);
        ANGLE_MAP.put(Direction.SOUTH, Math.PI / 2);
        ANGLE_MAP.put(Direction.WEST, Math.PI);
        ANGLE_MAP.put(Direction.NORTH, 3 * Math.PI / 2);
    }

    public MoveCommand(Direction direction) {
        this.angle = ANGLE_MAP.get(direction);
    }

    @Override
    public void execute(Player player, Board board) {
        player.getCharacter().move(this.angle);
    }
}
