package se.liu.ida.tddd78.towerdefense.objects.projectile;

public class ProjectileFactory {

    public static Projectile makeProjectile(ProjectileType type) {
        switch (type) {
            case NORMAL:
                return makeNormal();
            default:
                throw new IllegalArgumentException("Projectile type not supported");
        }
    }

    private static Projectile makeNormal() {
        return new BasicProjectile(5, 5, 3, ProjectileType.NORMAL);
    }

}
