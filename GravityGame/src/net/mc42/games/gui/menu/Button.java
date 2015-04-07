package net.mc42.games.gui.menu;

import net.mc42.games.events.Event;
import net.mc42.games.gui.EventHandler;
import net.mc42.global.Global;
import net.mc42.global.Pair;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

public class Button implements MenuElement {
	int xsz=0;
	int ysz=0;
	int x=0;
	int y=0;
	Font f;
	
	public void draw(Graphics g)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@EventHandler
	public void onSelect(SelectEvent e) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@EventHandler
	public void onMousedown(Event e) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@EventHandler
	public void onMouseup(Event e) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@EventHandler
	public void onKeyup(Event e) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@EventHandler
	public void onKeydown(Event e) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@EventHandler
	public void onInit(Event e) throws Exception {
		Global.log(Global.levels.INFO, "Button INIT event!");
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
