package se.liu.ida.tddd78.towerdefense.entities.layout;

import se.liu.ida.tddd78.towerdefense.exceptions.LayoutParseException;
import se.liu.ida.tddd78.towerdefense.entities.basic.Grid;
import se.liu.ida.tddd78.towerdefense.entities.basic.Point;
import se.liu.ida.tddd78.towerdefense.entities.tile.Tile;
import se.liu.ida.tddd78.towerdefense.entities.tile.TileType;
import se.liu.ida.tddd78.towerdefense.utils.FileDiscoveryUtil;
import se.liu.ida.tddd78.towerdefense.utils.FileDiscoveryUtil.FileType;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Handles loading layout from file
 */
public final class LayoutLoader {
    private static final Map<LayoutType, URL> LAYOUTTYPE_URL_MAP = new EnumMap<>(LayoutType.class);

    static {
        LAYOUTTYPE_URL_MAP.put(LayoutType.STANDARD, LayoutLoader.class.getClassLoader().getResource("resources/layout/standard.layout"));
        LAYOUTTYPE_URL_MAP.put(LayoutType.NOT_STANDARD, LayoutLoader.class.getClassLoader().getResource("resources/layout/not_standard.layout"));
    }

    private LayoutLoader() {}

    public static Layout load(LayoutType type) throws LayoutParseException { return readLayout(LAYOUTTYPE_URL_MAP.get(type)); }

    //*Reads a grid text file (20*20 chars) for a grid of tiles*//
    private static Layout readLayout(URL resourceUrl) throws LayoutParseException {

        try {
            Path resPath;
            try {
                resPath = Paths.get(resourceUrl.toURI());
            } catch (URISyntaxException e) {
                throw new LayoutParseException("Unable to find path to resource '" + resourceUrl + "'", e);
            }
            List<String> fileContent = Files.readAllLines(resPath);
            String name = fileContent.get(0);
            String header = fileContent.get(1);
            int x = 0, y = 0,
                    width = Integer.valueOf(header.substring(0, 2)),
                    height = Integer.valueOf(header.substring(2, 4));
            Grid<Tile> grid = new Grid<>(Tile.class, width, height);
            Tile spawnTile = null, goalTile = null;

            for (String line : fileContent.subList(2, fileContent.size())) {
                for (char c : line.toCharArray()) {
                    Tile tile = new Tile(charToTileType(c), new Point(x, y));
                    if (tile.getType() == TileType.SPAWN) {
                        spawnTile = tile;
                    } else if (tile.getType() == TileType.GOAL) {
                        goalTile = tile;
                    }
                    grid.set(x, y, tile);
                    x++;
                }
                x = 0;
                y++;
            }
            if (spawnTile == null || goalTile == null) {
                throw new LayoutParseException("Spawn tile or goal tile missing");
            }
            return new Layout(name, grid, spawnTile, goalTile);

        } catch (IOException e) {
            throw new LayoutParseException("Could not find layout file", e);
        }
    }

    public static List<Layout> getAvailableLayouts() throws LayoutParseException {
        List<Layout> layouts = new ArrayList<>();
        for (URL layout : FileDiscoveryUtil.retrieveExistingFiles(FileType.LAYOUT)) {
            if (verifyLayout(layout)) {
                layouts.add(readLayout(layout));
            }
        }
        return layouts;
    }

    private static boolean verifyLayout(URL file) {
        try {
            LayoutLoader.readLayout(file);
            return true;
        } catch (LayoutParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static TileType charToTileType(char c) {
        switch (c) {
            case 'S':
                return TileType.SPAWN;
            case 'G':
                return TileType.GOAL;
            case 'P':
                return TileType.PATH;
            case 'B':
                return TileType.BLOCKED;
            default:
                throw new IllegalArgumentException("File contains unrecognized tiles");
        }
    }
}
