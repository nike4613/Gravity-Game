package net.mc42.games.gui;

import net.mc42.games.fonts.Fonts;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class testwiget implements Widget{

	@Override
	public void draw(int x, int y, int szx, int szy, Graphics g) throws Exception {
		// TODO Auto-generated method stub
		Image i = new Image("resources/icon/gameIcon32.png");
		i.setFilter(Image.FILTER_NEAREST);
		i.getScaledCopy(szx, szy).draw(x, y);
		g.setFont(Fonts.getFont("default"));
		g.drawString("HOLLY CROOD SIECLEZ!", x, y);
	}

	@Override
	public void update(GameContainer container, int timeinms) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
