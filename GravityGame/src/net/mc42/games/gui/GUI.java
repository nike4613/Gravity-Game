package net.mc42.games.gui;

import net.mc42.games.ImageUtils;

import org.newdawn.slick.Image;
import org.newdawn.slick.util.xml.XMLElement;
import org.newdawn.slick.util.xml.XMLParser;

public class GUI {
	private Image[][] ss = new Image[3][3];
	protected ChangeProperties[][] props = new ChangeProperties[][]{
			{ChangeProperties.NONE,ChangeProperties.SCALE_HOR,ChangeProperties.NONE},
			{ChangeProperties.SCALE_VER,ChangeProperties.SCALE,ChangeProperties.SCALE_VER},
			{ChangeProperties.NONE,ChangeProperties.SCALE_HOR,ChangeProperties.NONE}
	};
	private int sepspace = 0;
	
	public GUI(String imgfile,String sectfile) throws Exception{
		XMLParser xml = new XMLParser();
		XMLElement el = xml.parse(sectfile);
		Image im = new Image(imgfile);
		im.setFilter(Image.FILTER_NEAREST);
		
		//process xml
		if(el.getName()!="gui") throw new Exception("Sections XML file supposed to be format '<gui><sect></sect>...</gui>'");
		for(int i = 0;i<el.getChildren().size();i++){
			XMLElement e = el.getChildren().get(i);
			if(e.getName()=="sect"){
				ss[e.getIntAttribute("py")][e.getIntAttribute("px")] = 
						im.getSubImage(e.getIntAttribute("x"), e.getIntAttribute("y"), e.getIntAttribute("w"), e.getIntAttribute("h"));
			}
			if(e.getName()=="seperation"){
				sepspace = e.getIntAttribute("value");
			}
		}
	}
	
	public void setSeperationSpace(int s){
		sepspace = s;
	}
	
	public void draw(int x, int y, int szx, int szy){
		draw(x, y, szx, szy, null);
	}
	
	public void draw(int x, int y, int szx, int szy, Widget w){
		int inx,iny=0;
		int cx=x;
		int cy=y;
		inx=(int) (x+ss[0][0].getWidth());
		iny=(int) (y+ss[0][0].getHeight());
		Image todraw = null;
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
					ImageUtils.tileImageToApproxSize(ss[py][px], inx, iny, szx, szy);
					cy+=szy;
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
		if(w!=null)
			w.draw(inx, iny, szx, szy);
	}
	
}
