package net.mc42.games.GravityGame.world;

import java.net.URL;
import java.util.ArrayList;

import net.mc42.games.world.Entity;
import net.mc42.games.world.Tile;
import net.mc42.games.world.TileEntity;

public class Layer {
	private int tilesx;
	private int tilesy;
	
	private ArrayList<
		ArrayList<Tile>
	> tiles;
	private ArrayList<TileEntity> tileE;
	private ArrayList<Entity> entities;
	
	protected Layer(int txsz,int tysz){
		tilesx = txsz;
		tilesy = tysz;
		tiles = new ArrayList<ArrayList<Tile>>();
	}
	
	protected void loadDataFrom(URL data){
		
	}
	
	protected void loadDataFrom(String data){
		
	}
	
	protected void tickEntities(){
		for(Entity e:entities){
			e.tick();
		}
	}
	
	protected void tickTileEntities(){
		for(TileEntity e:tileE){
			e.tick();
		}
	}
	
	protected void tick(){
		tickEntities();
		tickTileEntities();
	}
	
}
