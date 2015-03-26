package se.liu.ida.tddd78.towerdefense.objects.commands;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.Player;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.defense.Defense;
import se.liu.ida.tddd78.towerdefense.objects.defense.DefenseFactory;
import se.liu.ida.tddd78.towerdefense.objects.tile.Tile;
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
                    if (board.getTileUnder(defense) == tile) {
                        return;
                    }
                }
                //TODO:Will probably act out if defense size is bigger than tile
                Map<Tile, Tile> path = board.getPath();
                Point defensePos = defense.getPosition();
                for (Map.Entry<Tile, Tile> entry : path.entrySet()) {
                    Point tilePos = entry.getValue().getCenter();
                    double angle = Math.atan2(tilePos.y - defensePos.y, tilePos.x - defensePos.x);
                    Point closestPos = new Point(defensePos.x + (Math.cos(angle) * defense.getSize()),
                            defensePos.y + (Math.sin(angle) * defense.getSize()));

                    if (board.getTileUnder(closestPos) == entry.getValue()) {
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
