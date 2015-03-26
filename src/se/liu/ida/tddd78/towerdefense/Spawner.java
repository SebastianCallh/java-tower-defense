package se.liu.ida.tddd78.towerdefense;

import se.liu.ida.tddd78.towerdefense.objects.monster.Monster;
import se.liu.ida.tddd78.towerdefense.objects.monster.MonsterFactory;
import se.liu.ida.tddd78.towerdefense.objects.monster.MonsterType;

import java.util.*;

/**
 * Created by Seba on 2015-03-24.
 */
public class Spawner {
    private static int STARTING_MONSTER_POINTS = 5;
    private List<SpawnMapper> spawns = new ArrayList<>();
    private int cheapestMonsterPrice;

    public Spawner() {
        this.spawns.add(new SpawnMapper(1, 1, MonsterType.SMALL));
        this.spawns.add(new SpawnMapper(4, 4, MonsterType.BIG));
        Collections.sort(this.spawns);
        this.cheapestMonsterPrice = this.spawns.get(0).price;
    }

    public List<Monster> spawn(int round) {
        int monsterPoints = STARTING_MONSTER_POINTS + round;
        List<Monster> monsters = new ArrayList<>();
        int i = this.spawns.size() - 1;

        while (monsterPoints >= this.cheapestMonsterPrice) {
            SpawnMapper spawn = this.spawns.get(i);
            if (spawn.price <= monsterPoints && spawn.appearsOnWave <= round) {
                monsters.add(MonsterFactory.makeMonster(this.spawns.get(i).type));
                monsterPoints -= spawn.price;
            } else {
                i--;
            }
        }
        return monsters;
    }

    private class SpawnMapper implements Comparable<SpawnMapper> {
        private int appearsOnWave;
        private int price;
        private MonsterType type;

        public SpawnMapper(int appearsOnWave, int price, MonsterType type) {
            this.appearsOnWave = appearsOnWave;
            this.price = price;
            this.type = type;
        }

        public MonsterType getType() {
            return type;
        }

        @Override
        public int compareTo(SpawnMapper o) {
            if (this.price < o.price) {
                return -1;
            } else if (this.price == o.price) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
