package net.mc42.games.gui.menu;

import java.util.ArrayList;

import net.mc42.games.events.Event;
import net.mc42.games.events.EventType;
import net.mc42.games.gui.EventHandler;
import net.mc42.games.gui.Widget;
import net.mc42.global.Global;
import net.mc42.global.Utils;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Menu implements Widget {
	
	private ArrayList<MenuElement> elems = new ArrayList<>();
	private int selEl = -1;
	private String name;
	private int namePos;
	private Font font;
	private Color color;
	
	public Menu(String n){
		name = n;
	}
	
	public Menu addElement(MenuElement e){
		elems.add(e.setFont(font).setPos(0, 0));
		return this;
	}
	public Menu setFont(Font e){
		font = e;
		return this;
	}
	public Menu setFontColor(Color col){
		color = col;
		return this;
	}

	@Override
	public void draw(int x, int y, int szx, int szy, Graphics g)
			throws Exception {
		// TODO Auto-generated method stub
		Font tempFont = g.getFont();
		Color tempCol = g.getColor();
		g.setFont(font);
		g.setColor(color);
		
		namePos = (szx/2)-(g.getFont().getWidth(name)/2)+x;
		int ny = g.getFont().getHeight("AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz")+5;
		g.drawString(name, namePos, y);
		for(int i=0;i<elems.size();i++){
			//MenuElement el = elems.get(i);
			elems.get(i).setDrawPosOff(x, y);
			elems.get(i).setPos((szx/2)-(elems.get(i).getSize().first/2), ny);
			elems.get(i).draw(g);
			ny += 5+elems.get(i).getSize().last;
		}
		
		g.setFont(tempFont);
		g.setColor(tempCol);
	}
	
	public void processEvents(Event e){
		try {
		if(e.getType().equals(EventType.MOUSEMOVE)||e.getType().equals(EventType.DRAG)){
			int x = e.getPos().first;
			int y = e.getPos().last;
			int i=0;
			int selEln = -1;
			for(MenuElement m:elems){
				//Global.log(Global.levels.DEBUG, m.getPos().toString() + "::" + m.getSize().toString() + "//" + e.getPos().toString());
				if(m.getPos().first <= x && m.getPos().first+m.getSize().first >= x &&
						m.getPos().last <= y && m.getPos().last+m.getSize().last >= y){
					selEln = i;
					
					break;
				} else selEln = -1;
				//Global.log(Global.levels.DEBUG, m.getPos().toString() + "::" + m.getSize().toString() + "//" + e.getPos().toString());
				i++;
			}
			
			//Global.log(Global.levels.DEBUG,elems.get(selEl).getClass().getName());
			if(selEln!=selEl){
				if(selEl!=-1)
				Utils.getAnnotatedMethod(
						EventHandler.class, 
						elems.get(selEl).getClass(), 
						"onDeselect"
					).invoke(
						elems.get(selEl),
						new DeselectEvent(e.getGameContainer(), x, y)
					);
				selEl=selEln;
				if(selEl!=-1)
				Utils.getAnnotatedMethod(
						EventHandler.class, 
						elems.get(selEl).getClass(), 
						"onSelect"
					).invoke(
						elems.get(selEl),
						new SelectEvent(e.getGameContainer(), x, y)
					);
				
			}
			if(e.getType().equals(EventType.DRAG)){
				if(selEl==-1)return;
				Utils.getAnnotatedMethod(
						EventHandler.class, 
						elems.get(selEl).getClass(), 
						"onDrag"
					).invoke(
						elems.get(selEl),
						e
					);
			}
		} else if(e.getType().equals(EventType.INIT)){
			for(MenuElement m:elems)
			Utils.getAnnotatedMethod(
					EventHandler.class, 
					m.getClass(), 
					"onInit"
				).invoke(
					m,
					e
				);
		} else {
			if(selEl==-1)return;
			Utils.getAnnotatedMethod(
					EventHandler.class, 
					elems.get(selEl).getClass(), 
					"on" + e.getType().toCamelCaseType()
				).invoke(
					elems.get(selEl),
					e
				);
		}
		} catch(NullPointerException n){
		}catch (Exception e1) {
				// TODO Auto-generated catch block
			Global.log(Global.levels.DEBUG, e1);
		}
	}

	@Override
	public void update(GameContainer c, int timeinms) throws Exception {
		//for(int i=0;i<elems.size();i++){
			if(selEl!=-1)elems.get(selEl).update(c);
		//}
	}

}
