package net.mc42.games.guiutils;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import net.mc42.global.Global;
import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.renderer.Renderer;
import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
import de.matthiasmann.twl.theme.ThemeManager;

public class GUIs {

	private static Renderer renderer;// = new LWJGLRenderer();
	private static HashMap<String,GUI> guis;
	private static boolean initialized = false;
	private static ThemeManager defaultTheme;
	
	public static void init() throws Exception{
		if(initialized) return;
		
		initI();
	}
	public static void init(ThemeManager def) throws Exception{
		if(initialized) return;
		
		defaultTheme = def;
		initI();
	}
	
	/**
	 * Sets the default theme.
	 * @param theme The theme managet to use
	 */
	public static void setDefaultTheme(ThemeManager theme){
		defaultTheme = theme;
	}
	
	/**
	 * Sets the default theme.
	 * @param url The URL in the classpath 
	 * @throws IOException 
	 */
	public static void setDefaultTheme(String url) throws IOException{
		setDefaultTheme(Global.class.getClassLoader().getResource(url));
	}
	
	/**
	 * Sets the default theme.
	 * @param url The URL object to theme XML file
	 * @throws IOException 
	 */
	public static void setDefaultTheme(URL url) throws IOException{
		setDefaultTheme(ThemeManager.createThemeManager(url, getRenderer()));
	}
	
	private static void initI() throws Exception{
		initialized = true;
		renderer = new LWJGLRenderer();
		guis = new HashMap<String,GUI>();
	}
	
	public static void addGUI(String name, GUI gui) throws Exception{
		if(!initialized) init();
		if(defaultTheme!=null)gui.applyTheme(defaultTheme);
		guis.put(name, gui);
	}
	
	public static void addGUI(String name, Widget w) throws Exception{
		if(!initialized) init();
		addGUI(name, new GUI(w, renderer));
	}
	
	public static void removeGUI(String name){
		guis.remove(name);
	}
	
	public static GUI getGUI(String name) throws Exception{
		if(!initialized) init();
		return guis.get(name);
	}
	
	public static void setRenderer(Renderer rend){
		renderer = rend;
	}
	
	public static Renderer getRenderer(){
		return renderer;
	}
	
	public static void update(){
		for(int i=0;i<guis.keySet().size();i++)
			guis.get(guis.keySet().toArray()[i]).update();
	}
	
	public static void draw(){
		for(int i=0;i<guis.keySet().size();i++)
			guis.get(guis.keySet().toArray()[i]).draw();
	}
	
}
