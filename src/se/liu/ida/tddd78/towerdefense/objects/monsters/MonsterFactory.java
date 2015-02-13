package se.liu.ida.tddd78.towerdefense.objects.monsters;

import se.liu.ida.tddd78.towerdefense.objects.GameObjectType;

/**
 * Created by Seba on 2015-01-26.
 */
public class MonsterFactory {
    public static BasicMonster makeMonster(GameObjectType type) {
        switch (type) {
            case MONSTER_SMALL:
                return makeSmall();
            case MONSTER_BIG:
                return makeBig();
            default:
                throw new IllegalArgumentException("Monster type not supported");
        }
    }

    private static BasicMonster makeSmall() {
        return new BasicMonster(10, 10, GameObjectType.MONSTER_SMALL);
    }

    private static BasicMonster makeBig() {
        return new BasicMonster(20, 20, GameObjectType.MONSTER_BIG);
    }
}