package se.liu.ida.tddd78.towerdefense.objects.tile;

import se.liu.ida.tddd78.towerdefense.interfaces.ThemeableType;

public enum TileType implements ThemeableType {
    PATH, BLOCKED, GOAL, SPAWN;

    @Override
    public int getSize() {
        return (int) Tile.TILE_SIZE;
    }
}
