package se.liu.ida.tddd78.towerdefense.entities.theme;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import se.liu.ida.tddd78.towerdefense.exceptions.ThemeParseException;
import se.liu.ida.tddd78.towerdefense.interfaces.ThemeableType;
import se.liu.ida.tddd78.towerdefense.entities.character.CharacterType;
import se.liu.ida.tddd78.towerdefense.entities.defense.DefenseType;
import se.liu.ida.tddd78.towerdefense.entities.monster.MonsterType;
import se.liu.ida.tddd78.towerdefense.entities.projectile.ProjectileType;
import se.liu.ida.tddd78.towerdefense.entities.theme.Theme.ThemeFactory;
import se.liu.ida.tddd78.towerdefense.entities.tile.TileType;
import se.liu.ida.tddd78.towerdefense.utils.FileDiscoveryUtil;
import se.liu.ida.tddd78.towerdefense.utils.FileDiscoveryUtil.FileType;
import se.liu.ida.tddd78.towerdefense.utils.GraphicsUtil;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.List;

public final class ThemeLoader {
    private static final String ELEMENT_SELECTOR = "/theme/element";
    private static final String SPRITE_SELECTOR = "sprite";

    private static XPathExpression elementExpression = null;
    private static XPathExpression spriteSelector = null;
    private static boolean hasInitializedExpressions = false;

    private static Map<ThemeType, URL> THEMETYPE_URL_MAP = new HashMap<ThemeType, URL>() {{
        put(ThemeType.STANDARD, ThemeLoader.class.getClassLoader().getResource("resources/theme/standard.theme"));
        put(ThemeType.PIRATE, ThemeLoader.class.getClassLoader().getResource("resources/theme/pirate.theme"));
    }};

    private ThemeLoader() {
    }

    private static void initializeExpressions() throws XPathExpressionException {
        if (hasInitializedExpressions) return;

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();

        elementExpression = xpath.compile(ELEMENT_SELECTOR);
        spriteSelector = xpath.compile(SPRITE_SELECTOR);

        hasInitializedExpressions = true;
    }

    public static Theme load(ThemeType type) throws ThemeParseException, ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        return load(THEMETYPE_URL_MAP.get(type));
    }

    public static Theme load(URL resourceUrl) throws ThemeParseException, ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        initializeExpressions();

        try (InputStream resourceInputStream = resourceUrl.openStream()) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(resourceInputStream);

            String name = doc.getElementsByTagName("name").item(0).getTextContent();
            ThemeFactory themeFactory = new ThemeFactory(name);

            NodeList elements = (NodeList) elementExpression.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < elements.getLength(); i++) {
                Node element = elements.item(i);

                ThemeableType type = retrieveType(element);
                Image scaledSprite = scaleSprite(retrieveSprite(element), type);
                themeFactory.addSpriteMapping(type, scaledSprite);
            }

            return themeFactory.build();
        }
    }

    public static List<Theme> getAvailableThemes() {
        List<Theme> themes = new ArrayList<>();
        FileDiscoveryUtil.retrieveExistingFiles(FileType.THEME).stream().filter(ThemeLoader::verifyTheme).forEach(theme -> {
            try {
                themes.add(load(theme));
            } catch (ThemeParseException | ParserConfigurationException | XPathExpressionException | IOException | SAXException e) {
                e.printStackTrace();
            }
        });
        return themes;
    }

    private static boolean verifyTheme(URL file) {
        try {
            ThemeLoader.load(file);
            return true;
        } catch (ThemeParseException | ParserConfigurationException | SAXException | XPathExpressionException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static ThemeableType retrieveType(Node element) throws ThemeParseException {
        NamedNodeMap attributes = element.getAttributes();
        String object = attributes.getNamedItem("object").getNodeValue();
        String type = attributes.getNamedItem("type").getNodeValue().toUpperCase();

        try {
            switch (object) {
                case "character":
                    return CharacterType.valueOf(type);
                case "defense":
                    return DefenseType.valueOf(type);
                case "monster":
                    return MonsterType.valueOf(type);
                case "projectile":
                    return ProjectileType.valueOf(type);
                case "tile":
                    return TileType.valueOf(type);
                default:
                    throw new ThemeParseException("Unrecognized object '" + object + "'");
            }
        }
        catch (IllegalArgumentException e) {
            throw new ThemeParseException("Unrecognized object type '" + type + "'", e);
        }
    }

    private static Image retrieveSprite(Node element) throws XPathExpressionException, IOException {
        String spritePath = spriteSelector.evaluate(element);
        URL spriteUrl = ThemeLoader.class.getClassLoader().getResource(spritePath);
        if (spriteUrl == null) {
            throw new IOException("Unable to find sprite '" + spritePath + "'");
        }

        return ImageIO.read(spriteUrl);
    }

    private static Image scaleSprite(Image image, ThemeableType type) {
        return image.getScaledInstance(
                type.getSize() * 4 * GraphicsUtil.getScale(),
                type.getSize() * 4 * GraphicsUtil.getScale(),
                Image.SCALE_SMOOTH);
    }
}
