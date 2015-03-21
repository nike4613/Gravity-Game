package net.mc42.games.GravityGame;
import java.util.HashMap;
import java.util.Map;

import net.mc42.games.fonts.CustomFont;
import net.mc42.global.Global;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class MainClass extends BasicGame
{
	static Map<String,Font> fonts = new HashMap<>();
	
	public MainClass(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) {
		try {
			fonts.put("altchars", new CustomFont("basefont"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Global.log(Global.levels.SEVERE, "Could not initialize game!", e);
			System.exit(1);
		}
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.setColor(Color.white);
		g.drawString("Howdy!", 100, 100);
		g.setFont(fonts.get("altchars"));
		g.setColor(Color.red);
		g.drawString("AHAhahaHAHahAaH!\naHAHahahHAhahAHAha!", 10, 120);
		
	}

	public static void main(String[] args)
	{
		Global.setDebugMode(true);
		System.setProperty("org.lwjgl.opengl.Window.undecorated","false");
		Global.log(Global.levels.DEBUG, "Beggining program");
		
		
		//System.exit(0);;
		try
		{
			
			AppGameContainer appgc;
			appgc = new AppGameContainer(new MainClass("Simple Slick Game"));
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