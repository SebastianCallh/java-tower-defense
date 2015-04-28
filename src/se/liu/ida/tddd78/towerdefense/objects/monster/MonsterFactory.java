package se.liu.ida.tddd78.towerdefense.objects.monster;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;

/**
 * Contains methods for creating monsters.
 */
public final class MonsterFactory {
    private  final static int LOW_HEALTH = 30;
    private  final static int HIGH_HEALTH = 120;

    private  final static int SMALL_SIZE = 17;
    private  final static int LARGE_SIZE = 20;

    private  final static int SLOW_SPEED = 1;

    private  final static int LOW_DAMAGE = 1;
    private  final static int HIGH_DAMAGE = 4;

    private  final static int SMALL_BOUNTY = 5;
    private  final static int BIG_BOUNTY = 20;


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

    public static int getSize(MonsterType type) throws TypeNotSupportedException {
        switch (type) {
            case SMALL:
                return SMALL_SIZE;
            case BIG:
                return LARGE_SIZE;
            default:
                throw new TypeNotSupportedException("Monster type not supported");
        }
    }

    private static Monster makeSmall() {
        return new BasicMonster(LOW_HEALTH, SMALL_SIZE, SLOW_SPEED,
                LOW_DAMAGE, SMALL_BOUNTY, MonsterType.SMALL);
    }

    private static Monster makeBig() {
        return new BasicMonster(HIGH_HEALTH, LARGE_SIZE, SLOW_SPEED,
                HIGH_DAMAGE, BIG_BOUNTY, MonsterType.BIG);
    }
}