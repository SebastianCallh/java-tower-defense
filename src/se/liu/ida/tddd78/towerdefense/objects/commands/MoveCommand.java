package se.liu.ida.tddd78.towerdefense.objects.commands;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.Player;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.objects.basic.Direction;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by Seba on 2015-03-05.
 */
public class MoveCommand implements Command {
    private double angle;
    //SOUTH/WEST inverted since positive values move you down on the screen, not up
    //TODO: Check if the diagonal directions are really needed
    private static final Map<Direction, Double> ANGLE_MAP = new EnumMap<>(Direction.class);

    static {
        ANGLE_MAP.put(Direction.EAST, 0.0);
        ANGLE_MAP.put(Direction.SOUTHEAST, Math.PI / 4);
        ANGLE_MAP.put(Direction.SOUTH, Math.PI / 2);
        ANGLE_MAP.put(Direction.SOUTHWEST, 3 * Math.PI / 4);
        ANGLE_MAP.put(Direction.WEST, Math.PI);
        ANGLE_MAP.put(Direction.NORTHWEST, 5 * Math.PI / 4);
        ANGLE_MAP.put(Direction.NORTH, 3 * Math.PI / 2);
        ANGLE_MAP.put(Direction.NORTHEAST, 7 * Math.PI / 4);
    }

    public MoveCommand(Direction direction) {
        this.angle = ANGLE_MAP.get(direction);
    }

    @Override
    public void execute(Player player, Board board) {
        player.getCharacter().move(this.angle);
    }
}
