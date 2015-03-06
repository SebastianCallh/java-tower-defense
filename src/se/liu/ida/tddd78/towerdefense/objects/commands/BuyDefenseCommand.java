package se.liu.ida.tddd78.towerdefense.objects.commands;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.objects.defense.BasicDefense;
import se.liu.ida.tddd78.towerdefense.objects.defense.Defense;
import se.liu.ida.tddd78.towerdefense.objects.defense.DefenseFactory;
import se.liu.ida.tddd78.towerdefense.objects.defense.DefenseType;

/**
 * Created by Seba on 2015-03-05.
 */
public class BuyDefenseCommand implements Command {
    @Override
    public void execute(Board board) {
        Defense defense = DefenseFactory.makeDefense(DefenseType.SMALL);
        defense.setPosition(board.getPlayer().getPosition());
        board.getGameObjects().add(defense);
    }
}
