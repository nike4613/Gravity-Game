package net.mc42.games.GravityGame;

import java.net.URL;

import net.mc42.games.API;
import net.mc42.games.GameMain;
import net.mc42.games.world.TileSet;
import net.mc42.global.Global;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GravityGameMain extends GameMain{
	
	TileSet t;
	
	@Override
	public void init(GameContainer gc) throws Exception {
		
		setExitKeyCombo(Keyboard.KEY_LCONTROL,Keyboard.KEY_Q);
		
		t = new TileSet(new URL("classpath:/resources/tilesets/tileset1.json"));
		
		//GUIs.setDefaultTheme("resources/gui/gameui.xml");
		
		//GUIs.addGUI("test", new TWLTestWidget());
	//	ThemeManager tm = ThemeManager.createThemeManager(new URL("classpath:resources/gui/theme.xml"), GUIs.getRenderer());
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws Exception{
		//t.getTile().draw(0, 0);
		int x=0,y=0;
		for(int i=0;i<=255;i++){
			t.getTile(0).getTile(i).draw(x, y);
			//Global.log(Global.levels.DEBUG, i);
			x+=17;
			if(x>=gc.getWidth()){x=0;y+=17;}
		}
	}
	@Override
	public void deInit() throws Exception {
		// TODO Auto-generated method stub
		Global.log(Global.levels.DEBUG, "Game Main deInit");
	}

	@Override
	public void preInit() throws Exception {
		// TODO Auto-generated method stub
		Global.log(Global.levels.DEBUG, "Pre-Init//Lib=" + API.Info.toString());
	}

}
