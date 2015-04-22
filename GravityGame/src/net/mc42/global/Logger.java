package net.mc42.global;

import java.text.DateFormat;
import java.util.Date;

class Logger implements ILogger{
	public void log(Global.levels l, String msg, Exception error){
		Date d = new Date(); 
		String prefix = "[" + DateFormat.getTimeInstance(DateFormat.MEDIUM).format(d) + 
				"][" + Thread.currentThread().getName() + 
				"][" + l.getLocalizedName() + "]" + ((Global.getDebugMode())?"(in class " + Global.getClassName() + ")":"") + ": ";
		String out = prefix;
		
		if(msg != null){
			out += msg;
			if(error != null){
				out += "\n" + prefix + error.toString();
			}
		} else if(error != null){
			out += "Exception occured with error " + error.toString();
		}
		
		if(l != Global.levels.DEBUG || Global.getDebugMode() == true)
		System.out.println(out);
		if(error != null){
			StackTraceElement[] s = error.getStackTrace();
			StringBuilder sb = new StringBuilder("                                                                                                                                                                                                                       ");
			sb.setLength(prefix.length() + 3);
			for(StackTraceElement e:s){
				System.out.println(sb.toString() + "in " + e.toString());	
			}
		}
		if(l==Global.levels.FATAL) System.exit(1);
	}
}
