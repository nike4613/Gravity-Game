package net.mc42.games.gui;

import net.mc42.games.ImageUtils;
import net.mc42.games.events.Event;
import net.mc42.games.events.EventType;
import net.mc42.global.Pair;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.util.xml.XMLElement;
import org.newdawn.slick.util.xml.XMLParser;

public class GUI {
	private Image[][] ss = new Image[3][3];
	private ChangeProperties[][] propsEdgeScaling = new ChangeProperties[][]{
			{ChangeProperties.NONE,ChangeProperties.SCALE_HOR,ChangeProperties.NONE},
			{ChangeProperties.SCALE_VER,ChangeProperties.SCALE,ChangeProperties.SCALE_VER},
			{ChangeProperties.NONE,ChangeProperties.SCALE_HOR,ChangeProperties.NONE}
	};
	private ChangeProperties[][] propsEdgeTiling = new ChangeProperties[][]{
			{ChangeProperties.NONE,ChangeProperties.TILE_HOR,ChangeProperties.NONE},
			{ChangeProperties.TILE_VER,ChangeProperties.TILE,ChangeProperties.TILE_VER},
			{ChangeProperties.NONE,ChangeProperties.TILE_HOR,ChangeProperties.NONE}
	};
	protected ChangeProperties[][] props = propsEdgeScaling;
	private boolean tiling = false;
	private int sepspace = 0;
	private int margin = 3;
	private Widget widget;
	private int x,y,szx,szy = 0;
	private String name;
	
	/***********************************************************************
	 * Constructs a new GUI object from image and XML file using widget w. *
	 * @param sectfile XML file (can contain only one gui)                 *
	 * @param w The widget to use                                          *
	 * @throws Exception                                                   *
	 ***********************************************************************/
	public GUI(String name, String file, Widget w, int renderOrder) throws Exception{this(name, file,w,renderOrder,true);}
	public GUI(String name, String file, Widget w, int renderOrder, boolean active) throws Exception{
		String imgfile ;//= (new File("/resources/gui/" + name + ".png").isFile())?"/resources/gui/" + name + ".png":"/resources/gui/guis.png";
		String sectfile = "/resources/gui/" + file + ".xml";
		XMLParser xml = new XMLParser();
		XMLElement el = xml.parse(file, getClass().getResourceAsStream(sectfile));
		w.setGUI(this);
		//Image im = new Image(getClass().getResourceAsStream(imgfile),name,false);
		//im.setFilter(Image.FILTER_NEAREST);
		widget = w;
		
		//process xml
		if(el.getName()!="gui") throw new Exception("Sections XML file supposed to be format '<gui><sect></sect>...</gui>'");
		props = (el.getBooleanAttribute("tiling"))?propsEdgeTiling:propsEdgeScaling;
		tiling = el.getBooleanAttribute("tiling");
		margin = el.getIntAttribute("margin");
		imgfile = el.getAttribute("image");
		//Global.log(Global.levels.DEBUG, imgfile);
		Image im = new Image(getClass().getResourceAsStream(imgfile),file,false);
		im.setFilter(Image.FILTER_NEAREST);
		for(int i = 0;i<el.getChildren().size();i++){
			XMLElement e = el.getChildren().get(i);
			if(e.getName()=="sect"){
				ss[e.getIntAttribute("py")][e.getIntAttribute("px")] = 
						im.getSubImage(e.getIntAttribute("x"), e.getIntAttribute("y"), e.getIntAttribute("w"), e.getIntAttribute("h"));
			}
			/*if(e.getName()=="seperation"){
				sepspace = e.getIntAttribute("value");
			}*/
		}
		this.name = name;
		
		GUIs.regGUI(this, name, active, renderOrder);
	}
	
	public String getName(){
		return name;
	}
	
	public void update(GameContainer container, int timeinms) throws Exception{
		widget.update(container, timeinms);
	}
	
	public void processEvents(Event e){
		if(e.getType().equals(EventType.DRAG)||e.getType().equals(EventType.MOUSEMOVE)||
				e.getType().equals(EventType.MOUSEDOWN)||e.getType().equals(EventType.MOUSEUP)){
			e.setPos(new Pair<Integer,Integer>(e.getPos().first-(getPos().first.first+ss[0][0].getWidth()+margin),e.getPos().last-(getPos().first.last+ss[0][0].getHeight()+margin)));
		}
		widget.processEvents(e);
	}
	
	public GUI setPos(int x, int y, int szx, int szy){
		this.x = x;
		this.y = y;
		this.szx = szx;
		this.szy = szy;
		return this;
	}
	
	public Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> getPos(){
		return new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>
			(new Pair<Integer,Integer>(x,y), new Pair<Integer,Integer>(szx,szy));
	}
	
	public void draw(Graphics g) throws Exception{
		draw(x,y,szx,szy,g);
	}
	
	/**
	 * Draws GUI at x,y with size szx,szy.
	 * @param x The x pos
	 * @param y The y pos
	 * @param szx The x size
	 * @param szy The y size 
	 * @throws Exception
	 */
	public void draw(int x, int y, int szx, int szy, Graphics g) throws Exception{
		int inx,iny=0;
		int cx=x;
		int cy=y;
		szx=szx-(ss[0][0].getWidth()*2);
		szy=szy-(ss[0][0].getHeight()*2);
		inx=(int) (x+ss[0][0].getWidth()+margin+sepspace);
		iny=(int) (y+ss[0][0].getHeight()+margin+sepspace);
		//Global.log(Global.levels.DEBUG, "draw");
		Image todraw = null;
		Pair<Integer,Integer> pal = null;
		xloop:
		for(int px = 0;px<3;px++){
			for(int py = 0;py<3;py++){
				todraw = null;
				switch(props[py][px]){
				case NONE:
					todraw = ss[py][px];
					break;
				case SCALE:
					todraw = ss[py][px].getScaledCopy(szx, szy);
					break;
				case SCALE_HOR:
					todraw = ss[py][px].getScaledCopy(szx, ss[py][px].getHeight());
					break;
				case SCALE_VER:
					todraw = ss[py][px].getScaledCopy(ss[py][px].getWidth(), szy);
					break;
				case TILE:{
					
					
					if(px==1&&py==1){
						inx=cx+margin;
						iny=cy+margin;
						//szx=p.first-sepspace*2;
						//szy=p.last-sepspace*2;
					}
					cy+=(pal=ImageUtils.tileImageToApproxSize(ss[py][px], cx, cy, szx, szy)).last+sepspace;
					continue;
				}
				case TILE_VER:{
					cy+=(ImageUtils.tileImageToApproxSize(ss[py][px], cx, cy, 1, szy)).last+sepspace;
					continue;
				}
				case TILE_HOR:{
					Pair<Integer,Integer> p = ImageUtils.tileImageToApproxSize(ss[py][px], cx, cy, szx, 1);
					cy+=p.last+sepspace;
					if(py==2){
						cy=y;
						cx+=p.first+sepspace;
						continue xloop;
					}
					continue;
				}
				default:
					break;
					
				}
				todraw.draw(cx, cy);
				cy+=todraw.getHeight()+sepspace;
			}
			cy=y;
			cx+=todraw.getWidth()+sepspace;
		}
		if(tiling){
			szx=pal.first-margin*2;
			szy=pal.last-margin*2;
		} else {
			szx=szx-margin*2;
			szy=szy-margin*2;
		}
		if(widget!=null)
			widget.draw(inx, iny, szx, szy, g);
	}
	
}
