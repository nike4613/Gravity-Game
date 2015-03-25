package net.mc42.games.gui.menu;

import net.mc42.games.events.Event;
import net.mc42.global.Pair;

import org.newdawn.slick.Graphics;

public class Button implements MenuElement {

	private int xsz=0,ysz=0,x=0,y=0;
	
	@Override
	public void draw(int x, int y, Graphics g)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void onClick(Event e) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void onKeyPress(Event e) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void onKeyDown(Event e) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSize(int x, int y) {
		// TODO Auto-generated method stub
		xsz = x;
		ysz = y;
	}

	@Override
	public Pair<Integer, Integer> getSize() {
		// TODO Auto-generated method stub
		return new Pair<>(xsz,ysz);
	}

	@Override
	public void setPos(int x, int y) {
		// TODO Auto-generated method stub
		this.x=x;
		this.y=y;
	}

	@Override
	public Pair<Integer, Integer> getPos() {
		// TODO Auto-generated method stub
		return new Pair<>(x,y);
	}

}
