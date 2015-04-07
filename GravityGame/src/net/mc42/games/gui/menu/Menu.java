package net.mc42.games.gui.menu;

import java.util.ArrayList;

import net.mc42.games.events.Event;
import net.mc42.games.gui.Widget;
import net.mc42.global.Global;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Menu implements Widget {
	
	private ArrayList<MenuElement> elems = new ArrayList<>();
	private int selEl = 0;
	private String name;
	private int namePos;
	private Font font;
	
	public Menu(String n){
		name = n;
	}
	
	public Menu addElement(MenuElement e){
		elems.add(e.setFont(font));
		return this;
	}
	public Menu setFont(Font e){
		font = e;
		return this;
	}

	@Override
	public void draw(int x, int y, int szx, int szy, Graphics g)
			throws Exception {
		// TODO Auto-generated method stub
		Font tempFont = g.getFont();
		g.setFont(font);
		
		namePos = (szx/2)-(g.getFont().getWidth(name)/2)+x;
		g.drawString(name, namePos, y);
		for(MenuElement el:elems){
			
		}
		
		g.setFont(tempFont);
	}
	
	public void processEvents(Event e){
		
	}

	@Override
	public void update(GameContainer c, int timeinms) throws Exception {
	}

}
