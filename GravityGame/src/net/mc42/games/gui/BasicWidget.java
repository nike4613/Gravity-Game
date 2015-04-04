package net.mc42.games.gui;

import net.mc42.games.events.Event;
import net.mc42.games.fonts.Fonts;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class BasicWidget implements Widget{
	String[] s_px_s0023890 = new String[]{
		"Image i = new Image(\"resources/icon/gameIcon32.png\");",
		"i.setFilter(Image.FILTER_NEAREST);",
		"i.getScaledCopy(szx, szy).draw(x, y);",
		"g.setFont(Fonts.getFont(\"default\"));",
		"w = (szx/(g.getFont().getWidth(\"TeNrOuJiMa\")/\"TeNrOuJiMa\".length()));",
		"g.drawString(s.substring(0, (w>s.length())?s.length():w), x, y);"
	};
	Event lastEvent;
	String s = "";
	int cur = 0;
	int w = 0;
	@Override
	public void draw(int x, int y, int szx, int szy, Graphics g) throws Exception {
		// TODO Auto-generated method stub
		Image i = new Image("resources/icon/gameIcon32.png");
		i.setFilter(Image.FILTER_NEAREST);
		i.getScaledCopy(szx, szy).draw(x, y);
		g.setFont(Fonts.getFont("default"));
		w = (szx/(g.getFont().getWidth("TeNrOuJiMa")/"TeNrOuJiMa".length()));
		g.drawString(s.substring(0, (w>s.length())?s.length():w), x, y);
		//lastEvent = (lastEvent==null)?new Event((GameContainer) Global.mainShare.get(null), EventType.KEYDOWN, 0, 0):lastEvent;
		g.drawString(lastEvent.toString(), x, y+30);
	}

	@Override
	public void update(GameContainer container, int timeinms) throws Exception {
		// TODO Auto-generated method stub
		s = s_px_s0023890[cur++];
		if(cur>=s_px_s0023890.length) cur = 0;
	}

	@Override
	public void processEvents(Event e) {
		// TODO Auto-generated method stub
		lastEvent = e;
	}

}
