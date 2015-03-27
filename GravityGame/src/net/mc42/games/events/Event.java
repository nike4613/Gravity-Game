package net.mc42.games.events;

import net.mc42.global.Pair;

import org.newdawn.slick.GameContainer;

public class Event {
	private GameContainer gc;
	private EventType etype;
	
	public GameContainer getGameContainer(){
		return gc;
	}
	
	public EventType getType(){
		return etype;
	}
	
	public Event(GameContainer g, EventType e, int x, int y){
		gc = g;
		etype=e;
		this.x=x;
		this.y=y;
	}
	
	public Pair<Integer,Integer> getPos(){
		return new Pair<>(x,y);
		
	}
	
	protected int x=0,y=0;
	
}
