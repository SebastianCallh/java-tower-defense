package se.liu.ida.tddd78.towerdefense.objects.projectile;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
/**
 * Contains methods for creating projectile classes.
 */
public final class ProjectileFactory {
    private static final int NORMAL_DAMAGE = 5;
    private static final int NORMAL_SIZE = 5;
    private static final int NORMAL_SPEED = 3;

    private ProjectileFactory() {
    }

    public static Projectile makeProjectile(ProjectileType type) throws TypeNotSupportedException {
        switch (type) {
            case NORMAL:
                return makeNormal();
            default:
                throw new TypeNotSupportedException("Projectile type not supported");
        }
    }

    public static int getSize(ProjectileType type) throws TypeNotSupportedException {
        switch (type) {
            case NORMAL:
                return NORMAL_SIZE;
            default:
                throw new TypeNotSupportedException("Projectile type not supported");
        }
    }

    private static Projectile makeNormal() {
        return new BasicProjectile(NORMAL_DAMAGE, NORMAL_SIZE, NORMAL_SPEED, ProjectileType.NORMAL);
    }

}
