package net.mc42.games.events;

public enum EventType {
	CLICK,
	MOUSEDOWN,
	MOUSEUP,
	KEYPRESS,
	KEYDOWN,
	KEYUP,
	HOVERIN,
	HOVEROUT,
	MOUSEWHEELUP,
	MOUSEWHEELDOWN;
	private int val = 0;
	public EventType setVal(int i){val=i;return this;}
	public int getVal(){return val;}
}
