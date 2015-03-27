package net.mc42.games.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.mc42.games.events.Event;
import net.mc42.games.events.EventType;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GUIs {
	private static Map<String,GUI> guis = new HashMap<>();
	private static Map<String,Boolean> actives = new HashMap<>();
	private static ArrayList<String> renderOrder = new ArrayList<>();
	private static String selUI = "";
	
	
	protected static void processEvents(Event e){
		if(e.getType() == EventType.MOUSEMOVE){
			int x = e.getPos().first;
			int y = e.getPos().last;
			
			for(String name:renderOrder){
				if(getActive(name)){
					if(getGUI(name).getPos().first.first>=x&&getGUI(name).getPos().last.first<=x
							&&getGUI(name).getPos().first.last>=y&&getGUI(name).getPos().last.last<=y){
						selUI = name;
						getGUI(name).processEvents(e);
						break;
					}
				}
			}
		}
		if(e.getType() == EventType.CLICK){
			
		}
	}
	
	public static void regGUI(GUI gui, String name, boolean active, int renderPos){
		guis.put(name, gui);
		actives.put(name, active);
		renderOrder.add(renderPos, name);
	}
	
	public static void setActive(String name, boolean active){
		actives.put(name, active);
		/*if(active){
			GUI g;
			for(Entry<String, GUI> g124:guis.entrySet()){
				g = g124.getValue();
				g.getPos().first.first
			}
		}*/
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
		for(String name:renderOrder){
			if(getActive(name)){
				getGUI(name).draw(g); 
			}
		}
	}
}
