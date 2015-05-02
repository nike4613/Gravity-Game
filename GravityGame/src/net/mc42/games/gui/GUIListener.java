package net.mc42.games.gui;

import net.mc42.games.events.Event;
import net.mc42.games.events.EventType;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;

public class GUIListener implements InputListener {

	private boolean accepting = true;
	private GameContainer gc;
	
	public GUIListener(Input input, GameContainer c){
		this.gc = c;
	}
	
	public void setAccepting(boolean acc){
		accepting = acc;
	}
	
	@Override
	public void mouseWheelMoved(int change) {
		// TODO Auto-generated method stub
		GUIs.processEvents(new Event(gc, ((change>0)?EventType.MOUSEWHEELUP:EventType.MOUSEWHEELDOWN).setVal(change), 0, 0));
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub
		//GUIs.processEvents(new Event(gc, EventType.CLICK.setVal(button), x, y));
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		// TODO Auto-generated method stub
		GUIs.processEvents(new Event(gc, EventType.MOUSEDOWN.setVal(button), x, y));
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		// TODO Auto-generated method stub
		GUIs.processEvents(new Event(gc, EventType.MOUSEUP.setVal(button), x, y));
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		GUIs.processEvents(new Event(gc, EventType.MOUSEMOVE, newx, newy));
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		GUIs.processEvents(new Event(gc,EventType.DRAG,newx,newy));
	}

	@Override
	public void setInput(Input input) {
	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return this.accepting;
	}

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int key, char c) {
		// TODO Auto-generated method stub
		GUIs.processEvents(new Event(gc, EventType.KEYDOWN.setVal(key), 0,0));
	}

	@Override
	public void keyReleased(int key, char c) {
		// TODO Auto-generated method stub
		GUIs.processEvents(new Event(gc, EventType.KEYUP.setVal(key), 0,0));
	}

	@Override
	public void controllerLeftPressed(int controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerLeftReleased(int controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerRightPressed(int controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerRightReleased(int controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerUpPressed(int controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerUpReleased(int controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerDownPressed(int controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerDownReleased(int controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerButtonPressed(int controller, int button) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerButtonReleased(int controller, int button) {
		// TODO Auto-generated method stub

	}

}
