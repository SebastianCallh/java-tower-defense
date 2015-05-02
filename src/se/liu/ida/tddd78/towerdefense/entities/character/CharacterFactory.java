package se.liu.ida.tddd78.towerdefense.entities.character;
import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.entities.basic.Point;

/**
 * Contains methods for creating characters.
 */
public final class CharacterFactory {
    private static final int STANDARD_SIZE = 17;
    private static final int STANDARD_SPEED = 1;

    private CharacterFactory() {
    }

    public static Character makeCharacter(CharacterType type) throws TypeNotSupportedException {
        switch (type) {
            case PLAYER:
                return makePlayer();
            default:
                throw new TypeNotSupportedException("Character type not supported");
        }
    }

    public static int getSize(CharacterType type) throws TypeNotSupportedException {
        switch (type) {
            case PLAYER:
                return STANDARD_SIZE;
            default:
                throw new TypeNotSupportedException("Character type not supported");
        }
    }

    private static Character makePlayer() {
        return new BasicCharacter(new Point(0,0), STANDARD_SIZE, STANDARD_SPEED, CharacterType.PLAYER);
    }
}