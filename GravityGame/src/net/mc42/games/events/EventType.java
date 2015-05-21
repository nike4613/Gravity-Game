package net.mc42.games.events;

import net.mc42.global.Utils;

public enum EventType {
	MOUSE_DOWN(true,false),
	MOUSE_UP(true,false),
	KEY_DOWN(false,true),
	KEY_UP(false,true),
	MOUSE_WHEEL(true,false),
	MOUSE_MOVE(true,false),
	MOUSE_CLICK(true,false);
	private EventType(boolean m,boolean k){
		mouse = m;
		key = k;
	}
	private boolean mouse;
	private boolean key;
	public boolean isMouse(){return mouse;}
	public boolean isKeyboard(){return key;}
	public String toString(){
		return "[\"" + super.toString() + "\"]";
	}
	public String toCamelCaseType(){
		return Utils.capitalizeFirstLetter(super.toString().toLowerCase());
	}
	private int data = 0;
	public int getData(){return data;}
	public EventType setData(int d){data=d;return this;}
}
