package net.mc42.games.events;

import net.mc42.global.utils.Pair;

public class Event {
	private EventType etype;
	private int x;
	private int y;
	private int data = 0;
	
	
	public EventType getType(){
		return etype;
	}
	
	public Event(EventType e){
		etype=e;
	}
	
	public Pair<Integer,Integer> getPos(){
		return new Pair<>(x,y);
		
	}
	
	public Event setPos(Pair<Integer,Integer> pos){
		x = pos.first;
		y = pos.last;
		return this;
	}
	
	public int getData(){
		return data;
	}
	
	public Event setData(int d){
		data = d;
		return this;
	}
	
	public String toString(){
		return "{etype:" + etype.toString() + ",pos:" + getPos().toString() + "}";
	}
	
}
