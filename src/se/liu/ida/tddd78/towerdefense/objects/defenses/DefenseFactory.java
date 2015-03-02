package se.liu.ida.tddd78.towerdefense.objects.defenses;
import java.awt.*;

/**
 * Created by Seba on 2015-02-12.
 */
public class DefenseFactory {
    public static BasicDefense makeDefense(DefenseType type) {
        switch (type) {
            case SMALL:
                return makeSmall();
            case BIG:
                return makeBig();
            default:
                throw new IllegalArgumentException("Defense type not supported");
        }
    }

    private static BasicDefense makeSmall() {
        return new BasicDefense(10, DefenseType.SMALL, 50, 5, 500);
    }

    private static BasicDefense makeBig() {
        return new BasicDefense(15, DefenseType.BIG, 500, 10, 500);
    }
}
