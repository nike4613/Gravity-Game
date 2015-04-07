package net.mc42.games.gui.menu;

import org.newdawn.slick.GameContainer;

import net.mc42.games.events.Event;
import net.mc42.games.events.EventType;

public class SelectEvent extends Event {

	public SelectEvent(GameContainer g, EventType e, int x, int y) {
		super(g, e, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public EventType getType(){
		return null;
	}

}
