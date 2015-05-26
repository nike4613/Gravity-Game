package net.mc42.games;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class GameMain {
	private MainClass game;
	protected ArrayList<String> noStoreProps;
	public GameMain(){}
	protected void setExitKeyCombo(int... keyCombo){
		game.setExitKeys(keyCombo);
	}
	protected void initI(GameContainer gc, MainClass g) throws Exception{
		this.game = g;
		this.noStoreProps = MainClass.propsToNotStore;
		init(gc);
	}
	public abstract void preInit() throws Exception;
	public abstract void init(GameContainer gc) throws Exception;
	public abstract void update(GameContainer gc, int delta) throws Exception;
	public abstract void render(GameContainer gc, Graphics g) throws Exception;
	public abstract void deInit() throws Exception;
}
