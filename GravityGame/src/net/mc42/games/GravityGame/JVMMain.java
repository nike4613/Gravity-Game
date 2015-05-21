package net.mc42.games.GravityGame;

import net.mc42.games.API;

public class JVMMain {

	public static void main(String[] args) {
		// TODO Set logger, do any pre-preinit, 
		API.MainObject = new GravityGameMain();
		API.Game.start();
	}

}
