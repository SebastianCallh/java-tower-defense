package se.liu.ida.tddd78.towerdefense.objects.commands;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.Player;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.defense.BasicDefense;
import se.liu.ida.tddd78.towerdefense.objects.defense.Defense;
import se.liu.ida.tddd78.towerdefense.objects.defense.DefenseFactory;
import se.liu.ida.tddd78.towerdefense.objects.defense.DefenseType;
import se.liu.ida.tddd78.towerdefense.objects.tile.Tile;
import se.liu.ida.tddd78.towerdefense.objects.tile.TileType;
import se.liu.ida.tddd78.towerdefense.utils.Collision;

import java.util.List;
import java.util.Map;

/**
 * Created by Seba on 2015-03-05.
 */
public class BuyCommand implements Command {
    @Override
    public void execute(Player player, Board board) {
        if (player.isReadyForAction()) {
            Defense defense = DefenseFactory.makeDefense(player.getSelectedDefense());
            if (player.getMoney() >= defense.getCost()) {
                defense.setPosition(player.getCharacter().getPosition());
                List<Defense> defenses = board.getGameObjects().getDefenses();
                for (GameObject object: defenses) {
                    if (Collision.isColliding(defense, object)) {
                        return;
                    }
                }

                for (Map.Entry<Tile, Tile> tile : board.getPath().entrySet()) {
                    if (board.getTileUnderObject(defense) == tile) {
                        return;
                    }
                }

                board.getGameObjects().add(defense);
                player.removeMoney(defense.getCost());
                player.resetActionTimer();
            }
        }
    }
}
