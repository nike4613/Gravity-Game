package net.mc42.games.GravityGame;

import net.mc42.games.GameMain;
import net.mc42.games.GravityGame.Widgets.TWLTestWidget;
import net.mc42.games.guiutils.GUIs;
import net.mc42.global.Global;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GravityGameMain extends GameMain{
	
	@Override
	public void init(GameContainer gc) throws Exception {
		
		setExitKeyCombo(Keyboard.KEY_LCONTROL,Keyboard.KEY_Q);
		
		GUIs.setDefaultTheme("resources/gui/gameui.xml");
		
		GUIs.addGUI("test", new TWLTestWidget());
		
	//	ThemeManager tm = ThemeManager.createThemeManager(new URL("classpath:resources/gui/theme.xml"), GUIs.getRenderer());
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws Exception{
	
	}
	@Override
	public void deInit() throws Exception {
		// TODO Auto-generated method stub
		Global.log(Global.levels.DEBUG, "Game Main deInit");
	}

}
