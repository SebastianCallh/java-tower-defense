package se.liu.ida.tddd78.towerdefense.entities.defense;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.interfaces.ThemeableType;

public enum DefenseType implements ThemeableType {
    SMALL,
    BIG,
    FAST;

    @Override
    public int getSize() {
        try {
            return DefenseFactory.getSize(this);
        } catch (TypeNotSupportedException ignored) {
            return 0;
        }
    }
}
