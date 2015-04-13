package se.liu.ida.tddd78.towerdefense.objects.commands;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.Player;
import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.GameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.defense.Defense;
import se.liu.ida.tddd78.towerdefense.objects.defense.DefenseFactory;
import se.liu.ida.tddd78.towerdefense.objects.tile.Tile;
import se.liu.ida.tddd78.towerdefense.utils.Collision;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Seba on 2015-03-05.
 */
public class BuyCommand implements Command {
    private static final Logger LOG = Logger.getLogger(BuyCommand.class.getName());

    @Override
    public void execute(Player player, Board board) {
        if (player.isReadyForAction()) {
            Defense defense;
            try {
                defense = DefenseFactory.makeDefense(player.getSelectedDefense());
            } catch (TypeNotSupportedException e) {
                LOG.log(Level.WARNING, "Unable to create tower of unsupported type", e);
                return;
            }

            if (player.getMoney() >= defense.getCost()) {
                defense.setPosition(player.getCharacter().getPosition());
                List<Defense> defenses = board.getGameObjects().getDefenses();
                for (GameObject object: defenses) {
                    if (Collision.isColliding(defense, object)) {
                        return;
                    }
                }

                //TODO:Will probably act out if defense size is bigger than tile
                Map<Tile, Tile> path = board.getPath();
                Point defensePos = defense.getPosition();
                for (Entry<Tile, Tile> entry : path.entrySet()) {
                    Point tilePos = entry.getValue().getCenter();
                    double angle = Math.atan2(tilePos.getY() - defensePos.getY(), tilePos.getX() - defensePos.getX());
                    Point closestPos = new Point(defensePos.getX() + (Math.cos(angle) * defense.getSize()),
                            defensePos.getY() + (Math.sin(angle) * defense.getSize()));

                    if (Objects.equals(board.getTileUnder(closestPos), entry.getValue())) {
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
