package net.mc42.games.world;

public abstract class TileEntity {
	protected int xpos;
	protected int ypos;
	
	public TileEntity(){}
	
	public void drawI(int pxptx, int pxpty){
		draw(xpos*pxptx,ypos*pxpty);
	}
	
	protected abstract void draw(int x,int y);
	public abstract void tick();
}
