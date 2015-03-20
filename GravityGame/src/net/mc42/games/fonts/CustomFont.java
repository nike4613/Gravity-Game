package net.mc42.games.fonts;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.mc42.games.ImageUtils;
import net.mc42.global.Global;
import net.mc42.global.Pair;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class CustomFont {
	
	private SpriteSheet font;
	private Map<Character,Pair<Integer,Integer>> charmap = new HashMap<>();
	private String name;
	
	public CustomFont(String fontfile) throws Exception{
		loadFontFile(fontfile);
	}
	
	private void loadFontFile(String filename) throws Exception{
		ZipInputStream file = new ZipInputStream(new FileInputStream(filename));
		ZipEntry e;
		byte[] buffer = new byte[4096];
		byte[] image = new byte[4096];
		String text = new String("");
		while((e = file.getNextEntry()) != null){
			Global.log(Global.levels.DEBUG, "Found file " + e.getName() + " in font file " + filename);
			int len = 0;
			while((len = file.read(buffer))>0){
				Global.log(Global.levels.DEBUG, "Read " + len + " bytes from file " + filename + "!" + e.getName());
			}
			if(e.getName() == "icons.png"){
				image = buffer;
			}
			if(e.getName() == "charmap.txt"){
				text = new String(buffer);
			}
		}
		name = filename;
		file.close();
		loadImages(image);
		loadCharacters(text);
	}
	
	private void loadImages(final byte[] image) throws Exception{
		Image i = ImageUtils.getImageFromBytes(image);
		if(i.getWidth()!=i.getHeight()&&(i.getWidth()%16)!=0) 
			throw new FontLoadException("The image file for font " + name + " is incorrectly proportioned!");
		font = new SpriteSheet(i, i.getWidth()/16, i.getHeight()/16);
	}
	
	private void loadCharacters(String text){
		List<String> lines = new ArrayList<>();
		while(text.indexOf('\n')>0){
			lines.add(text.substring(0, text.indexOf('\n')));
			text = text.substring(text.indexOf('\n'));
		}
		
	}
	
}
