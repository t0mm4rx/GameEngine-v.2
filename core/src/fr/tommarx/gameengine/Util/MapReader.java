package fr.tommarx.gameengine.Util;


import com.badlogic.gdx.files.FileHandle;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapReader {

    public static void parse(FileHandle file, float tileSize, MapInterface mapInterface) {
        try {
            BufferedImage image = ImageIO.read(file.read());
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    Color c = new Color(image.getRGB(x, y), true);
                    if (c.getAlpha() != 0) {
                        mapInterface.onPixel(new com.badlogic.gdx.graphics.Color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()), x * tileSize / 100, (image.getHeight() - y) * tileSize / 100);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
