package se.liu.ida.tddd78.towerdefense.objects.commands;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.Player;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.objects.defense.DefenseType;

/**
 * Created by Seba on 2015-03-24.
 */
public class SelectCommand implements Command {
    private DefenseType type;

    public SelectCommand(DefenseType type) {
        this.type = type;
    }

    @Override
    public void execute(Player player, Board board) {
        player.setSelectedDefense(this.type);
    }
}
