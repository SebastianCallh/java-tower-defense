package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.exceptions.TypeNotSupportedException;
import se.liu.ida.tddd78.towerdefense.entities.monster.Monster;
import se.liu.ida.tddd78.towerdefense.entities.monster.MonsterFactory;
import se.liu.ida.tddd78.towerdefense.entities.monster.MonsterType;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles creating and placing monsters on the board, and the games difficulty.
 */
public class Spawner {
    private static final Logger LOG = Logger.getLogger(Spawner.class.getName());

    private static final int STARTING_MONSTER_POINTS = 5;

    private List<SpawnMapper> spawns = new ArrayList<>();
    private int cheapestMonsterPrice;

    public Spawner() {
        this.spawns.add(new SpawnMapper(1, 1, MonsterType.SMALL));
        this.spawns.add(new SpawnMapper(4, 4, MonsterType.BIG));
        Collections.sort(this.spawns);
        this.cheapestMonsterPrice = this.spawns.get(0).price;

        assert this.cheapestMonsterPrice > 0;
    }

    public List<Monster> spawn(int round) {
        int monsterPoints = STARTING_MONSTER_POINTS + round;
        List<Monster> monsters = new ArrayList<>();
        int i = this.spawns.size() - 1;

        while (monsterPoints >= this.cheapestMonsterPrice) {
            SpawnMapper spawn = this.spawns.get(i);
            if (spawn.price <= monsterPoints && spawn.appearsOnWave <= round) {
                try {
                    monsters.add(MonsterFactory.makeMonster(this.spawns.get(i).type));
                } catch (TypeNotSupportedException e) {
                    LOG.log(Level.WARNING, "Unable to spawn monster of unsupported type", e);
                }
                monsterPoints -= spawn.price;
            } else {
                i--;
            }
        }
        return monsters;
    }

    private final class SpawnMapper implements Comparable<SpawnMapper> {
        private int appearsOnWave;
        private int price;
        private MonsterType type;

        private SpawnMapper(int appearsOnWave, int price, MonsterType type) {
            this.appearsOnWave = appearsOnWave;
            this.price = price;
            this.type = type;
        }

        @Override
        public int compareTo(SpawnMapper o) { // Complains about missing @NotNull parameter, also complains when added.
            if (this.price < o.price) {
                return -1;
            } else if (this.price == o.price) {
                return 0;
            } else {
                return 1;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SpawnMapper that = (SpawnMapper) o;

            if (appearsOnWave != that.appearsOnWave) return false;
            if (price != that.price) return false;
            if (type != that.type) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = appearsOnWave;
            result = 31 * result + price;
            result = 31 * result + (type != null ? type.hashCode() : 0);
            return result;
        }
    }
}
