package net.mc42.global.utils;

public class Ptr<T> {
	public T val;
	
	public Ptr(T o){
		val = o;
	}
	
	public String toString(){
		return val.toString();
	}
	
}