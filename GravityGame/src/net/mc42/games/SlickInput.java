package net.mc42.games;

import java.util.ArrayList;

import net.mc42.games.events.Event;
import net.mc42.games.events.EventType;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import de.matthiasmann.twl.GUI;

public class SlickInput implements de.matthiasmann.twl.input.Input {
	
	private Input input;
	private boolean wasActive;
	private static ArrayList<Event> incomingEvents = new ArrayList<>();
	private boolean[] k_up;// = new boolean[];
	private boolean[] k_down;// = false;
	private boolean[] k_right;// = false;
	private boolean[] k_left;// = false;
	private boolean[] k_a;// = false;
	
	public static void addIncomingEvent(Event e){
		incomingEvents.add(e);
	}
	
	public SlickInput(Input in){
		input = in;
		init();
	}
	
	private void init(){
		k_up = new boolean[input.getControllerCount()];
		k_down = new boolean[input.getControllerCount()];
		k_left = new boolean[input.getControllerCount()];
		k_right = new boolean[input.getControllerCount()];
		k_a = new boolean[input.getControllerCount()];
	}
	
	public SlickInput(){
		input = ((GameContainer)API.GC.get()).getInput();
		init();
	}
	
	@Override
	public boolean pollInput(GUI gui) {
		boolean active = Display.isActive();
        if(wasActive && !active) {
            wasActive = false;
            return false;
        }
        wasActive = active;
        
        /*if(Keyboard.isCreated()) {
            while(Keyboard.next()) {
                gui.handleKey(
                        Keyboard.getEventKey(),
                        Keyboard.getEventCharacter(),
                        Keyboard.getEventKeyState());
            }
        }
        if(Mouse.isCreated()) {
            while(Mouse.next()) {
                gui.handleMouse(
                        Mouse.getEventX(), gui.getHeight() - Mouse.getEventY() - 1,
                        Mouse.getEventButton(), Mouse.getEventButtonState());

                int wheelDelta = Mouse.getEventDWheel();
                if(wheelDelta != 0) {
                    gui.handleMouseWheel(wheelDelta / 120);
                }
            }
        }*/
        for(int i=0;i<input.getControllerCount();i++){
        	if(input.getAxisCount(i)<5) continue;
        	if((input.isControllerDown(i) || input.getAxisValue(i, 0)>0 || input.getAxisValue(i, 2)>0)&&!k_down[i]) {
        		addIncomingEvent(new Event(EventType.KEY_DOWN.setData(Input.KEY_DOWN)));
        		k_down[i]=true;
        	}
        	if((input.isControllerUp(i) || input.getAxisValue(i, 0)<0 || input.getAxisValue(i, 2)<0)&&!k_up[i]){ 
        		addIncomingEvent(new Event(EventType.KEY_DOWN.setData(Input.KEY_UP)));
        		k_up[i]=true;
        	}
        	if((input.isControllerLeft(i) || input.getAxisValue(i, 1)<0 || input.getAxisValue(i, 3)<0)&&!k_left[i]) {
        		addIncomingEvent(new Event(EventType.KEY_DOWN.setData(Input.KEY_LEFT)));
        		k_left[i]=true;
        	}
        	if((input.isControllerRight(i) || input.getAxisValue(i, 1)>0 || input.getAxisValue(i, 3)>0)&&!k_right[i]) {
        		addIncomingEvent(new Event(EventType.KEY_DOWN.setData(Input.KEY_RIGHT)));
        		k_right[i]=true;
        	}
        	
        	if(!input.isControllerDown(i) && input.getAxisValue(i, 0)==0 && input.getAxisValue(i, 2)==0 && k_down[i]){
        		addIncomingEvent(new Event(EventType.KEY_UP.setData(Input.KEY_DOWN)));
        		k_down[i] = false;
        	}
        	if(!input.isControllerUp(i) && input.getAxisValue(i, 0)==0 && input.getAxisValue(i, 2)==0 && k_up[i]){
        		addIncomingEvent(new Event(EventType.KEY_UP.setData(Input.KEY_UP)));
        		k_up[i] = false;
        	}
        	if(!input.isControllerLeft(i) && input.getAxisValue(i, 1)==0 && input.getAxisValue(i, 3)==0 && k_left[i]){
        		addIncomingEvent(new Event(EventType.KEY_UP.setData(Input.KEY_LEFT)));
        		k_left[i] = false;
        	}
        	if(!input.isControllerRight(i) && input.getAxisValue(i, 1)==0 && input.getAxisValue(i, 3)==0 && k_right[i]){
        		addIncomingEvent(new Event(EventType.KEY_UP.setData(Input.KEY_RIGHT)));
        		k_right[i] = false;
        	}
        	
        	//Button Press
        	
        	if(input.isButtonPressed(0, i) && !k_a[i]){
        		addIncomingEvent(new Event(EventType.KEY_DOWN.setData(Input.KEY_ENTER)));
        		k_a[i] = true;
        	}
        	if(!input.isButtonPressed(0, i) && k_a[i]){
        		addIncomingEvent(new Event(EventType.KEY_UP.setData(Input.KEY_ENTER)));
        		k_a[i] = false;
        	}
        }
        
        for(Event e:incomingEvents){
        	if(e.getType().isKeyboard()){
        		gui.handleKey((e.getType().getData()), (char) e.getData(), e.getType().equals(EventType.KEY_DOWN));
        	} else
        	if(e.getType().isMouse()&&!e.getType().equals(EventType.MOUSE_WHEEL)){
        		gui.handleMouse(e.getPos().first,e.getPos().last, e.getType().getData(),e.getType().equals(EventType.MOUSE_DOWN));
        	} else
        	if(e.getType().equals(EventType.MOUSE_WHEEL)){
        		gui.handleMouseWheel(e.getType().getData());
        	}
        	//incomingEvents.remove(e);
        }
        
        incomingEvents.clear();
        
        
        return true;
        
	}
}