package se.liu.ida.tddd78.towerdefense.objects.projectiles;

import se.liu.ida.tddd78.towerdefense.objects.GameObject;

public interface Projectile extends GameObject {

    public int getDamage();

    public void setDamage(int damage);

    public ProjectileType getType();

}
