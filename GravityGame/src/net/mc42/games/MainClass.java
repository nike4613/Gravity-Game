package net.mc42.games;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Properties;

import net.mc42.games.gui.GUIs;
import net.mc42.global.BaseClass;
import net.mc42.global.Global;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

@BaseClass
public class MainClass extends BasicGame
{	
	
	private static GameContainer globalShare;
	public static Properties props;
	private static GameMain mainObj;
	
	
	public MainClass(String gamename)
	{
		super(gamename);
	}
	
	long exit=0;private void checkForceExit(GameContainer gc){long is=0,tmp=exit<<8;boolean down=true;while((tmp=(tmp>>8))!=0){is=(tmp&0xFF);down=down&&gc.getInput().isKeyDown((int)is);}if(down){System.exit(0);}}protected void setExitKeys(int... keys){int i=0,out=0;for(int key:keys){out|=key<<(i++*8);}exit=out;Thread t=new Thread(){public void run(){try{deInit();}catch(Exception e){}}};t.setName("deinit");Runtime.getRuntime().addShutdownHook(t);}
	
	private static void deInit() throws Exception{
		//Cleanup
		//Global.log(Global.levels.INFO, "Closing program... But why?");
		Global.log(Global.levels.INFO, props.getProperty("exitLogMessage"));
		try {mainObj.deInit();} catch (Exception e) {}
		globalShare.exit();
		props.store(new FileOutputStream(new File(System.getProperty("user.dir")+"/configs/properties.ini")), "Properties");
	}
	
	@Override
	public void init(GameContainer gc) {
		try {
			MainClass.globalShare = gc;
			//Fonts.addFont("basefont");
			GUIs.init(gc);
			
			mainObj.initI(gc, this);
			//setExitKeys(Keyboard.KEY_LCONTROL,Keyboard.KEY_Q);
			/*new GUI( "mainui", "testgui", new Menu("Test Menu")
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
			,0,false).setPos(50, 180, 300, 250);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Global.log(Global.levels.FATAL, "Could not initialize game!", e);
			System.exit(1);
		}
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException 
	{try {
		GUIs.updateGUIs(gc, i);
		mainObj.update(gc, i);
		checkForceExit(gc);
	} catch (Exception e) {
			// TODO Auto-generated catch block
		Global.log(Global.levels.SEVERE, "Error while updating game!", e);
	}}

	@Override
	public void render(GameContainer gc, Graphics g)throws SlickException
	{try {
		//g.setColor(Color.white);
		//g.drawString("Howdy!", 100, 100);
		//g.setFont(Fonts.getFont("basefont"));
		//g.setColor(Color.red);
		//g.drawString("AHAhahaHAHahAaH!\naHAHahahHAhahAHAha!", 100, 120);
		
		GUIs.drawGUIs(g);
		
		mainObj.render(gc, g);
		
	} catch (Exception e) {
		Global.log(Global.levels.SEVERE, "Error in Game.render()", e);
	}}

	public static void main(String[] args)
	{
		Properties defs = new Properties();
		defs.setProperty("mainClass", "net.mc42.games.MainClass");
		defs.setProperty("debugMode", "false");
		defs.setProperty("unDecorated", "false");
		defs.setProperty("fullscreen", "true");
		defs.setProperty("fps", "60");
		defs.setProperty("exitLogMessage", "Closing game...");
		defs.setProperty("title", "MC42 Slick UI and Engine Frontend");
		try {
			
			defs.load(ClassLoader.getSystemResourceAsStream("resources/properties.ini"));
			props = defs;
			File propf = new File(System.getProperty("user.dir")+"/configs/properties.ini");
			if(!propf.exists()){
				new File(System.getProperty("user.dir")+"/configs").mkdirs();
				propf.createNewFile();
			}
			props.load(new FileInputStream(propf));
			//props.store(new FileOutputStream(propf), "Properties");
		} catch (Exception e1) {
			Global.log(Global.levels.WARNING, "Failed to load properties... using defaults", e1);
			props = defs;
		}
		Thread.currentThread().setName("execution");
		Global.setDebugMode(props.getProperty("debugMode").contains("true"));
		System.setProperty("org.lwjgl.opengl.Window.undecorated",props.getProperty("unDecorated"));
		Global.log(Global.levels.DEBUG, "Beggining program");
		
		Enumeration<?> enumr = props.propertyNames();
		
		while(enumr.hasMoreElements()){
			String name=enumr.nextElement().toString();
			
			if(name.startsWith("Sys:"))System.setProperty(name.substring(4), props.getProperty(name));
		}
		
		//System.exit(0);;
		try
		{
			DisplayMode best = new DisplayMode(0, 0);
			//DisplayMode[] dss = Display.getAvailableDisplayModes();
			for(DisplayMode ds:Display.getAvailableDisplayModes()){
				if(ds.getFrequency()>=60&&ds.isFullscreenCapable()&&ds.getBitsPerPixel()>=24){
					if(ds.getHeight()>best.getHeight()&&ds.getWidth()>best.getWidth()) best = ds;
				}
			}
			Global.log(Global.levels.DEBUG, "Display mode is as follows: " + best.toString());
			
			AppGameContainer appgc = new AppGameContainer(new MainClass(props.getProperty("title")));
			appgc.setDisplayMode(/*best.getWidth()*/640, /*best.getHeight()*/480, false);
			appgc.setTargetFrameRate(/*best.getFrequency()*60*/Integer.parseInt(props.getProperty("fps")));
			appgc.setShowFPS(false);
			try {
				//appgc.setIcon("resources/icon/gameIcon.png");
				Global.log(Global.levels.DEBUG, "Setting application icons");
				appgc.setIcons(new String[]{"resources/icon/gameIcon32.png","resources/icon/gameIcon24.png","resources/icon/gameIcon16.png"});
			} catch (Exception e) {
				Global.log(Global.levels.WARNING, "Error occured while setting icons... Continuing", e);
			}
			try{
				Class<?> main = ClassLoader.getSystemClassLoader().loadClass(props.getProperty("mainClass"));
				if (!(main.getSuperclass()==GameMain.class)) throw new Exception("Couldn't load class");
				mainObj = (GameMain) main.newInstance();
			} catch (Exception e) {
				Global.log(Global.levels.FATAL, "Could not locate execution class", e);
			}
			
			appgc.start();
		}
		catch (Exception ex)
		{
			Global.log(Global.levels.FATAL, "Could not start game!", ex);
		}
	}
}