package se.liu.ida.tddd78.towerdefense.objects.defense;

import se.liu.ida.tddd78.towerdefense.Board;
import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.utils.Collision;
import se.liu.ida.tddd78.towerdefense.interfaces.Command;
import se.liu.ida.tddd78.towerdefense.objects.abstracts.AbstractGameObject;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;
import se.liu.ida.tddd78.towerdefense.objects.basic.Timer;
import se.liu.ida.tddd78.towerdefense.objects.monster.Monster;
import se.liu.ida.tddd78.towerdefense.objects.projectile.Projectile;
import se.liu.ida.tddd78.towerdefense.objects.projectile.ProjectileFactory;
import se.liu.ida.tddd78.towerdefense.objects.projectile.ProjectileType;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Basic implementation of a defense that can be placed on the board.
 */
public class BasicDefense extends AbstractGameObject implements Defense {
    private static final Logger LOG = Logger.getLogger(BasicDefense.class.getName());

    private DefenseType type;
    private int range;
    private int cost;
    private int damage;
    private long attackSpeed;
    private Monster target;
    private Timer cooldownTimer;

    @Override public DefenseType getType() {
        return type;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    private Projectile getProjectile() {
        try {
            Projectile projectile = ProjectileFactory.makeProjectile(ProjectileType.NORMAL);
            projectile.setDamage(this.damage);
            projectile.setPosition(this.getPosition().getX(), this.getPosition().getY());
            projectile.setTarget(this.target);

            return projectile;
        } catch (TypeNotSupportedException e) {
            LOG.log(Level.WARNING, "Unable to fire projectile of unsupported type", e);

            return null;
        }
    }

    private void coolDown() {
        this.cooldownTimer.reset(this.attackSpeed);
    }

    private boolean isCoolingDown() {
        return !this.cooldownTimer.hasCompleted();
    }

    public BasicDefense(int size, int range, int damage, long attackSpeed, int cost, DefenseType type) {
        super(new Point(0,0), size);
        this.range = range;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.cost = cost;
        this.cooldownTimer = new Timer(this.attackSpeed);
        this.type = type;
        this.target = null;
    }

    @Override
    public void update(Board board) {
        if (this.target != null && !this.target.isRemoved()) {
            if (Collision.distanceBetween(this, this.target) > this.range) {
                this.target = null;
            } else if (!this.isCoolingDown()) {
                board.getGameObjects().add(this.getProjectile());
                this.coolDown();
            }
        } else {
            board.getGameObjects().getMonsters().stream().filter(
                    monster -> Collision.distanceBetween(this, monster) < this.range).forEach(
                    monster -> this.target = monster);
        }
    }

    @Override
    public Command remove() {
        return null;
    }
}
