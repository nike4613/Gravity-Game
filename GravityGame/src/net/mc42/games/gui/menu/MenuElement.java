package net.mc42.games.gui.menu;

import net.mc42.games.events.Event;
import net.mc42.games.gui.EventHandler;
import net.mc42.global.Pair;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class MenuElement {
	protected int x,y,szx,szy,ofx,ofy;
	protected Font font;
	protected boolean selected = false;
	protected Menu parent;
	
	public void drawI(Graphics g)
			throws Exception{
		Font f = g.getFont();
		g.setFont(font);
		draw(g);
		g.setFont(f);
	}
	public abstract void draw(Graphics g) throws Exception;
	public MenuElement setSize(int x, int y) {
		szx=x;szy=y;return this;
	}
	public MenuElement setPos(int x, int y) {
		this.x=x;this.y=y;return this;
	}
	public MenuElement setDrawPosOff(int x, int y) {
		ofx=x;ofy=y;return this;
	}
	public abstract void update(GameContainer gc, int ms);
	public Pair<Integer,Integer> getSize() {
		return new Pair<>(szx,szy);
	}
	public Pair<Integer,Integer> getPos() {
		return new Pair<>(x,y);
	}
	public MenuElement setFont(Font f) {
		font=f;return this;
	}
	@EventHandler
	public abstract void onInit(Event e) throws Exception;
	@EventHandler
	public void onSelectI(SelectEvent e) throws Exception{
		selected=true;
		onSelect(e);
	}
	public abstract void onSelect(SelectEvent e) throws Exception;
	@EventHandler
	public void onDeselectI(DeselectEvent e) throws Exception{
		selected=false;
		onDeselect(e);
	}
	public abstract void onDeselect(DeselectEvent e) throws Exception;
	public boolean isSelected(){
		return selected;
	}
	public MenuElement setParent(Menu m) {
		parent=m;return this;
	}
}
