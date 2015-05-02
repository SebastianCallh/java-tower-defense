package se.liu.ida.tddd78.towerdefense.entities.theme;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import se.liu.ida.tddd78.towerdefense.exceptions.ThemeLoadException;
import se.liu.ida.tddd78.towerdefense.exceptions.ThemeParseException;
import se.liu.ida.tddd78.towerdefense.interfaces.ThemeableType;
import se.liu.ida.tddd78.towerdefense.entities.character.CharacterType;
import se.liu.ida.tddd78.towerdefense.entities.defense.DefenseType;
import se.liu.ida.tddd78.towerdefense.entities.monster.MonsterType;
import se.liu.ida.tddd78.towerdefense.entities.projectile.ProjectileType;
import se.liu.ida.tddd78.towerdefense.entities.theme.Theme.ThemeFactory;
import se.liu.ida.tddd78.towerdefense.entities.tile.TileType;
import se.liu.ida.tddd78.towerdefense.utils.FileDiscoveryUtil;
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

    private static void initializeExpressions() throws ThemeParseException {
        if (hasInitializedExpressions) return;

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();

        try {
            elementExpression = xpath.compile(ELEMENT_SELECTOR);
            spriteSelector = xpath.compile(SPRITE_SELECTOR);
        } catch (XPathExpressionException e) {
            throw new ThemeParseException("Failed to initialize theme parser XPath expressions", e);
        }

        hasInitializedExpressions = true;
    }

    public static Theme load(ThemeType type) throws ThemeLoadException{
        return load(THEMETYPE_URL_MAP.get(type));
    }

    public static Theme load(URL resourceUrl) throws ThemeLoadException {
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
        } catch (ParserConfigurationException e) {
            throw new ThemeParseException("Misconfigured parser configuration", e);
        } catch (SAXException e) {
            throw new ThemeParseException("Invalid XML format", e);
        } catch (IOException e) {
            throw new ThemeParseException("Unable to open file", e);
        } catch (XPathExpressionException e) {
            throw new ThemeParseException("Failed to evaluate XPath expression", e);
        }
    }

    public static List<Theme> getAvailableThemes() throws ThemeLoadException {
        List<Theme> themes = new ArrayList<>();
        for (URL theme: FileDiscoveryUtil.retrieveExistingFiles(FileDiscoveryUtil.FileType.THEME)) {
            if (verifyTheme(theme)) {
                themes.add(load(theme));
            }
        }
        return themes;
    }

    private static boolean verifyTheme(URL file) {
        try {
            ThemeLoader.load(file);
            return true;
        } catch (ThemeLoadException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static ThemeableType retrieveType(Node element) throws ThemeLoadException {
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
                    throw new ThemeLoadException("Unrecognized object '" + object + "'");
            }
        }
        catch (IllegalArgumentException ignored) {
            throw new ThemeLoadException("Unrecognized object type '" + type + "'");
        }
    }

    private static Image retrieveSprite(Node element) throws XPathExpressionException, ThemeLoadException {
        String spritePath = spriteSelector.evaluate(element);
        URL spriteUrl = ThemeLoader.class.getClassLoader().getResource(spritePath);
        if (spriteUrl == null) {
            throw new ThemeLoadException("Unable to find sprite '" + spritePath + "'");
        }

        try {
            return ImageIO.read(spriteUrl);
        } catch (IOException ignored) {
            throw new ThemeLoadException("Unable to read sprite '" + spritePath + "'");
        }
    }

    private static Image scaleSprite(Image image, ThemeableType type) {
        return image.getScaledInstance(
                type.getSize() * 4 * GraphicsUtil.getScale(),
                type.getSize() * 4 * GraphicsUtil.getScale(),
                Image.SCALE_SMOOTH);
    }
}
