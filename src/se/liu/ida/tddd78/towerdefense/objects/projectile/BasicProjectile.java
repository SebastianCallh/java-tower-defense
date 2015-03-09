package se.liu.ida.tddd78.towerdefense.objects.projectile;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.Collision;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.interfaces.Painter;
import se.liu.ida.tddd78.towerdefense.abstracts.AbstractMovable;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.monster.Monster;

public class BasicProjectile extends AbstractMovable implements Projectile {
    private int damage;
    private ProjectileType type;
    private Monster target;

    public BasicProjectile(int damage, int size, int speed, ProjectileType type) {
        super(new Point(0, 0), size, speed);
        this.damage = damage;
        this.type = type;
    }

    public void setTarget(Monster target) {
        this.target = target;
    }

    @Override
    public Monster getTarget() {
        return this.target;
    }

    @Override
    public int getDamage() {
        return damage;
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
    public Painter getPainter() {
        return ProjectilePainter.instanceFor(this);
    }

    @Override
    public void update(Board board) {
        if (this.getTarget() != null) {
            if (Collision.distanceBetween(this, this.getTarget()) < this.getSize()) {
                this.getTarget().removeHealth(this.getDamage());
                this.setRemoved(true);
            }

            double angle = Math.atan2(this.getTarget().getPosition().y - this.getPosition().y,
                    this.getTarget().getPosition().x - this.getPosition().x);

            double x = this.getPosition().x + Math.cos(angle) * this.getSpeed();
            double y = this.getPosition().y + Math.sin(angle) * this.getSpeed();
            this.setPosition(x, y);
        }
    }
    @Override
    public Command getOnRemovedCommand() {
        return null;
    }
}
