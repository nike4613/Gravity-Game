package net.mc42.games.world;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import net.mc42.global.Global;

import org.json.JSONArray;
import org.json.JSONObject;
import org.lwjgl.opengl.GLContext;
import org.newdawn.slick.Image;

public class TileSet {
	
	ArrayList<TileType> tiles;
	ArrayList<Boolean> tilesLoaded;
	Image tilesetImage;
	protected int loadPercent = 0;
	boolean alLoaded = false;
	
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
		tilesLoaded = new ArrayList<Boolean>();
		
		//Global.log(Global.levels.DEBUG, new File(jsonfile.getPath()).getParent() + "/" + json.getString("image"));
		
		tilesetImage = new Image(new File(jsonfile.getPath()).getParent().replace('\\', '/').substring(1) + "/" + json.getString("image"));
		
		JSONArray tils = json.getJSONArray("tiles");
		
		for(int i=0;i<tils.length();i++){
			JSONObject o = tils.getJSONObject(i);
			tiles.add(i, new TileType(o, tilesetImage));
			tilesLoaded.add(i, false);
		}
	}
	
	public synchronized TileType getTile(int index){
		return tiles.get(index);
	}
	
	public synchronized int getLoadPercent(){
		return loadPercent;
	}
	
	private synchronized void loadTileInThread(int idx,int count) throws Exception{
		int prcnt = 0;
		int inc = (50/(2^4))/count;
		for(int i=0;i<(2^4);i++){
			getTile(idx).createTileIDX(i);
			prcnt += inc;
			loadPercent = prcnt;
		}
		for(int i=0;i<(2^4);i++){
			getTile(idx).createTileIDX(i>>4);
			prcnt += inc;
			loadPercent = prcnt;
		}
	}
	
	public void loadTile(int idx){
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Thread.currentThread().setName("TileLoad");
				try {
					loadTileInThread(idx, 1);
					synchronized(this){
						tilesLoaded.set(idx, true);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Global.log(Global.levels.SEVERE, "Error while loading tileset textures", e);
				}
			}
		});
		t.start();
		
	}
	
	public synchronized boolean isLoaded(int idx){
		return tilesLoaded.get(idx);
	}
	
	public synchronized boolean areAllLoaded(){
		return alLoaded;
	}
	
	public void loadTiles(){

		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Thread.currentThread().setName("TileLoad");
				try {
					GLContext.loadOpenGLLibrary();
					for(int i=0;i<tiles.size();i++){
						loadTileInThread(i,tiles.size());
						synchronized(this){
							tilesLoaded.set(i, true);
						}
					}
					synchronized(this){
						alLoaded = true;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Global.log(Global.levels.SEVERE, "Error while loading tileset textures", e);
				}
			}
		});
		t.start();
	}
	
}
