package net.mc42.games.events;

import net.mc42.global.Pair;

import org.newdawn.slick.GameContainer;

public class DragEvent extends Event {

	Pair<Integer,Integer> old, New;
	
	public DragEvent(GameContainer g, EventType e, int x, int y) {
		super(g, e, x, y);
		// TODO Auto-generated constructor stub
	}

	public DragEvent(GameContainer g, int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated constructor stub
		this(g,EventType.DRAG,oldx,oldy);
		old = new Pair<Integer,Integer>(oldx, oldy);
		New = new Pair<Integer,Integer>(newx, newy);
	}

}
