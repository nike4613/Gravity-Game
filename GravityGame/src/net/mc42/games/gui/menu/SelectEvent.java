package net.mc42.games.gui.menu;

import org.newdawn.slick.GameContainer;

import net.mc42.games.events.Event;
import net.mc42.games.events.EventType;

public class SelectEvent extends Event {

	public SelectEvent(GameContainer g, int x, int y) {
		super(g, null, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public EventType getType(){
		return null;
	}

}
