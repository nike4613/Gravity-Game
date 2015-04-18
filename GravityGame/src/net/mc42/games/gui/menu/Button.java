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
	int offx=0;
	int offy=0;
	Font f;
	
	public void draw(Graphics g)
			throws Exception {
		// TODO Auto-generated method stub
		g.drawRect(x+offx, y+offy, xsz, ysz);
	}

	@EventHandler
	public void onSelect(SelectEvent e) throws Exception {
		// TODO Auto-generated method stub
		Global.log(Global.levels.DEBUG,"I got selected!!!");
	}
	
	@EventHandler
	public void onMousedown(Event e) throws Exception {
		// TODO Auto-generated method stub
		Global.log(Global.levels.DEBUG, "md:" + e.getType().getVal());
		
	}
	
	@EventHandler
	public void onMouseup(Event e) throws Exception {
		// TODO Auto-generated method stub
		Global.log(Global.levels.DEBUG, "mu:" + e.getType().getVal());
	}

	@EventHandler
	public void onKeyup(Event e) throws Exception {
		// TODO Auto-generated method stub
		Global.log(Global.levels.DEBUG, "ku:" + e.getType().getVal());
	}

	@EventHandler
	public void onKeydown(Event e) throws Exception {
		// TODO Auto-generated method stub
		Global.log(Global.levels.DEBUG, "kd:" + e.getType().getVal());
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

	@EventHandler
	public void onDeselect(DeselectEvent e) throws Exception {
		// TODO Auto-generated method stub
		Global.log(Global.levels.DEBUG,"I got deselected... :(");
	}

	@Override
	public MenuElement setDrawPosOff(int x, int y) {
		// TODO Auto-generated method stub
		offx=x;
		offy=y;
		return this;
	}

}
