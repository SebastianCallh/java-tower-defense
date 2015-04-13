package se.liu.ida.tddd78.towerdefense.objects.monster;

/**
 * Contains methods for creating monsters.
 */
public class MonsterFactory {
    public static BasicMonster makeMonster(MonsterType type) {
        switch (type) {
            case SMALL:
                return makeSmall();
            case BIG:
                return makeBig();
            default:
                throw new IllegalArgumentException("Monster type not supported");
        }
    }

    private static BasicMonster makeSmall() {
    return new BasicMonster(30, 10, 1, 1, 5, MonsterType.SMALL);
    }

    private static BasicMonster makeBig() {
        return new BasicMonster(80, 12, 1, 2, 10, MonsterType.BIG); // TODO: Change speed to 0.5
    }
}