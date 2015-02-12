package factories;

import objects.BasicMonster;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Seba on 2015-01-26.
 */
public class MonsterFactory {
    private static Map<BasicMonster.Type, BasicMonster> monsterTypeMap = new HashMap<BasicMonster.Type, BasicMonster>() {{
        put(BasicMonster.Type.SMALL, makeSmall());
        put(BasicMonster.Type.BIG, makeBig());
    }};

    public static BasicMonster.Type[] getTypes() {
        return BasicMonster.Type.values();
    }

    public static BasicMonster makeMonster(BasicMonster.Type type) {
        return monsterTypeMap.get(type);
    }

    private static BasicMonster makeSmall() {
        return new BasicMonster(5, 5, BasicMonster.Type.SMALL);
    }

    private static BasicMonster makeBig() {
        return new BasicMonster(10, 10, BasicMonster.Type.BIG);
    }
}