package net.mc42.games.GravityGame.Widgets;

import org.newdawn.slick.util.Log;

import de.matthiasmann.twl.Event;
import de.matthiasmann.twl.FPSCounter;
import de.matthiasmann.twl.ToggleButton;
import de.matthiasmann.twl.Widget;

public class TWLTestWidget extends Widget {

	private ToggleButton btnPause;
	private ToggleButton btnArmageddon;
	private FPSCounter fpsCounter;

	public TWLTestWidget() {
		
        btnPause = new ToggleButton();
        btnPause.setTheme("pause");
        add(btnPause);

        btnArmageddon = new ToggleButton();
        btnArmageddon.setTheme("armageddon");
        add(btnArmageddon);

        fpsCounter = new FPSCounter();
        add(fpsCounter);
    }
	
	 protected void layout() {
		 	int x = 10;
		 	int y = 40;
	        

		 	x = getInnerWidth() - 10;
		 	y = 10;
		 	
		 	btnPause.adjustSize();
		 	x -= btnPause.getWidth() + 5;
		 	btnPause.setPosition(x, y);
		 	
		 	btnArmageddon.adjustSize();
		 	x -= btnArmageddon.getWidth() + 5;
		 	btnArmageddon.setPosition(x, y);
		 	
		 	fpsCounter.adjustSize();
		 	fpsCounter.setPosition(
		 			getInnerWidth() - fpsCounter.getWidth(),
		 			getInnerHeight() - fpsCounter.getHeight());
	 } 
	
	 @Override
	 protected boolean handleEvent(Event evt) {
		 if(super.handleEvent(evt)) {
			 return true;
		 }
		 Log.debug("handleEvent");
		 switch (evt.getType()) {
		 	case KEY_PRESSED:
		 		switch (evt.getKeyCode()) {
		 			case Event.KEY_ESCAPE:
					return true;
		 		}
		 		break;
			case MOUSE_BTNDOWN:
				if(evt.getMouseButton() == Event.MOUSE_RBUTTON) {
					return true;//createRadialMenu().openPopup(evt);
				}
				break;
		default:
			break;
		}
		return evt.isMouseEventNoWheel();
	}
	 
}
