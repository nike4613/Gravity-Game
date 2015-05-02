package net.mc42.games.gui;

import net.mc42.games.events.Event;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface Widget {
	public void draw(int x, int y, int szx, int szy, Graphics g) throws Exception;
	public void titlePos(int x, int y, int szx, int szy, Graphics g) throws Exception;
	public void update(GameContainer container, int timeinms) throws Exception;
	public void processEvents(Event e);
	public void setGUI(GUI gui);
}