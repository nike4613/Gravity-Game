package net.mc42.games;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class GameMain {
	private MainClass g;
	public GameMain(){}
	protected void setExitKeyCombo(int... keyCombo){
		g.setExitKeys(keyCombo);
	}
	protected void initI(GameContainer gc, MainClass g) throws Exception{
		this.g = g;
		init(gc);
	}
	public abstract void init(GameContainer gc) throws Exception;
	public abstract void update(GameContainer gc, int delta) throws Exception;
	public abstract void render(GameContainer gc, Graphics g) throws Exception;
	public abstract void deInit() throws Exception;
}
