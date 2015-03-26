package net.mc42.games.gui.menu;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import net.mc42.games.events.Event;
import net.mc42.games.gui.GUIListener;
import net.mc42.games.gui.Widget;

public class Menu implements Widget {
	
	private ArrayList<MenuElement> elems = new ArrayList<>();
	private String name;
	
	public Menu(GameContainer c, String n){
		name = n;
		Input input = c.getInput();
	}
	
	public void addElement(MenuElement e){
		elems.add(e);
	}

	@Override
	public void draw(int x, int y, int szx, int szy, Graphics g)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	protected void processEvents(Event e){
		
	}

	@Override
	public void update(GameContainer c, int timeinms) throws Exception {
		// TODO Auto-generated method stub
		Input input = c.getInput();
	}

}
