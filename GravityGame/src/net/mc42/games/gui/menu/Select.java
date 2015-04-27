package net.mc42.games.gui.menu;

import net.mc42.games.events.Event;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.util.xml.XMLElement;
import org.newdawn.slick.util.xml.XMLParser;

public class Select extends MenuElement {

	private boolean open = false;
	
	public Select(String name, String... options) throws Exception{
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
			
			
		}
		
	}
	
	@Override
	public void draw(Graphics g) throws Exception {
		// TODO Auto-generated method stub

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
		
	}

}
