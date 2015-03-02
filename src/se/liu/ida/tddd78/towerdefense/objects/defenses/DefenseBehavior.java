package se.liu.ida.tddd78.towerdefense.objects.defenses;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.Collision;
import se.liu.ida.tddd78.towerdefense.objects.monsters.Monster;
import se.liu.ida.tddd78.towerdefense.objects.projectiles.Projectile;
import se.liu.ida.tddd78.towerdefense.objects.projectiles.ProjectileFactory;
import se.liu.ida.tddd78.towerdefense.objects.projectiles.ProjectileType;

/**
 * Created by Seba on 2015-03-01.
 */
public class DefenseBehavior {
    private Board board;

    public DefenseBehavior(Board board) {
        this.board = board;
    }

    public void behave() {
        for (Defense defense : this.board.getGameObjects().getDefenses()) {
            if (defense.getTarget() != null && !defense.getTarget().isRemoved()) {
                if (Collision.distanceBetween(defense, defense.getTarget()) > defense.getRange()) {
                    defense.setTarget(null);
                }
                if (!defense.isCoolingDown()) {
                    this.board.getGameObjects().add(defense.getProjectile());
                    defense.coolDown();
                }
            } else {
                for (Monster monster : this.board.getGameObjects().getMonsters()) {
                    if (Collision.distanceBetween(defense, monster) < defense.getRange()) {
                        defense.setTarget(monster);
                    }
                }
            }
        }
    }
}
