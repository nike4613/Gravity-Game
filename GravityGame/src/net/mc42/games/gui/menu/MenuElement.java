package net.mc42.games.gui.menu;

import net.mc42.games.events.Event;
import net.mc42.games.gui.EventHandler;
import net.mc42.global.Pair;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

public interface MenuElement {
	public void draw(Graphics g)
			throws Exception;
	public MenuElement setSize(int x, int y);
	public MenuElement setPos(int x, int y);
	public MenuElement setDrawPosOff(int x, int y);
	public Pair<Integer,Integer> getSize();
	public Pair<Integer,Integer> getPos();
	public MenuElement setFont(Font f);
	@EventHandler
	public void onInit(Event e) throws Exception;
	@EventHandler
	public void onSelect(SelectEvent e) throws Exception;
	@EventHandler
	public void onDeselect(DeselectEvent e) throws Exception;
	/*
	public void onKeyPress(Event e) throws Exception;
	public void onKeyDown(Event e) throws Exception;
	public void onKeyUp(Event e) throws Exception;*/
}
