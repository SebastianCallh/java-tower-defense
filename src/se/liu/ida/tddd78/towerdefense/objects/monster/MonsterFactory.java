package se.liu.ida.tddd78.towerdefense.objects.monster;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;

/**
 * Contains methods for creating monsters.
 */
public final class MonsterFactory {
    private MonsterFactory() {
    }

    public static Monster makeMonster(MonsterType type) throws TypeNotSupportedException {
        switch (type) {
            case SMALL:
                return makeSmall();
            case BIG:
                return makeBig();
            default:
                throw new TypeNotSupportedException("Monster type not supported");
        }
    }

    private static Monster makeSmall() {
    return new BasicMonster(30, 10, 1, 1, 5, MonsterType.SMALL);
    }

    private static Monster makeBig() {
        return new BasicMonster(80, 12, 1, 2, 10, MonsterType.BIG); // TODO: Change speed to 0.5
    }
}