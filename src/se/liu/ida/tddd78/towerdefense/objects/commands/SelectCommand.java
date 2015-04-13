package se.liu.ida.tddd78.towerdefense.objects.commands;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.Player;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.objects.defense.DefenseType;

/**
 * Sets the players selected defense upon execution.
 */
public class SelectCommand implements Command {
    DefenseType type;

    public SelectCommand(DefenseType type) {
        this.type = type;
    }

    @Override
    public void execute(Player player, Board board) {
        player.setSelectedDefense(this.type);
    }
}
