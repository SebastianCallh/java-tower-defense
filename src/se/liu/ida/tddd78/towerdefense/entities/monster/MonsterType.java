package se.liu.ida.tddd78.towerdefense.entities.monster;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.interfaces.ThemeableType;

public enum MonsterType implements ThemeableType {
    SMALL, BIG;

    @Override
    public int getSize() {
        try {
            return MonsterFactory.getSize(this);
        } catch (TypeNotSupportedException ignored) {
            return 0;
        }
    }
}
