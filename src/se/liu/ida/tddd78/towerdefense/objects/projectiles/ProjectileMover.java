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
            Point position = projectile.getPosition();

            double angle = Math.atan2(projectile.getTarget().y - projectile.getPosition().y,
                    projectile.getTarget().x - projectile.getPosition().x);

            int x = (int)(projectile.getPosition().x + Math.cos(angle) * projectile.getSpeed());
            int y = (int)(projectile.getPosition().y + Math.sin(angle) * projectile.getSpeed());
            projectile.setPosition(x, y);
        }
    }
}
