package net.mc42.global.utils;

import java.io.Serializable;
import java.util.ArrayList;

public class ByteString implements Serializable {

	private ArrayList<Byte> bytes;
	/**
	 * 
	 */
	private static final long serialVersionUID = -7924464729350564251L;

	public ByteString(){
		bytes = new ArrayList<Byte>();
	}
	public ByteString(byte[] b){
		bytes = new ArrayList<Byte>();
		for(byte by:b){
			//if(by == 0)
				//continue;
			bytes.add(by);
		}
	}
	public ByteString(String s){
		bytes = new ArrayList<Byte>();
		for(char c:s.toCharArray())
			bytes.add((byte) c);
	}
	public ByteString(ArrayList<Byte> b){
		bytes = new ArrayList<Byte>();
		for(byte by:b)
			bytes.add(by);
	}
	
	public String toString(){
		String out = "";
		char[] chs = new char[bytes.size()];
		for (int i=0;i<bytes.size();i++){
			chs[i] = (char)(bytes.get(i).byteValue());
			//Global.log(Global.levels.DEBUG, new String(new char[]{(char)(bytes.get(i).byteValue())}));
		}
		out = new String(chs);
		//Global.log(Global.levels.DEBUG, out);
		return out;
	}
	public byte[] toByteArray(){
		byte[] out = new byte[bytes.size()]; 
		for (int i=0;i<bytes.size();i++)
			out[i] = bytes.get(i);
		return out;
	}
	
	public void add(byte[] b){
		for(byte by:b){
			//if(by == 0)
				//continue;
			bytes.add(by);
		}
		//Global.log(Global.levels.DEBUG, "Adding byte[] with value " + b + " and " + bytes);
	}
	public void add(ArrayList<Byte> b){
		for(byte by:b)
			bytes.add(by);
	}
	public void add(String s){
		for(char c:s.toCharArray())
			bytes.add((byte) c);
	}

}
