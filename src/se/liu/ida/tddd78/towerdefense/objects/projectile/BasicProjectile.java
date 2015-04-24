package se.liu.ida.tddd78.towerdefense.objects.projectile;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.utils.Collision;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.AbstractMovable;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.monster.Monster;

/**
 * Basic implementation of a defense projectile to be fired at a monster.
 */
public class BasicProjectile extends AbstractMovable implements Projectile {
    private int damage;
    private ProjectileType type;
    private Monster target;

    public BasicProjectile(int damage, int size, int speed, ProjectileType type) {
        super(new Point(0, 0), size, speed);

        assert damage > 0;
        assert size > 0;
        assert speed > 0;

        this.damage = damage;
        this.type = type;
        this.target = null;
    }

    public void setTarget(Monster target) {
        this.target = target;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public ProjectileType getType() {
        return type;
    }

    @Override
    public void update(Board board) {
        if (this.target != null) {
            if (Collision.isColliding(this, this.target)) {
                this.target.removeHealth(damage);
                this.setRemoved(true);
            }

            double angle = Collision.getAngle(this.target, this);
            double x = this.getPosition().getX() + Math.cos(angle) * this.getSpeed();
            double y = this.getPosition().getY() + Math.sin(angle) * this.getSpeed();
            this.setPosition(x, y);
        }
    }
    @Override
    public Command remove() {
        return null;
    }
}
