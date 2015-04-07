package net.mc42.games.gui.menu;

import net.mc42.games.events.Event;
import net.mc42.games.gui.EventHandler;
import net.mc42.global.Pair;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

public class Button implements MenuElement {
	int xsz=0;
	int ysz=0;
	int x=0;
	int y=0;
	Font f;
	
	@Override
	public void draw(int x, int y, Graphics g)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@EventHandler
	public void onSelect(Event e) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@EventHandler
	public void onMouseDown(Event e) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@EventHandler
	public void onMouseUp(Event e) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@EventHandler
	public void onKeyUp(Event e) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@EventHandler
	public void onKeyDown(Event e) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MenuElement setSize(int x, int y) {
		// TODO Auto-generated method stub
		xsz = x;
		ysz = y;
		return this;
	}

	@Override
	public Pair<Integer, Integer> getSize() {
		// TODO Auto-generated method stub
		return new Pair<>(xsz,ysz);
	}

	@Override
	public MenuElement setPos(int x, int y) {
		// TODO Auto-generated method stub
		this.x=x;
		this.y=y;
		return this;
	}

	@Override
	public Pair<Integer, Integer> getPos() {
		// TODO Auto-generated method stub
		return new Pair<>(x,y);
	}

	@Override
	public MenuElement setFont(Font f) {
		// TODO Auto-generated method stub
		this.f = f;
		return this;
	}

}
