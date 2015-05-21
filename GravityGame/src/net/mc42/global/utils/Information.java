package net.mc42.global.utils;

public abstract class Information {
	public abstract String getVersion();
	public abstract String getAuthor();
	public abstract String getName();
	public String toString(){
		return getName() + " v'" + getVersion() + "' by " + getAuthor();
	}
}
