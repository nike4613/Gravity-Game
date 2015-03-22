package net.mc42.games.fonts;

import java.util.HashMap;
import java.util.Map;

import net.mc42.global.Global;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

public class Fonts {
	private static Map<String,Font> fonts = new HashMap<>();
	
	static {
		try {
			addFont(new Graphics(0,0).getFont(), "default");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Global.log(Global.levels.SEVERE, "Failed to initialize fonts!", e);
		}
	}
	
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
