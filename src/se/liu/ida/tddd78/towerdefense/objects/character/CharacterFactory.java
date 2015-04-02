package se.liu.ida.tddd78.towerdefense.objects.character;
import se.liu.ida.tddd78.towerdefense.objects.basic.Point;

/**
 * Created by Seba on 2015-02-12.
 */
public class CharacterFactory {
    public static BasicCharacter makeCharacter(CharacterType type) {
        switch (type) {
            case PLAYER:
                return makePlayer();
            case SOMEDUDE:
                return makeOther();
            default:
                throw new IllegalArgumentException("Character type not supported");
        }
    }

    private static BasicCharacter makePlayer() {
        return new BasicCharacter(new Point(0,0), 10, 1, CharacterType.PLAYER);
    }

    private static BasicCharacter makeOther() {
        return new BasicCharacter(new Point(0,0), 10, 8, CharacterType.SOMEDUDE);
    }
}