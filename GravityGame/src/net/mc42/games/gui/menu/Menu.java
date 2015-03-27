package net.mc42.games.gui.menu;

import java.util.ArrayList;

import net.mc42.games.events.Event;
import net.mc42.games.gui.Widget;
import net.mc42.global.Global;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Menu implements Widget {
	
	private ArrayList<MenuElement> elems = new ArrayList<>();
	private String name;
	
	public Menu(GameContainer c, String n){
		name = n;
	}
	
	public void addElement(MenuElement e){
		elems.add(e);
	}

	@Override
	public void draw(int x, int y, int szx, int szy, Graphics g)
			throws Exception {
		// TODO Auto-generated method stub
		Global.log(Global.levels.DEBUG, name);
		
	}
	
	public void processEvents(Event e){
		
	}

	@Override
	public void update(GameContainer c, int timeinms) throws Exception {
	}

}
