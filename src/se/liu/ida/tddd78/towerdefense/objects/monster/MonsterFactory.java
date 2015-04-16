package se.liu.ida.tddd78.towerdefense.objects.monster;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;

/**
 * Contains methods for creating monsters.
 */
public final class MonsterFactory {
    private  final static int SMALL_HEALTH = 30;
    private  final static int SMALL_SIZE = 17;
    private  final static int SMALL_SPEED = 1;
    private  final static int SMALL_DAMAGE = 1;
    private  final static int SMALL_BOUNTY = 5;

    private  final static int BIG_HEALTH = 120;
    private  final static int BIG_SIZE = 20;
    private  final static int BIG_SPEED = 1;
    private  final static int BIG_DAMAGE = 4;
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
                return BIG_SIZE;
            default:
                throw new TypeNotSupportedException("Monster type not supported");
        }
    }

    private static Monster makeSmall() {
        return new BasicMonster(SMALL_HEALTH, SMALL_SIZE, SMALL_SPEED,
                SMALL_DAMAGE, SMALL_BOUNTY, MonsterType.SMALL);
    }

    private static Monster makeBig() {
        return new BasicMonster(BIG_HEALTH, BIG_SIZE, BIG_SPEED,
                BIG_DAMAGE, BIG_BOUNTY, MonsterType.BIG);
    }
}