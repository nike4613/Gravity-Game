package net.mc42.games.gui.menu;

import net.mc42.global.Pair;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

public interface MenuElement {
	public void draw(int x, int y, Graphics g)
			throws Exception;
	public MenuElement setSize(int x, int y);
	public MenuElement setPos(int x, int y);
	public Pair<Integer,Integer> getSize();
	public Pair<Integer,Integer> getPos();
	public MenuElement setFont(Font f);
	/*public void onClick(Event e) throws Exception;
	public void onKeyPress(Event e) throws Exception;
	public void onKeyDown(Event e) throws Exception;
	public void onKeyUp(Event e) throws Exception;*/
}
