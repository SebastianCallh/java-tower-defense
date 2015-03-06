package se.liu.ida.tddd78.towerdefense.objects.monster;

/**
 * Created by Seba on 2015-01-26.
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
    return new BasicMonster(10, 10, 2, MonsterType.SMALL);
    }

    private static BasicMonster makeBig() {
        return new BasicMonster(20, 20, 1, MonsterType.BIG);
    }
}