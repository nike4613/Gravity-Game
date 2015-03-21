package net.mc42.games.fonts;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Font;

public class Fonts {
	private static Map<String,Font> fonts = new HashMap<>();
	
	public static void addFont(String name) throws Exception{
		fonts.put(name, new CustomFont(name));
	}
	
	public static void addFont(Font font, String name) throws Exception{
		fonts.put(name, font);
	}
	
	public static Font getFont(String name){
		return fonts.get(name);
	}
	
}
