package se.liu.ida.tddd78.towerdefense.entities.commands;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.Player;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;

/**
 * Adds the bounty to the players money upon execution.
 */
public class BountyCommand implements Command{
    private int bounty;

    public BountyCommand(int bounty) {
        this.bounty = bounty;
    }

    @Override
    public void execute(Player player, Board board) {
        player.setMoney(player.getMoney() + bounty);
    }
}
