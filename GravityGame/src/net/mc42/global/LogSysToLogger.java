package net.mc42.global;

import org.newdawn.slick.util.LogSystem;

import net.mc42.games.API;
import net.mc42.global.Global.levels;

public class LogSysToLogger implements ILogger{

	private LogSystem log;
	
	public LogSysToLogger(LogSystem l){
		log = l;
	}
	
	@Override
	public void log(levels l, String msg, Exception error) {
		// TODO Auto-generated method stub
		if(msg==null)msg="";
		if(l.equals(Global.levels.DEBUG)) log.debug(msg);
		if(l.equals(Global.levels.INFO)) log.info(msg);
		if(l.equals(Global.levels.WARNING))if(error==null)log.warn(msg);else log.warn(msg, error);
		if(l.equals(Global.levels.SEVERE))if(error==null)log.error(msg);else log.error(msg, error);
		if(l.equals(Global.levels.FATAL)){if(error==null)log.error(msg);else log.error(msg, error);API.Game.stop();}
	}

}
