package net.mc42.games.GravityGame;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import net.mc42.games.GameMain;
import net.mc42.games.gui.BasicWidget;
import net.mc42.games.gui.EventHandler;
import net.mc42.games.gui.GUI;
import net.mc42.games.gui.GUIs;
import net.mc42.games.gui.menu.Button;
import net.mc42.games.gui.menu.Menu;
import net.mc42.games.gui.menu.MenuElement;
import net.mc42.global.Global;
import net.mc42.global.Utils;

public class GravityGameMain extends GameMain{

	@EventHandler
	public static void button1(GUI g,MenuElement m){
		Global.log(Global.levels.DEBUG, "button1");
	}
	@EventHandler
	public static void button2(GUI g,MenuElement m){
		Global.log(Global.levels.DEBUG, "button2");
	}
	@EventHandler
	public static void exitbutton(GUI g,MenuElement m) throws Exception{
		Global.log(Global.levels.DEBUG,"Closing GUI " + g.getName());
		GUIs.setActive(g.getName(), false);
		Thread.sleep(1000);
		GUIs.setActive("closeui", true);
	}
	@EventHandler
	public static void closeGame(GUI g,MenuElement m){
		System.exit(0);
	}
	
	@Override
	public void init(GameContainer gc) throws Exception {
		// TODO Auto-generated method stub
		setExitKeyCombo(Keyboard.KEY_LCONTROL,Keyboard.KEY_Q);
		new GUI("ui3", "testgui", new BasicWidget(), 0).setPos(10, 10, 300, 300);
		new GUI( "mainui", "testgui", new Menu("Test Menu")
			.setFont( gc.getGraphics().getFont() )
			.setFontColor(Color.green)
			.addElement(
				new Button("button","Button-For-Testing!!!")
				.setClickAction(Utils.getAnnotatedMethod(EventHandler.class, this.getClass(), "button1"))
			) 
			.addElement(
				new Button("button","Button 2!!!")
				.setClickAction(Utils.getAnnotatedMethod(EventHandler.class, this.getClass(), "button2"))
			)
			.addElement(
				new Button("button","Close UI")
				.setClickAction(Utils.getAnnotatedMethod(EventHandler.class, this.getClass(), "exitbutton"))
			)
		,0).setPos(50, 180, 300, 250);
		new GUI( "closeui", "testgui", new Menu("Close")
			.setFont( gc.getGraphics().getFont() )
			.setFontColor(Color.red)
			.addElement(
				new Button("button","Close Game")
				.setClickAction(Utils.getAnnotatedMethod(EventHandler.class, this.getClass(), "closeGame"))
			)
		,0,false).setPos(50, 180, 300, 250);
	}

	@Override
	public void update(GameContainer gc, int delta) throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws Exception{
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.drawString("Howdy!", 100, 100);
	}
	@Override
	public void deInit() throws Exception {
		// TODO Auto-generated method stub
		Global.log(Global.levels.DEBUG, "Game Main deInit");
	}

}
