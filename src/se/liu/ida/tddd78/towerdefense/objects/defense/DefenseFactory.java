package se.liu.ida.tddd78.towerdefense.objects.defense;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;

/**
 * Contains methods for creating defenses.
 */
public final class DefenseFactory {
    private DefenseFactory() {
    }

    public static Defense makeDefense(DefenseType type) throws TypeNotSupportedException {
        switch (type) {
            case SMALL:
                return makeSmall();
            case BIG:
                return makeBig();
            case FAST:
            default:
                throw new TypeNotSupportedException("Defense type not supported");
        }
    }

    private static Defense makeSmall() {
        return new BasicDefense(10, DefenseType.SMALL, 50, 5, 500, 100);
    }

    private static Defense makeBig() {
        return new BasicDefense(15, DefenseType.BIG, 500, 10, 500, 200);
    }
}
