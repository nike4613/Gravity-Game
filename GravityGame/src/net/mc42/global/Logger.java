package net.mc42.global;

import java.text.DateFormat;
import java.util.Date;

import net.mc42.games.API;

class Logger implements ILogger{
	public void log(Global.levels l, String msg, Exception error){
		Date d = new Date(); 
		String prefix = "[" + DateFormat.getTimeInstance(DateFormat.MEDIUM).format(d) + 
				"][" + Thread.currentThread().getName() + 
				"][" + l.getLocalizedName() + "]" + ((Global.getDebugMode())?"(in class " + Global.getClassName() + ")":"") + ": ";
		String out = prefix;
		
		if(msg != null){
			out += msg;
			if(error != null && l!=Global.levels.FATAL){
				out += "\n" + prefix + error.toString();
			}
		} else if(error != null){
			out += "Exception occured with error " + error.toString();
		}
		
		boolean printed = false;
		if(l==Global.levels.FATAL||l==Global.levels.SEVERE||l==Global.levels.WARNING){System.err.println(out);printed = true;}
		if((l != Global.levels.DEBUG || Global.getDebugMode() == true) && !printed){
			System.out.println(out);
			printed = true;
		}
		if(error != null){
			if(l==Global.levels.FATAL){ error.printStackTrace(); API.Game.stop();}
			StackTraceElement[] s = error.getStackTrace();
			StringBuilder sb = new StringBuilder("                                                                                                                                                                                                                       ");
			sb.setLength(prefix.length() + 3);
			for(StackTraceElement e:s){
				if(l==Global.levels.SEVERE||l==Global.levels.WARNING)System.err.println(sb.toString() + "in " + e.toString());else System.out.println(sb.toString() + "in " + e.toString());
			}
		}
		if(l==Global.levels.FATAL) API.Game.stop();
	}
}
