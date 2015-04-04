package net.mc42.games.events;

public enum EventType {
	MOUSEDOWN,
	MOUSEUP,
	KEYDOWN,
	KEYUP,
	MOUSEWHEELUP,
	MOUSEWHEELDOWN,
	MOUSEMOVE,
	DRAG,
	INIT;
	private int val = 0;
	public String toString(){
		return "[\"" + super.toString() + "\",val:\"" + val + "\"]";
	}
	public EventType setVal(int i){val=i;return this;}
	public int getVal(){return val;}
}
