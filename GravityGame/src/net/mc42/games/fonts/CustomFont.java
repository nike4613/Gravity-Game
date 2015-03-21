package net.mc42.games.fonts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.mc42.games.ImageUtils;
import net.mc42.global.ByteString;
import net.mc42.global.Global;
import net.mc42.global.Pair;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class CustomFont {
	
	private SpriteSheet font;
	private Map<Character,Pair<Integer,Integer>> charmap = new HashMap<>();
	private String name;
	
	public CustomFont(String fontfile) throws Exception{
		loadFontFile(fontfile);
	}
	
	private void loadFontFile(String filename) throws Exception{
		ZipInputStream file = new ZipInputStream(/*new FileInputStream*/(getClass().getResourceAsStream(filename)));
		ZipEntry e;
		byte[] rdbuffer = new byte[1024];
		ByteString buffer = new ByteString();
		ByteString image = new ByteString();
		String text = "";
		while((e = file.getNextEntry()) != null){
			buffer = new ByteString();
			Global.log(Global.levels.DEBUG, "Found file " + e.getName() + " in font file " + filename);
			int len = 0;
			while((len = file.read(rdbuffer))>0){
				/*for(byte b:rdbuffer)
					buffer.add(b);*/
				buffer.add(rdbuffer);
				Global.log(Global.levels.DEBUG, "Read " + len + " bytes from file " + filename + "!" + e.getName());
				//Global.log(Global.levels.DEBUG, buffer.toString());
			}
			if(e.getName().endsWith(".png")){
				image = buffer;
			}
			if(e.getName().endsWith(".map")){
				text = buffer.toString();
			}
			//Global.log(Global.levels.DEBUG, text);
		}
		name = filename;
		file.close();
		//Global.log(Global.levels.DEBUG, text);
		loadCharacters(text);
		loadImages(image);
	}
	
	private void loadImages(ByteString image) throws Exception{
		Image i = ImageUtils.getImageFromBytes(image);
		if(i.getWidth()!=i.getHeight()&&(i.getWidth()%16)!=0) 
			throw new FontLoadException("The image file for font " + name + " is incorrectly proportioned!");
		font = new SpriteSheet(i, i.getWidth()/16, i.getHeight()/16);
	}
	
	private void loadCharacters(String text) throws Exception{
		List<String> lines = new ArrayList<>();
		//Global.log(Global.levels.DEBUG, "Text: " + text);
		while(text.indexOf('\n')>0){
			lines.add(text.substring(0, text.indexOf('\n')).trim());
			text = text.substring(text.indexOf('\n')).trim();
		}
		for(int linenum = 0;linenum<lines.size();linenum++){
			if(lines.get(linenum).length()>16)
				throw new FontLoadException("The map file for font " + name + " is incorrectly proportioned!");
			int i = 0;
			for(byte c:lines.get(linenum).getBytes()){
				charmap.put((char) c, new Pair<Integer,Integer>(i, linenum));
				i++;
			}
		}
	}
	
	public void drawStr(Graphics g, String msg, int sz, int x, int y){
		for(byte ch:msg.getBytes()){
			char c = (char) ch;
			
			font.getSprite(charmap.get(c).first, charmap.get(c).last).getScaledCopy(sz, sz).draw(x, y);
			x+=font.getWidth()/font.getHorizontalCount();
		}
	}
	
}
