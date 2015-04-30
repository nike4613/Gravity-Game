package net.mc42.games.gui.menu;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import net.mc42.games.ImageUtils;
import net.mc42.games.events.Event;
import net.mc42.games.gui.EventHandler;
import net.mc42.global.Global;
import net.mc42.global.Pair;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.util.xml.XMLElement;
import org.newdawn.slick.util.xml.XMLParser;

public class Select extends MenuElement {

	private boolean open = false;
	
	private Image[] im_closed = new Image[3];
	private Image[] im_hovered = new Image[3];
	private Image[] im_extension = new Image[3];
	private Image[] im_open = new Image[3];
	private Image[] im_clicked = new Image[3];
	private Image[] im_extension_sel = new Image[3];
	
	private int current = 0;
	private ArrayList<Pair<String,Object>> options;
	
	private Method clickAct;

	private int mouseDown = 0x0;	
	
	private int texwidth = -1;
	
	private int selEl = -1;

	/**
	 * Constructs the select element.
	 * @param name The name of the xml file to load the image from.
	 * @param options The options this select provides. The last element is the object to pass to the function. NOTE: The first set is the default!
	 * @throws Exception
	 */
	
	@SafeVarargs
	public Select(String name, Pair<String,Object>... opts) throws Exception{
		//Global.log(Global.levels.DEBUG, "" + String.format("%8s", Integer.toBinaryString(clickMode)).replace(' ', '0') + " - " + getModeSet(MODE_MOUSE_DOWN));
		String imgfile ;//= (new File("/resources/gui/" + name + ".png").isFile())?"/resources/gui/" + name + ".png":"/resources/gui/guis.png";
		String sectfile = "/resources/gui/selects/" + name + ".xml";
		XMLParser xml = new XMLParser();
		XMLElement el = xml.parse(name, getClass().getResourceAsStream(sectfile));
		
		//process xml
		if(el.getName()!="select") throw new Exception("Sections XML file supposed to be format '<select>...</select>'");
		imgfile = el.getAttribute("image");
		//Global.log(Global.levels.DEBUG, imgfile);
		Image img = new Image(getClass().getResourceAsStream(imgfile),name,false);
		img.setFilter(Image.FILTER_NEAREST);
		
		for(int i = 0;i<el.getChildren().size();i++){
			XMLElement e = el.getChildren().get(i);
			
			if(e.getName()=="closed"){
				for(int ik = 0;ik<e.getChildren().size();ik++){
					XMLElement ej = e.getChildren().get(ik);
					im_closed[Integer.parseInt(ej.getAttribute("por").replace('l', '0').replace('m', '1').replace('r', '2'))] = 
						img.getSubImage(ej.getIntAttribute("x"), ej.getIntAttribute("y"), ej.getIntAttribute("width"), ej.getIntAttribute("height"));
				}
			}
			if(e.getName()=="open"){
				for(int ik = 0;ik<e.getChildren().size();ik++){
					XMLElement ej = e.getChildren().get(ik);
					im_open[Integer.parseInt(ej.getAttribute("por").replace('l', '0').replace('m', '1').replace('r', '2'))] = 
						img.getSubImage(ej.getIntAttribute("x"), ej.getIntAttribute("y"), ej.getIntAttribute("width"), ej.getIntAttribute("height"));
				}
			}
			if(e.getName()=="hovered"){
				for(int ik = 0;ik<e.getChildren().size();ik++){
					XMLElement ej = e.getChildren().get(ik);
					im_hovered[Integer.parseInt(ej.getAttribute("por").replace('l', '0').replace('m', '1').replace('r', '2'))] = 
						img.getSubImage(ej.getIntAttribute("x"), ej.getIntAttribute("y"), ej.getIntAttribute("width"), ej.getIntAttribute("height"));
				}
			}
			if(e.getName()=="extension"){
				for(int ik = 0;ik<e.getChildren().size();ik++){
					XMLElement ej = e.getChildren().get(ik);
					im_extension[Integer.parseInt(ej.getAttribute("por").replace('l', '0').replace('m', '1').replace('r', '2'))] = 
						img.getSubImage(ej.getIntAttribute("x"), ej.getIntAttribute("y"), ej.getIntAttribute("width"), ej.getIntAttribute("height"));
				}
			}
			if(e.getName()=="clicked"){
				for(int ik = 0;ik<e.getChildren().size();ik++){
					XMLElement ej = e.getChildren().get(ik);
					im_clicked[Integer.parseInt(ej.getAttribute("por").replace('l', '0').replace('m', '1').replace('r', '2'))] = 
						img.getSubImage(ej.getIntAttribute("x"), ej.getIntAttribute("y"), ej.getIntAttribute("width"), ej.getIntAttribute("height"));
				}
			}
			if(e.getName()=="extension-selected"){
				for(int ik = 0;ik<e.getChildren().size();ik++){
					XMLElement ej = e.getChildren().get(ik);
					im_extension_sel[Integer.parseInt(ej.getAttribute("por").replace('l', '0').replace('m', '1').replace('r', '2'))] = 
						img.getSubImage(ej.getIntAttribute("x"), ej.getIntAttribute("y"), ej.getIntAttribute("width"), ej.getIntAttribute("height"));
				}
			}
		}
		
		options = new ArrayList<Pair<String,Object>>(Arrays.asList(opts));
		
	}
	
	/**
	 * Sets the click action
	 * @param m The method to activate on a selection
	 * @return this
	 */
	public Select setAction(Method m){
		clickAct = m;
		return this;
	}
	
	@Override
	public void draw(Graphics g) throws Exception {
		// TODO Auto-generated method stub
		Image[] i= new Image[3];
		
		i = (selected)?im_hovered:im_closed;
		i = (getMouseDown())?im_clicked:i;
		i = (open)?im_open:i;
		
		if(texwidth<0){
			for(Pair<String,Object> p:options){
				if(g.getFont().getWidth(p.first)>texwidth)texwidth=g.getFont().getWidth(p.first);
			}
		}
		
		int x=this.x+ofx,y=this.y+ofy;
		int nsx=szx,nsy=szy;
		
		String text = options.get(current).first;
		
		int h=0;
		h=((h=(g.getFont().getHeight(text)/i[0].getHeight()))>1)?h:1;
		
		i[0]=i[0].getScaledCopy(h);i[1]=i[1].getScaledCopy(h);i[2]=i[2].getScaledCopy(h);
		
		i[0].draw(x, y);
		Pair<Integer,Integer> w=ImageUtils.tileImageToApproxSize(i[1], x+i[0].getWidth(), y, texwidth, 1);
		i[2].draw(x+w.first+i[0].getWidth(), y);
		int texh = (w.last/2)-(g.getFont().getHeight(text)/2);
		int texw = (w.first/2)-(texwidth/2);
		g.drawString(text, x+i[0].getWidth()+texw, y+texh);
		
		nsx = i[2].getWidth()+w.first+i[0].getWidth();
		nsy = (i[2].getHeight()+w.last+i[0].getHeight())/3;
		
		if(open){
			@SuppressWarnings("unchecked")
			ArrayList<Pair<String,Object>> a = (ArrayList<Pair<String,Object>>) options.clone();
			a.remove(current);
			
			y = y+(i[2].getHeight()+w.last+i[0].getHeight())/3;
			
			for (int idx=0;idx<a.size();idx++){
				//Global.log(Global.levels.DEBUG, idx+"="+selEl);
				i = (selEl>=0&&idx==selEl)?im_extension_sel:im_extension;
				text = a.get(idx).first;
				
				h=((h=(g.getFont().getHeight(text)/i[0].getHeight()))>1)?h:1;
				
				i[0]=i[0].getScaledCopy(h);i[1]=i[1].getScaledCopy(h);i[2]=i[2].getScaledCopy(h);
				
				i[0].draw(x, y);
				w=ImageUtils.tileImageToApproxSize(i[1], x+i[0].getWidth(), y, texwidth, 1);
				i[2].draw(x+w.first+i[0].getWidth(), y);
				texh = (w.last/2)-(g.getFont().getHeight(text)/2);
				texw = (w.first/2)-(texwidth/2);
				g.drawString(text, x+i[0].getWidth()+texw, y+texh);
				y = y+(i[2].getHeight()+w.last+i[0].getHeight())/3;
			}
		
			nsy = im_extension[0].getHeight()*options.size();//(i[2].getHeight()+w.last+i[0].getHeight())/3;
		}
		
		setSize(nsx, nsy);
	}

	@Override
	public void update(GameContainer gc, int ms) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onInit(Event e) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSelect(SelectEvent e) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeselect(DeselectEvent e) throws Exception {
		// TODO Auto-generated method stub
		open = false;
	}
	
	@EventHandler
	public void onMouseup(Event e){
		//Global.log(Global.levels.DEBUG, e.toString());
		mouseDown &= ~(1<<(e.getType().getVal()+1));
	}

	@EventHandler
	public void onMousedown(Event e) throws Exception{
		//Global.log(Global.levels.DEBUG, e.toString());
		mouseDown |= 1<<(e.getType().getVal()+1);
		if(!open)open=true;
		else if(open){
			@SuppressWarnings("unchecked")
			ArrayList<Pair<String,Object>> a = (ArrayList<Pair<String,Object>>) options.clone();
			a.remove(current);
			current = options.indexOf(a.get(selEl));
			
			clickEx(options.get(current).last);
			
			open=false;
		}
	}
	
	@EventHandler
	public void onMousemove(Event e){
		//Global.log(Global.levels.DEBUG, e.toString());
		if(open){
			int idx = e.getPos().last/im_extension[0].getHeight();
			selEl = idx-1;
			//Global.log(Global.levels.DEBUG, "" + selEl);
		} else {
			selEl = -1;
		}
	}
	
	private boolean getMouseDown(){
		return (mouseDown!=0);
	}
	
	private void clickEx(Object obj) throws Exception{
		//clickAct.invoke(null, parent.getGUI(), this);
		new ActionThread(clickAct,parent.getGUI(), this, obj).start();
	}
	
	private boolean getMouseButtonDown(int mouse){
		return ((mouseDown&(1<<(mouse+1)))==(1<<(mouse+1)))?true:false;
	}
	
	private class ActionThread extends Thread{
		Method m;
		Object[] args;
		protected ActionThread(Method m, Object... args){
			this.m=m;
			this.args = args;
			setName("action");
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
