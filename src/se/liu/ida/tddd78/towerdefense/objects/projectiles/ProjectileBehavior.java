package se.liu.ida.tddd78.towerdefense.objects.projectiles;

        import se.liu.ida.tddd78.towerdefense.Board;
        import se.liu.ida.tddd78.towerdefense.Collision;

/**
 * Created by Seba on 2015-03-01.
 */
public class ProjectileBehavior {
    private Board board;

    public ProjectileBehavior(Board board) {
        this.board = board;
    }

    public void behave() {
        for (Projectile projectile : this.board.getGameObjects().getProjectiles()) {
            if (projectile.getTarget() != null &&
                    Collision.distanceBetween(projectile, projectile.getTarget()) < projectile.getSize()) {
                projectile.getTarget().removeHealth(projectile.getDamage());
                projectile.setRemoved(true);
            }
        }
    }
}
