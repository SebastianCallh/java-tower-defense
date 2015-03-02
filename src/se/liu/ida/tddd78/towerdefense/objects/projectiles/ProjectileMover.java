package se.liu.ida.tddd78.towerdefense.objects.projectiles;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

public class ProjectileMover {
    private Board board;

    public ProjectileMover(final Board board) {
        this.board = board;
    }

    public void move() {
        for (Projectile projectile : this.board.getGameObjects().getProjectiles()) {
            double angle = Math.atan2(projectile.getTarget().getPosition().y - projectile.getPosition().y,
                    projectile.getTarget().getPosition().x - projectile.getPosition().x);

            double x = projectile.getPosition().x + Math.cos(angle) * projectile.getSpeed();
            double y = projectile.getPosition().y + Math.sin(angle) * projectile.getSpeed();
            projectile.setPosition(x, y);
        }
    }
}
