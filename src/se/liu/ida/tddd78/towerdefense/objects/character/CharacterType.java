package se.liu.ida.tddd78.towerdefense.objects.character;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.interfaces.ThemeableType;

public enum CharacterType implements ThemeableType {
    PLAYER,
    SOMEDUDE;

    @Override
    public int getSize() {
        try {
            return CharacterFactory.getSize(this);
        } catch (TypeNotSupportedException ignored) {
            return 0; //Does this need more error handling?
        }
    }
}