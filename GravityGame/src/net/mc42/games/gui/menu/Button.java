package net.mc42.games.gui.menu;

import net.mc42.games.events.Event;
import net.mc42.games.gui.EventHandler;
import net.mc42.global.Global;
import net.mc42.global.Pair;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.util.xml.XMLElement;
import org.newdawn.slick.util.xml.XMLParser;

public class Button implements MenuElement {
	int xsz=0;
	int ysz=0;
	int x=0;
	int y=0;
	int offx=0;
	int offy=0;
	boolean selected = false;
	int mouseDown = 0x0;
	String text;
	Image[] im_nonsel = new Image[3];
	Image[] im_sel = new Image[3];
	Image[] im_click = new Image[3];
	Font f;
	
	public Button(String name, String text) throws Exception{
		String imgfile ;//= (new File("/resources/gui/" + name + ".png").isFile())?"/resources/gui/" + name + ".png":"/resources/gui/guis.png";
		String sectfile = "/resources/gui/" + name + ".xml";
		XMLParser xml = new XMLParser();
		XMLElement el = xml.parse(name, getClass().getResourceAsStream(sectfile));
		
		//process xml
		if(el.getName()!="button") throw new Exception("Sections XML file supposed to be format '<button>...</button>'");
		imgfile = el.getAttribute("image");
		//Global.log(Global.levels.DEBUG, imgfile);
		Image img = new Image(getClass().getResourceAsStream(imgfile),name,false);
		img.setFilter(Image.FILTER_NEAREST);
		for(int i = 0;i<el.getChildren().size();i++){
			XMLElement e = el.getChildren().get(i);
			
			if(e.getName()=="unselected"){
				for(int ik = 0;ik<e.getChildren().size();ik++){
					XMLElement ej = e.getChildren().get(ik);
					im_nonsel[Integer.parseInt(ej.getAttribute("por").replace('l', '0').replace('m', '1').replace('r', '2'))] = 
						img.getSubImage(ej.getIntAttribute("x"), ej.getIntAttribute("y"), ej.getIntAttribute("width"), ej.getIntAttribute("height"));
				}
			}
			if(e.getName()=="selected"){
				for(int ik = 0;ik<e.getChildren().size();ik++){
					XMLElement ej = e.getChildren().get(ik);
					im_sel[Integer.parseInt(ej.getAttribute("por").replace('l', '0').replace('m', '1').replace('r', '2'))] = 
						img.getSubImage(ej.getIntAttribute("x"), ej.getIntAttribute("y"), ej.getIntAttribute("width"), ej.getIntAttribute("height"));
				}
			}
			if(e.getName()=="clicked"){
				for(int ik = 0;ik<e.getChildren().size();ik++){
					XMLElement ej = e.getChildren().get(ik);
					im_click[Integer.parseInt(ej.getAttribute("por").replace('l', '0').replace('m', '1').replace('r', '2'))] = 
						img.getSubImage(ej.getIntAttribute("x"), ej.getIntAttribute("y"), ej.getIntAttribute("width"), ej.getIntAttribute("height"));
				}
			}
			/*if(e.getName()=="seperation"){
				sepspace = e.getIntAttribute("value");
			}*/
		}
		this.text = text;
	}
	
	public Button(){}
	
	public void draw(Graphics g)
			throws Exception {
		// TODO Auto-generated method stub
		Color c = g.getColor();
		g.setColor(((mouseDown&0x1)==1)?Color.green:Color.yellow);
		g.setColor((selected)?g.getColor():Color.red);
		g.drawRect(x+offx, y+offy, xsz, ysz);
		g.setColor(c);
	}
	
	public void update(GameContainer gc){
		for(int i=0;i<6;i++){
			if(!gc.getInput().isMouseButtonDown(i))mouseDown &= ~(1<<i);
			else mouseDown |= 1<<i;
		}
		//Global.log(Global.levels.DEBUG, "mouseDown=" + mouseDown);
	}

	@EventHandler
	public void onSelect(SelectEvent e) throws Exception {
		// TODO Auto-generated method stub
		Global.log(Global.levels.DEBUG,"I got selected!!!");
		selected = true;
	}

	@EventHandler
	public void onMouseup(Event e){
		if(e.getType().getVal()==0)Global.log(Global.levels.DEBUG,"Yae! I got clicked!");
	}
	
	@EventHandler
	public void onKeyup(Event e) throws Exception {
		// TODO Auto-generated method stub
		Global.log(Global.levels.DEBUG, "ku:" + e.getType().getVal());
	}

	@EventHandler
	public void onKeydown(Event e) throws Exception {
		// TODO Auto-generated method stub
		Global.log(Global.levels.DEBUG, "kd:" + e.getType().getVal());
	}
	
	@EventHandler
	public void onInit(Event e) throws Exception {
		Global.log(Global.levels.INFO, "Button INIT event!");
	}

	@Override
	public MenuElement setSize(int x, int y) {
		// TODO Auto-generated method stub
		xsz = x;
		ysz = y;
		return this;
	}

	@Override
	public Pair<Integer, Integer> getSize() {
		// TODO Auto-generated method stub
		return new Pair<>(xsz,ysz);
	}

	@Override
	public MenuElement setPos(int x, int y) {
		// TODO Auto-generated method stub
		this.x=x;
		this.y=y;
		return this;
	}

	@Override
	public Pair<Integer, Integer> getPos() {
		// TODO Auto-generated method stub
		return new Pair<>(x,y);
	}

	@Override
	public MenuElement setFont(Font f) {
		// TODO Auto-generated method stub
		this.f = f;
		return this;
	}

	@EventHandler
	public void onDeselect(DeselectEvent e) throws Exception {
		// TODO Auto-generated method stub
		Global.log(Global.levels.DEBUG,"I got deselected... :(");
		selected = false;
	}

	@Override
	public MenuElement setDrawPosOff(int x, int y) {
		// TODO Auto-generated method stub
		offx=x;
		offy=y;
		return this;
	}

}
