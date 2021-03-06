package net.mc42.games.world;

import java.util.ArrayList;

import org.json.JSONObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class TileType {
	Image im;
	ArrayList<Image> borders;
	
	Image imBord;
	Image imCorn;
	
	int textureX;
	int textureY;
	int textureW;
	int textureH;
	
	public static int SIDE_TOP = 0b10000000;
	public static int SIDE_BOTTOM = 0b01000000;
	public static int SIDE_LEFT = 0b00100000;
	public static int SIDE_RIGHT = 0b00010000;
	
	public static int CORNER_TL = 0b00001000;
	public static int CORNER_TR = 0b00000100;
	public static int CORNER_BL = 0b00000010;
	public static int CORNER_BR = 0b00000001;
	
	public TileType(JSONObject json, Image imageDataFile) throws Exception{
		JSONObject j = json;
		
		borders = new ArrayList<Image>();
		
		im = imageDataFile.copy();
		textureX = j.getJSONObject("texture").getInt("x");
		textureY = j.getJSONObject("texture").getInt("y");
		textureW = j.getJSONObject("texture").getInt("width");
		textureH = j.getJSONObject("texture").getInt("height");
		
		JSONObject bord = j.getJSONObject("border");
		JSONObject bordo = j.getJSONObject("corner");
		//Global.setDepth(10);
		if(bord!=null){
			Image imif;
			Image imifd;
			imif = im.getSubImage(bord.getInt("x"),bord.getInt("y"),bord.getInt("width"),bord.getInt("height"));
			imifd = im.getSubImage(bordo.getInt("x"),bordo.getInt("y"),bordo.getInt("width"),bordo.getInt("height"));
			imBord = new Image(textureW,textureH);
			imCorn = new Image(textureW,textureH);
			imBord.getGraphics().drawImage(imif, 0, 0);
			imBord.setCenterOfRotation(textureW/2, textureH/2);
			imBord.setFilter(Image.FILTER_NEAREST);
			imCorn.getGraphics().drawImage(imifd, 0, 0);
			imCorn.setCenterOfRotation(textureW/2, textureH/2);
			imCorn.setFilter(Image.FILTER_NEAREST);
			for(int i=0;i<256;i++){borders.add(new Image(0,0));}
			/*for(int i=0;i<=255;i++){
				Image borde = new Image(textureW,textureH);
				Graphics g = borde.getGraphics();
				if((i&SIDE_TOP)>0){
					imi.setRotation(0);
					g.drawImage(imi, 0, 0);
				}
				if((i&SIDE_LEFT)>0){
					imi.setRotation(270);
					g.drawImage(imi, 0, 0);
				}
				if((i&SIDE_RIGHT)>0){
					imi.setRotation(90);
					g.drawImage(imi, 0, 0);
				}
				if((i&SIDE_BOTTOM)>0){
					imi.setRotation(180);
					g.drawImage(imi, 0, 0);
				}
				if((i&CORNER_TL)>0){
					imid.setRotation(0);
					g.drawImage(imid, 0, 0);
				}
				if((i&CORNER_TR)>0){
					imid.setRotation(270);
					g.drawImage(imid, 0, 0);
				}
				if((i&CORNER_BL)>0){
					imid.setRotation(90);
					g.drawImage(imid, 0, 0);
				}
				if((i&CORNER_BR)>0){
					imid.setRotation(180);
					g.drawImage(imid, 0, 0);
				}
				borders.add(i, borde);
			}*/
		}
		im = im.getSubImage(textureX, textureY, textureW, textureH);
		//Global.setDepth(0);
	}
	
	void createTileIDX(int i) throws Exception{
		//Global.setDepth(1);
		Image borde = im.copy();
		Graphics g = borde.getGraphics();
		if((i&SIDE_TOP)>0){
			imBord.setRotation(0);
			g.drawImage(imBord, 0, 0);
		}
		if((i&SIDE_LEFT)>0){
			imBord.setRotation(270);
			g.drawImage(imBord, 0, 0);
		}
		if((i&SIDE_RIGHT)>0){
			imBord.setRotation(90);
			g.drawImage(imBord, 0, 0);
		}
		if((i&SIDE_BOTTOM)>0){
			imBord.setRotation(180);
			g.drawImage(imBord, 0, 0);
		}
		if((i&CORNER_TL)>0){
			imCorn.setRotation(0);
			g.drawImage(imCorn, 0, 0);
		}
		if((i&CORNER_TR)>0){
			imCorn.setRotation(270);
			g.drawImage(imCorn, 0, 0);
		}
		if((i&CORNER_BL)>0){
			imCorn.setRotation(90);
			g.drawImage(imCorn, 0, 0);
		}
		if((i&CORNER_BR)>0){
			imCorn.setRotation(180);
			g.drawImage(imCorn, 0, 0);
		}
		borders.set(i, borde);
		//Global.setDepth(0);
	}
	
	public Image getTile(int border) throws Exception{
		if(borders.get(border).getHeight()<1||borders.get(border).getWidth()<1) createTileIDX(border);
		return borders.get(border).copy();
	}
	public Image getTile() throws Exception{
		return getTile(0);
	}
}
