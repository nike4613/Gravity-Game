package net.mc42.games.GravityGame;
import net.mc42.games.fonts.Fonts;
import net.mc42.games.gui.GUI;
import net.mc42.games.gui.GUIs;
import net.mc42.games.gui.testwiget;
import net.mc42.global.Global;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class MainClass extends BasicGame
{	
	public MainClass(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) {
		try {
			Fonts.addFont("basefont");
			new GUI("testgui", new testwiget());
			GUIs.getGUI("testgui").setPos(10, 180, 300, 250);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Global.log(Global.levels.SEVERE, "Could not initialize game!", e);
			System.exit(1);
		}
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException 
	{try {
		GUIs.updateGUIs(gc, i);
	} catch (Exception e) {
			// TODO Auto-generated catch block
		Global.log(Global.levels.SEVERE, "Error while updating game!", e);
	}}

	@Override
	public void render(GameContainer gc, Graphics g)throws SlickException
	{try {
		g.setColor(Color.white);
		g.drawString("Howdy!", 100, 100);
		g.setFont(Fonts.getFont("basefont"));
		g.setColor(Color.red);
		g.drawString("AHAhahaHAHahAaH!\naHAHahahHAhahAHAha!", 10, 120);
		
		GUIs.drawGUIs(g);
	} catch (Exception e) {
			// TODO Auto-generated catch block
		Global.log(Global.levels.SEVERE, "Error in Game.render()", e);
	}}

	public static void main(String[] args)
	{
		Global.setDebugMode(true);
		System.setProperty("org.lwjgl.opengl.Window.undecorated","false");
		Global.log(Global.levels.DEBUG, "Beggining program");
		
		
		//System.exit(0);;
		try
		{
			AppGameContainer appgc = new AppGameContainer(new MainClass("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
			appgc.setTargetFrameRate(60);
			appgc.setShowFPS(false);
			try {
				//appgc.setIcon("resources/icon/gameIcon.png");
				Global.log(Global.levels.DEBUG, "Setting application icons");
				appgc.setIcons(new String[]{"resources/icon/gameIcon32.png","resources/icon/gameIcon24.png","resources/icon/gameIcon16.png"});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Global.log(Global.levels.WARNING, "Error occured while setting icons... Continuing", e);
			}
			appgc.start();
		}
		catch (Exception ex)
		{
			Global.log(Global.levels.SEVERE, "Could not start game!", ex);
		}
	}
}