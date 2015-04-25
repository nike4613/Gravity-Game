package net.mc42.games.gui.menu;

import net.mc42.games.events.Event;
import net.mc42.global.Pair;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Select implements MenuElement {

	private boolean selected = false;
	private boolean open = false;
	private int szx,szy;
	private int x,y;
	private int ofx,ofy;
	private Menu parent;
	
	@Override
	public void draw(Graphics g) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public MenuElement setSize(int x, int y) {
		// TODO Auto-generated method stub
		szx=x;szy=y;
		return this;
	}

	@Override
	public MenuElement setPos(int x, int y) {
		// TODO Auto-generated method stub
		this.x=x;this.y=y;
		return this;
	}

	@Override
	public MenuElement setDrawPosOff(int x, int y) {
		// TODO Auto-generated method stub
		ofx=x;ofy=y;
		return this;
	}

	@Override
	public void update(GameContainer gc, int ms) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pair<Integer, Integer> getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pair<Integer, Integer> getPos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuElement setFont(Font f) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void onInit(Event e) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSelect(SelectEvent e) throws Exception {
		// TODO Auto-generated method stub
		selected = true;

	}

	@Override
	public void onDeselect(DeselectEvent e) throws Exception {
		// TODO Auto-generated method stub
		selected = false;
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return selected;
	}

	@Override
	public MenuElement setParent(Menu m) {
		// TODO Auto-generated method stub
		parent = m;
		return this;
	}

}
