package net.mc42.games.gui.menu;

import java.lang.reflect.Method;

import net.mc42.games.ImageUtils;
import net.mc42.games.events.Event;
import net.mc42.games.gui.EventHandler;
import net.mc42.global.Global;
import net.mc42.global.Pair;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.util.xml.XMLElement;
import org.newdawn.slick.util.xml.XMLParser;

public class Button implements MenuElement {
	private int xsz=0;
	private int ysz=0;
	private int x=0;
	private int y=0;
	private int offx=0;
	private int offy=0;
	private boolean selected = false;
	private int mouseDown = 0x0;
	private String text;
	private Image[] im_nonsel = new Image[3];
	private Image[] im_sel = new Image[3];
	private Image[] im_click = new Image[3];
	private Font f;
	private Method clickAct = null;
	private int clickMode ;//= 0b00001100;
	private int clickLen ;//= 500;//ms
	private long f1_ct = 0;
	public static final int MODE_MOUSE_DOWN 	= 0b000000001;
	public static final int MODE_MOUSE_UP 		= 0b000000010;
	public static final int MODE_MOUSE_CLICK 	= 0b000000100;
	public static final int MODE_MOUSE_1 		= 0b000001000;
	public static final int MODE_MOUSE_2 		= 0b000010000;
	public static final int MODE_MOUSE_3 		= 0b000100000;
	public static final int MODE_MOUSE_4 		= 0b001000000;
	public static final int MODE_MOUSE_5 		= 0b010000000;
	private Menu parent;
	
	public Button(String name, String text) throws Exception{
		setClickModes(MODE_MOUSE_CLICK,MODE_MOUSE_1,MODE_MOUSE_2,MODE_MOUSE_3,MODE_MOUSE_4,MODE_MOUSE_5);
		setClickLength(500);
		//Global.log(Global.levels.DEBUG, "" + String.format("%8s", Integer.toBinaryString(clickMode)).replace(' ', '0') + " - " + getModeSet(MODE_MOUSE_DOWN));
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

	private boolean getModeSet(int mode){
		return ((clickMode&mode)==mode)?true:false;
	}
	
	private boolean getMouseDown(){
		for(int i=0;i<5;i++){
			if(getMouseButtonDown(i))
				return true;
		}
		return false;
	}
	
	private boolean getMouseButtonDown(int mouse){
		return ((mouseDown&(1<<(mouse+1)))==(1<<(mouse+1)))?true:false;
	}
	
	//public Button(){}
	
	public Button setClickAction(Method m){
		clickAct = m;
		return this;
	}
	
	public Button setClickModes(int... modes){
		return setClickModes(true,modes);
	}
	
	public Button unsetClickMode(int mode){
		clickMode &= ~mode;
		return this;
	}
	
	public Button setClickMode(int mode){
		clickMode |= mode;
		return this;
	}
	
	public Button setClickModes(boolean override,int... modes){
		int total = (override)?0:clickMode;
		for(int m:modes){
			total |= m;
		}
		clickMode = total;
		return this;
	}
	
	public Button setClickLength(int len){
		clickLen = len;
		return this;
	}
	
	private void clickEx() throws Exception{
		//clickAct.invoke(null, parent.getGUI(), this);
		new ClickThread(clickAct,parent.getGUI(), this).start();
	}
	
	public void draw(Graphics g)
			throws Exception {
		// TODO Auto-generated method stub
		Font fonT = g.getFont();
		g.setFont(f);
		//g.setColor(((mouseDown&0x1)==1)?Color.green:Color.yellow);
		//g.setColor((selected)?g.getColor():Color.red);
		Image[] i= new Image[3];
		i = ((mouseDown)!=0)?im_click:im_sel;
		i = (selected)?i:im_nonsel;
		//g.drawRect(x+offx, y+offy, xsz, ysz);
		
		int x=this.x+offx,y=this.y+offy;
		int nsx=xsz,nsy=ysz;
		
		int h=0;
		h=((h=(g.getFont().getHeight(text)/i[0].getHeight()))>1)?h:1;
		
		i[0]=i[0].getScaledCopy(h);i[1]=i[1].getScaledCopy(h);i[2]=i[2].getScaledCopy(h);
		
		i[0].draw(x, y);
		Pair<Integer,Integer> w=ImageUtils.tileImageToApproxSize(i[1], x+i[0].getWidth(), y, g.getFont().getWidth(text), 1);
		i[2].draw(x+w.first+i[0].getWidth(), y);
		int texh = (w.last/2)-(g.getFont().getHeight(text)/2);
		int texw = (w.first/2)-(g.getFont().getWidth(text)/2);
		g.drawString(text, x+i[0].getWidth()+texw, y+texh);
		
		nsx = i[2].getWidth()+w.first+i[0].getWidth();
		nsy = (i[2].getHeight()+w.last+i[0].getHeight())/3;
		
		setSize(nsx, nsy);
		//setPos();
		
		g.setFont(fonT);
	}
	
	public void update(GameContainer gc, int ms){
		/*for(int i=0;i<6;i++){
			if(!gc.getInput().isMouseButtonDown(i))mouseDown &= ~(1<<(i+1));
			else mouseDown |= 1<<(i+1);
		}*/
		
		if(f1_ct>0){
		/*if(getModeSet(MODE_MOUSE_1)&&getMouseButtonDown(1))f1_ct+=ms;
		else if(getModeSet(MODE_MOUSE_2)&&getMouseButtonDown(2))f1_ct+=ms;
		else if(getModeSet(MODE_MOUSE_3)&&getMouseButtonDown(3))f1_ct+=ms;
		else if(getModeSet(MODE_MOUSE_4)&&getMouseButtonDown(4))f1_ct+=ms;
		else if(getModeSet(MODE_MOUSE_5)&&getMouseButtonDown(5))f1_ct+=ms;
		else f1_ct=0;*/
			//Global.log(Global.levels.DEBUG, ""+f1_ct+" " + getMouseDown());
			if(getMouseDown())f1_ct+=ms;
		}
		
		try{
		/*if(getModeSet(MODE_MOUSE_1)&&!getMouseButtonDown(1)&&f1_ct<=clickLen&&f1_ct!=0&&getModeSet(MODE_MOUSE_CLICK)){if(clickAct!=null)clickAct.invoke(null);f1_ct=0;}
		else if(getModeSet(MODE_MOUSE_2)&&!getMouseButtonDown(2)&&f1_ct<=clickLen&&f1_ct!=0&&getModeSet(MODE_MOUSE_CLICK)){if(clickAct!=null)clickAct.invoke(null);f1_ct=0;}
		else if(getModeSet(MODE_MOUSE_3)&&!getMouseButtonDown(3)&&f1_ct<=clickLen&&f1_ct!=0&&getModeSet(MODE_MOUSE_CLICK)){if(clickAct!=null)clickAct.invoke(null);f1_ct=0;}
		else if(getModeSet(MODE_MOUSE_4)&&!getMouseButtonDown(4)&&f1_ct<=clickLen&&f1_ct!=0&&getModeSet(MODE_MOUSE_CLICK)){if(clickAct!=null)clickAct.invoke(null);f1_ct=0;}
		else if(getModeSet(MODE_MOUSE_5)&&!getMouseButtonDown(5)&&f1_ct<=clickLen&&f1_ct!=0&&getModeSet(MODE_MOUSE_CLICK)){if(clickAct!=null)clickAct.invoke(null);f1_ct=0;}*/
		if(!getMouseDown()&&f1_ct<=clickLen&&f1_ct!=0&&getModeSet(MODE_MOUSE_CLICK)){if(clickAct!=null)this.clickEx();f1_ct=0;}
		}catch(Exception e){Global.log(Global.levels.WARNING, "Exeption while trying to execute click function", e);}
		//Global.log(Global.levels.DEBUG, "mouseDown=" + mouseDown);
		//Global.log(Global.levels.DEBUG, "\""+this.text+"\" count=" + f1_ct);
		if(f1_ct>clickLen)f1_ct=0;
	}

	@EventHandler
	public void onSelect(SelectEvent e) throws Exception {
		// TODO Auto-generated method stub
		//Global.log(Global.levels.DEBUG,"I got selected!!!");
		selected = true;
	}

	@EventHandler
	public void onMouseup(Event e){
		//if(e.getType().getVal()==0)Global.log(Global.levels.DEBUG,"Yae! I got clicked!");
		mouseDown &= ~(1<<(e.getType().getVal()+1));
		try{if(getModeSet(MODE_MOUSE_UP)&&clickAct!=null)this.clickEx();}catch(Exception ea){Global.log(Global.levels.WARNING, "Exeption while trying to execute click function", ea);}
	}
	
	@EventHandler
	public void onMousedown(Event e){
		//if(e.getType().getVal()==0)Global.log(Global.levels.DEBUG,"Yae! I got clicked!");
		mouseDown |= 1<<(e.getType().getVal()+1);
		if(f1_ct==0&&getModeSet(MODE_MOUSE_CLICK))f1_ct=1;
		try{if(getModeSet(MODE_MOUSE_DOWN)&&clickAct!=null)this.clickEx();}catch(Exception ea){Global.log(Global.levels.WARNING, "Exeption while trying to execute click function", ea);}
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
		//Global.log(Global.levels.DEBUG,"I got deselected... :(");
		selected = false;
	}
	
	public boolean isSelected(){
		return selected;
	}

	@Override
	public MenuElement setDrawPosOff(int x, int y) {
		// TODO Auto-generated method stub
		offx=x;
		offy=y;
		return this;
	}

	@Override
	public MenuElement setParent(Menu m) {
		// TODO Auto-generated method stub]
		parent = m;
		return this;
	}
	private class ClickThread extends Thread{
		Method m;
		Object[] args;
		protected ClickThread(Method m, Object... args){
			this.m=m;
			this.args = args;
			setName("click");
		}
		public void run(){
			try {
				m.invoke(null, args);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Global.log(Global.levels.WARNING, "Error in button click", e);
			}
		}
	}
}
