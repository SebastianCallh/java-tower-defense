import factories.MonsterFactory;
import objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Seba on 2015-01-23.
 */
public class Board {
    private Layout layout;
    private Theme theme;
    private List<Monster> monsters = new ArrayList<Monster>();
    private MonsterFactory monsterFactory;
    private int tileSize;

    //TODO:Make size of window/board/tiles work solely out of the layout files size
    public final static int BOARD_SIZE = 400;

    public int getWidth() {
        return this.layout.getWidth();
    }

    public int getHeight() {
        return this.layout.getHeight();
    }

    public Tile getTile(int x, int y) {
        return this.layout.getTile(x, y);
    }

    public int getTileSize() {
        return this.tileSize;
    }

    public int getMonsterCount() {
        return this.monsters.size();
    }

    public Monster getMonster(int i) {
        return this.monsters.get(i);
    }

    public Theme getTheme() {
        return this.theme;
    }

    public Board(int width, int height, Theme theme, Layout layout) {
        this.layout = layout;
        this.theme = theme;
        this.tileSize = BOARD_SIZE / this.layout.getWidth();

        //Temporary
        this.monsterFactory= new MonsterFactory();
        Monster monster = this.monsterFactory.makeMonster(this.monsterFactory.getTypes()[0]);
        monster.setPosition(this.layout.getSpawnTile().getCenter());
        this.monsters.add(monster);
    }

    /*
    public class Graph<E> {
        Map<String, Node<E>> nodes;

        public void addNode(String edge, Node<E> node) {
            this.nodes.put(edge, node);
        }

        public class Node<E> {
            private E value;
            private List<String> edges;

            public Node(E value, List<String> edges) {
                this.value = value;
                this.edges = edges;
            }
        }
    }*/
}
