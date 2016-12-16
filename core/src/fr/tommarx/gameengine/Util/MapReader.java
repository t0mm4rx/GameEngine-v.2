package fr.tommarx.gameengine.Util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapReader {

    public static void read(String file) {
        Element xml = XMLParser.getDocumentElement(file);
        ArrayList<Tile> tiles = getTiles(xml);
    }

    public static ArrayList<Tile> getTiles(Element xml) {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int i = 0; i < xml.getElementsByTagName("tileset").getLength(); i++) {
            Node tileSet = xml.getElementsByTagName("tileset").item(i);
            int id = Integer.parseInt(tileSet.getAttributes().getNamedItem("firstgid").getNodeValue());
            String name = tileSet.getAttributes().getNamedItem("name").getNodeValue();
            //Find the images props (width, height, and source)
            String image = "";
            int width = 0, height = 0;
            for (int j = 0; j < tileSet.getChildNodes().getLength(); j++) {
                if (tileSet.getChildNodes().item(j).getNodeName().equals("image")) {
                    image = tileSet.getChildNodes().item(j).getAttributes().getNamedItem("source").getNodeValue();
                    width = Integer.parseInt(tileSet.getChildNodes().item(j).getAttributes().getNamedItem("width").getNodeValue());
                    height = Integer.parseInt(tileSet.getChildNodes().item(j).getAttributes().getNamedItem("height").getNodeValue());
                }
            }
            //Find properties
            Map<String, String> props = new HashMap<String, String>();
            for (int k = 0; k < tileSet.getChildNodes().getLength(); k++) {
                if (tileSet.getChildNodes().item(k).getNodeName().equals("tile")) {
                    Node properties = tileSet.getChildNodes().item(k).getChildNodes().item(1);
                    for (int l = 0; l < properties.getChildNodes().getLength(); l++) {
                        System.out.println(properties.getFirstChild().getAttributes().getLength());
                        Node prop = properties.getChildNodes().item(l);
                        props.put(prop.getAttributes().getNamedItem("name").getNodeValue(), prop.getAttributes().getNamedItem("value").getNodeValue());
                    }
                }
            }
            System.out.println("Id = " + id + " && name = " + name + " && image = " + image + " && width = " + width + " && height = " + height + " && propsLenght = " + props.size());
        }
        return tiles;
    }

}
