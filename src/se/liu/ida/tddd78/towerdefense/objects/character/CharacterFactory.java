package se.liu.ida.tddd78.towerdefense.objects.character;
import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Contains methods for creating characters.
 */
public final class CharacterFactory {
    private static final int PLAYER_SIZE = 17;
    private static final int PLAYER_SPEED = 1;

    private static final int SOMEDUDE_SIZE = 10;
    private static final int SOMEDUDE_SPEED = 8;

    private CharacterFactory() {
    }

    public static Character makeCharacter(CharacterType type) throws TypeNotSupportedException {
        switch (type) {
            case PLAYER:
                return makePlayer();
            case SOMEDUDE:
                return makeOther();
            default:
                throw new TypeNotSupportedException("Character type not supported");
        }
    }

    public static int getSize(CharacterType type) throws TypeNotSupportedException {
        switch (type) {
            case PLAYER:
                return PLAYER_SIZE;
            case SOMEDUDE:
                return SOMEDUDE_SIZE;
            default:
                throw new TypeNotSupportedException("Character type not supported");
        }
    }

    private static Character makePlayer() {
        return new BasicCharacter(new Point(0,0), PLAYER_SIZE, PLAYER_SPEED, CharacterType.PLAYER);
    }

    private static Character makeOther() {
        return new BasicCharacter(new Point(0,0), SOMEDUDE_SIZE, SOMEDUDE_SPEED, CharacterType.SOMEDUDE);
    }
}