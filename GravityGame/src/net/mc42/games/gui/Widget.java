package net.mc42.games.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface Widget {
	public void draw(int x, int y, int szx, int szy, Graphics g) throws Exception;
	public void update(GameContainer container, int timeinms) throws Exception;
}
