package net.mc42.games.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.mc42.games.events.Event;
import net.mc42.games.events.EventType;
import net.mc42.global.Global;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GUIs {
	private static Map<String,GUI> guis = new HashMap<>();
	private static Map<String,Boolean> actives = new HashMap<>();
	private static ArrayList<String> renderOrder = new ArrayList<>();
	private static GameContainer gc;
	//private static int exitKey = Keyboard.KEY_ESCAPE;
	private static String selUI = "";
	private static boolean updateAll = false;
	
	public static void init(GameContainer g){
		gc = g;
		//exitKey = exitkey;
		gc.getInput().addListener(new GUIListener(gc.getInput(), gc));
		Global.log(Global.levels.INFO, "GUIs initialized");
	}
	
	protected static void processEvents(Event e){
		try{
		if(e.getType().equals(EventType.MOUSEMOVE)){
			int x = e.getPos().first;
			int y = e.getPos().last;
			
			for(String name:renderOrder){
				if(getActive(name)){
					if(getGUI(name).getPos().first.first<=x&&getGUI(name).getPos().last.first+getGUI(name).getPos().first.first>=x
							&&getGUI(name).getPos().first.last<=y&&getGUI(name).getPos().last.last+getGUI(name).getPos().first.last>=y){
						selUI = name;
						getGUI(name).processEvents(e);
						break;
					} else {
						selUI = "";
					}
				} else {
					selUI = "";
				}
			}
		}
		if(e.getType().equals(EventType.MOUSEDOWN)){
			int x = e.getPos().first;
			int y = e.getPos().last;
			
			for(String name:renderOrder){
				if(getActive(name)){
					if(getGUI(name).getPos().first.first<=x&&getGUI(name).getPos().last.first+getGUI(name).getPos().first.first>=x
							&&getGUI(name).getPos().first.last<=y&&getGUI(name).getPos().last.last+getGUI(name).getPos().first.last>=y){
						selUI = name;
						getGUI(name).processEvents(e);
						break;
					} else {
						selUI = "";
						getGUI(name).processEvents(new Event(gc, EventType.MOUSEMOVE, 0, 0));
					}
				} else {
					selUI = "";
				}
			}
		}
		if(e.getType().equals(EventType.MOUSEUP)){
			if(selUI=="") return;
			getGUI(selUI).processEvents(e);
		}
		if(e.getType().equals(EventType.KEYDOWN)){
			//if(e.getType().getVal() == exitKey) gc.exit();
			if(selUI=="") return;
			getGUI(selUI).processEvents(e);
		}
		if(e.getType().equals(EventType.KEYUP)){
			//if(e.getType().getVal() == exitKey) gc.exit();
			if(selUI=="") return;
			getGUI(selUI).processEvents(e);
		}
		if(e.getType().equals(EventType.MOUSEWHEELDOWN)){
			if(selUI=="") return;
			getGUI(selUI).processEvents(e);
		}
		if(e.getType().equals(EventType.MOUSEWHEELUP)){
			if(selUI=="") return;
			getGUI(selUI).processEvents(e);
		}
		if(e.getType().equals(EventType.DRAG)){
			int x = e.getPos().first;
			int y = e.getPos().last;
			
			for(String name:renderOrder){
				if(getActive(name)){
					if(getGUI(name).getPos().first.first<=x&&getGUI(name).getPos().last.first+getGUI(name).getPos().first.first>=x
							&&getGUI(name).getPos().first.last<=y&&getGUI(name).getPos().last.last+getGUI(name).getPos().first.last>=y){
						selUI = name;
						getGUI(name).processEvents(e);
						break;
					} else {
						selUI = "";
					}
				} else {
					selUI = "";
				}
			}
		}
		}catch(Exception error){
			Global.log(Global.levels.WARNING, error);
		}
	}
	
	public static void regGUI(GUI gui, String name, boolean active, int renderPos){
		guis.put(name, gui);
		actives.put(name, active);
		renderOrder.add(renderPos, name);
		gui.processEvents(new Event(gc, EventType.INIT,0,0));
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
	
	public static void setUpdateAll(boolean b){
		
	}
	
	public static void updateGUIs(GameContainer gc, int timesinceupdate) throws Exception{
		if(updateAll){
			for(Entry<String, GUI> gui:guis.entrySet()){
				if(getActive(gui.getKey())){
					gui.getValue().update(gc, timesinceupdate);
				}
			}
		} else {
			if(selUI==""||!getActive(selUI))return;
			getGUI(selUI).update(gc, timesinceupdate);
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
