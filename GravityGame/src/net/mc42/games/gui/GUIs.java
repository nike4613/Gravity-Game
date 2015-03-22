package net.mc42.games.gui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GUIs {
	private static Map<String,GUI> guis = new HashMap<>();
	private static Map<String,Boolean> actives = new HashMap<>();
	
	public static void regGUI(GUI gui, String name, boolean active){
		guis.put(name, gui);
		actives.put(name, active);
	}
	
	public static void setActive(String name, boolean active){
		actives.put(name, active);
	}
	
	public static GUI getGUI(String name){
		return guis.get(name);
	}
	
	public static boolean getActive(String name){
		return actives.get(name);
	}
	
	public static void updateGUIs(GameContainer gc, int timesinceupdate) throws Exception{
		for(Entry<String, GUI> gui:guis.entrySet()){
			if(getActive(gui.getKey())){
				gui.getValue().update(gc, timesinceupdate);
			}
		}
	}
	
	public static void drawGUIs(Graphics g) throws Exception{
		for(Entry<String, GUI> gui:guis.entrySet()){
			if(getActive(gui.getKey())){
				gui.getValue().draw(g);
			}
		}
	}
}
