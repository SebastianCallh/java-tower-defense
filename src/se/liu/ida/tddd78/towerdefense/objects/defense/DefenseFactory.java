package se.liu.ida.tddd78.towerdefense.objects.defense;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;

/**
 * Contains methods for creating defenses.
 */
public final class DefenseFactory {
    private final static int SMALL_SIZE = 10;
    private final static int BIG_SIZE = 15;

    private final static int SMALL_RANGE = 50;
    private final static int BIG_RANGE = 500;

    private final static int SMALL_DAMAGE = 5;
    private final static int BIG_DAMAGE = 10;

    private final static int SMALL_COST = 100;
    private final static int BIG_COST = 200;
    private static final int FAST_COST = 150;

    private final static long MEDIUM_ATTACK_SPEED = 500;
    private static final long FAST_ATTACK_SPEED = 250;

    private DefenseFactory() {
    }

    public static Defense makeDefense(DefenseType type) throws TypeNotSupportedException {
        switch (type) {
            case SMALL:
                return makeSmall();
            case BIG:
                return makeBig();
            case FAST:
                return makeFast();
            default:
                throw new TypeNotSupportedException("Defense type not supported");
        }
    }

    public static int getSize(DefenseType type) throws TypeNotSupportedException {
        switch (type) {
            case SMALL:
                return SMALL_SIZE;
            case BIG:
                return BIG_SIZE;
            case FAST:
            default:
                throw new TypeNotSupportedException("Defense type not supported");
        }
    }

    private static Defense makeSmall() {
        return new BasicDefense(SMALL_SIZE, SMALL_RANGE, SMALL_DAMAGE,
                MEDIUM_ATTACK_SPEED, SMALL_COST, DefenseType.SMALL);
    }

    private static Defense makeBig() {
        return new BasicDefense(BIG_SIZE, BIG_RANGE, BIG_DAMAGE,
                MEDIUM_ATTACK_SPEED, BIG_COST, DefenseType.BIG);
    }

    private static Defense makeFast() {
        return new BasicDefense(SMALL_SIZE, SMALL_RANGE, SMALL_DAMAGE,
                FAST_ATTACK_SPEED, FAST_COST, DefenseType.SMALL);
    }
}