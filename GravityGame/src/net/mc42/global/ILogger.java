package net.mc42.global;

public interface ILogger {
	
	public void log(Global.levels l, String msg, Exception error);
	
	/*public static void logStatic(levels l, String msg);
	
	public static void logStatic(levels l, Exception error);
	
	public static void log(Logger.levels l, String msg, Exception error);*/
}
