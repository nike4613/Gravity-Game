package net.mc42.global.utils;

import net.mc42.global.Global;

public abstract class ThreadedRunnable implements Runnable {
	public abstract void runT();
	public void run(){
		Thread t = new Thread(){
			public void run(){
				this.setName("Runnable" + this.getId());
				try{
				runT();
				}catch(Exception e){
					Global.log(Global.levels.WARNING, e);
				}
			}
		};
		t.start();
	}
}
