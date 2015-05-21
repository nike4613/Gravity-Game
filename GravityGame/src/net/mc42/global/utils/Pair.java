package net.mc42.global.utils;

public class Pair<T1,T2> {
	public T1 first;
	public T2 last;
	public Pair(T1 f, T2 l){
		first = f;
		last = l;
	}
	public String toString(){
		return "[first:\"" + first.toString() + "\",last:\"" + last.toString() + "\"]";
	}
}
