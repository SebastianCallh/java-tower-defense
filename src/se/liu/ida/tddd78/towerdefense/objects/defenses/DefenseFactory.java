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
        return new BasicDefense(new Dimension(25, 25), DefenseType.SMALL, 50, 0.5);
    }

    private static BasicDefense makeBig() {
        return new BasicDefense(new Dimension(40, 40), DefenseType.BIG, 50, 0.5);
    }
}
