package net.mc42.global;

public class Global {

	private static ILogger logger = new Logger();
	private static boolean debugMsgLocation = false;
	
	static {
		debugMsgLocation = (System.getProperty("net.mc42.global.logger.debugMode")=="true")?true:false;
	}
	
	public static void setLogger(Class<ILogger> log) throws InstantiationException, IllegalAccessException{
		logger = log.newInstance();
	}
	
	public static void setDebugMode(boolean b){
		debugMsgLocation = b || (System.getProperty("net.mc42.global.logger.debugMode")=="true")?true:false;
	}
	
	public static boolean getDebugMode() {
		return debugMsgLocation;
	}
	
	public static String getClassName(int depth)
    {
      final StackTraceElement[] ste = new Throwable().getStackTrace();
      depth = depth+3;

      //System. out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
      return ste[depth].getClassName() + "[" + ste[depth].getMethodName() + "]";
    }
	
	public static void log(Global.levels l, String msg){
		logger.log(l, msg, null);
	}
	
	public static void log(Global.levels l, Exception error){
		logger.log(l, null, error);
	}
	
	public static void log(Global.levels l, String msg, Exception error){
		logger.log(l, msg, error);
	}
	
	public static enum levels {
		INFO ("Info"),
		WARNING ("Warning"),
		SEVERE ("Severe"),
		DEBUG ("Debug");
		
		private final String locName;

		levels(String s){
			locName = s;
		}
		
		public String getLocalizedName() {
			// TODO Auto-generated method stub
			return locName;
		}
	}
	
}
