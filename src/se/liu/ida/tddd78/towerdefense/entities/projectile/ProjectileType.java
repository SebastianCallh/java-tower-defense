package se.liu.ida.tddd78.towerdefense.entities.projectile;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.interfaces.ThemeableType;

public enum ProjectileType implements ThemeableType {
    NORMAL;

    @Override
    public int getSize() {
        try {
            return ProjectileFactory.getSize(this);
        } catch (TypeNotSupportedException ignored) {
            return 0;
        }
    }
}
