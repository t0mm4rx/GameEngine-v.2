package fr.tommarx.gameengine.IO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class File {

	public static String read(String fileName){
		return Gdx.files.internal(fileName).readString();
	}
	
	public static void create(String fileName, String content) {
		FileHandle file = Gdx.files.local(fileName);
		file.writeString(content, false);
	}
	
}
