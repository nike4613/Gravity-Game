package net.mc42.games;

import org.lwjgl.LWJGLException;

import de.matthiasmann.twl.input.Input;
import de.matthiasmann.twl.renderer.Renderer;
import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;

public class SlickRenderer extends LWJGLRenderer implements Renderer {

	public SlickRenderer() throws LWJGLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Input getInput(){
		return new SlickInput();
	}

}
