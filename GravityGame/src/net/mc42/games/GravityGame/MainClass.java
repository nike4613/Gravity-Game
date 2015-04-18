package net.mc42.games.GravityGame;
import net.mc42.games.fonts.*;
import net.mc42.games.gui.*;
import net.mc42.games.gui.menu.Button;
import net.mc42.games.gui.menu.Menu;
import net.mc42.global.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.*;

@BaseClass
public class MainClass extends BasicGame
{	
	
	public static GameContainer globalShare;
	
	public MainClass(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) {
		try {
			MainClass.globalShare = gc;
			Fonts.addFont("basefont");
			GUIs.init(gc, Keyboard.KEY_ESCAPE);
			new GUI( "testgui", new Menu("Test Menu").setFont( gc.getGraphics().getFont() ).addElement(new Button().setSize(50,50)) ).setPos(50, 180, 200, 150).reg(0);
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
		g.drawString("AHAhahaHAHahAaH!\naHAHahahHAhahAHAha!", 100, 120);
		
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
			DisplayMode best = new DisplayMode(0, 0);
			for(DisplayMode ds:Display.getAvailableDisplayModes()){
				if(ds.getFrequency()>=60&&ds.isFullscreenCapable()&&ds.getBitsPerPixel()>=24){
					if(ds.getHeight()>best.getHeight()&&ds.getWidth()>best.getWidth()) best = ds;
				}
			}
			Global.log(Global.levels.DEBUG, "Display mode is as follows: " + best.toString());
			
			AppGameContainer appgc = new AppGameContainer(new MainClass("Simple Slick Game"));
			appgc.setDisplayMode(/*best.getWidth()*/640, /*best.getHeight()*/480, false);
			appgc.setTargetFrameRate(/*best.getFrequency()*/60);
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