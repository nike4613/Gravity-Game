package net.mc42.games.world;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import net.mc42.global.Global;

import org.json.JSONArray;
import org.json.JSONObject;
import org.newdawn.slick.Image;

public class TileSet {
	
	ArrayList<TileType> tiles;
	Image tilesetImage;
	
	public TileSet(URL jsonfile) throws Exception{
		InputStream file = jsonfile.openStream();
		ByteArrayOutputStream  jsonB = new ByteArrayOutputStream ();
		byte[] in = new byte[1024];
		while(file.read(in)>0){
			jsonB.write(in);
		}
		String jsonS = jsonB.toString("UTF-8");		
		
		JSONObject json = new JSONObject(jsonS);
		
		tiles = new ArrayList<TileType>();
		
		//Global.log(Global.levels.DEBUG, new File(jsonfile.getPath()).getParent() + "/" + json.getString("image"));
		
		tilesetImage = new Image(new File(jsonfile.getPath()).getParent().replace('\\', '/').substring(1) + "/" + json.getString("image"));
		
		JSONArray tils = json.getJSONArray("tiles");
		
		for(int i=0;i<tils.length();i++){
			JSONObject o = tils.getJSONObject(i);
			Global.log(Global.levels.DEBUG, o);
			tiles.add(i, new TileType(o, tilesetImage));
		}
	}
	
	public TileType getTile(int index){
		return tiles.get(index);
	}
	public TileType getTile(){
		return getTile(0);
	}
}
