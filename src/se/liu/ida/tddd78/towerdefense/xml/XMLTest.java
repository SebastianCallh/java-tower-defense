package se.liu.ida.tddd78.towerdefense.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.net.URL;

public class XMLTest {

    public static void main(String[] args) throws Exception {
        URL xmlURL = XMLTest.class.getClassLoader().getResource("data/theme.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse((InputStream) xmlURL.getContent(new Class[] {InputStream.class}));
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile("/theme/element[@object='monster']");
        NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        System.out.println(nodeList.getLength());
    }

}
