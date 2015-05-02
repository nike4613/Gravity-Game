package net.mc42.games.gui;

import net.mc42.games.events.Event;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface Widget {
	/**
	 * 
	 * @param x X
	 * @param y Y 
	 * @param szx Size X
	 * @param g Graphics
	 * @return Height rendered
	 * @throws Exception
	 */
	public int draw(int x, int y, int szx, Graphics g) throws Exception;
	public void titlePos(int x, int y, int szx, Graphics g) throws Exception;
	public void update(GameContainer container, int timeinms) throws Exception;
	public void processEvents(Event e);
	public void setGUI(GUI gui);
}