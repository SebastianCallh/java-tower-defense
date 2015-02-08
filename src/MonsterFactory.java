import java.util.HashMap;
import java.util.Map;

/**
 * Created by Seba on 2015-01-26.
 */
public class MonsterFactory {

    private static Map<Monster.Type, Monster> MonsterTypeMap = new HashMap<Monster.Type, Monster>() {{
        put(Monster.Type.SMALL, makeSmall());
        put(Monster.Type.BIG, makeBig());
    }};

    public Monster.Type[] getTypes() {
        return Monster.Type.values();
    }

    public Monster makeMonster(Monster.Type type) {
        return MonsterTypeMap.get(type);
    }

    private static Monster makeSmall() {
        return new Monster(5, 5, Monster.Type.SMALL);
    }

    private static Monster makeBig() {
        return new Monster(10, 10, Monster.Type.BIG);
    }
}
