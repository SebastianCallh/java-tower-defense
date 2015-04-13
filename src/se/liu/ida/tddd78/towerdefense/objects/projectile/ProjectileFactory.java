package se.liu.ida.tddd78.towerdefense.objects.projectile;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;

public final class ProjectileFactory {

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

    private static Projectile makeNormal() {
        return new BasicProjectile(5, 5, 3, ProjectileType.NORMAL);
    }

}
