package fr.tommarx.gameengine.Util;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Util {

    public static BitmapFont ttfToBitmap(FileHandle file, int size) {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(file);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        return fontGenerator.generateFont(parameter);
    }

}
