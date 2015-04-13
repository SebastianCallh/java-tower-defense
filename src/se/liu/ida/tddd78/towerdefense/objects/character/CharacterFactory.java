package se.liu.ida.tddd78.towerdefense.objects.character;
import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Contains methods for creating characters.
 */
public final class CharacterFactory {
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

    private static Character makePlayer() {
        return new BasicCharacter(new Point(0,0), 10, 1, CharacterType.PLAYER);
    }

    private static Character makeOther() {
        return new BasicCharacter(new Point(0,0), 10, 8, CharacterType.SOMEDUDE);
    }
}