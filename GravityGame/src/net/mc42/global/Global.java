package net.mc42.global;

public class Global {

	private static ILogger logger = new Logger();
	private static boolean debugMsgLocation = false;
	//public static Field mainShare;
	//private static ExecutionEndThread end = new ExecutionEndThread();
	
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
	
	public static void setLogger(Class<ILogger> log) throws InstantiationException, IllegalAccessException{
		logger = log.newInstance();
	}
	
	public static void setDebugMode(boolean b){
		debugMsgLocation = (b || ((System.getProperty("loggerdebugMode")=="true")?true:false));
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
	
	static {
		debugMsgLocation = (System.getProperty("loggerdebugMode")=="true")?true:false;
		//Runtime.getRuntime().addShutdownHook(end);
	}
	
	/*public static void addEndHook(Method m){
		end.addMethod(m);
	}*/
	
	/*static {
		debugMsgLocation=true;
		Global.log(Global.levels.DEBUG, "Before main()");
		//System.out.println("Before main()");
		try {
		String dottedPackage = "";
        List<Class<?>> classes = new ArrayList<Class<?>>();
        URL upackage = ClassLoader.getSystemClassLoader().getResource("");

        BufferedReader dis = new BufferedReader(new InputStreamReader((InputStream) upackage.getContent()));
        String line = null;
        while ((line = dis.readLine()) != null) {
            if(line.endsWith(".class")) {
               
				classes.add(Class.forName(line.substring(0,line.lastIndexOf('.'))));
			
            }
        }
        for(Class<?> c:classes){
        	if(c.getAnnotation(BaseClass.class)!=null){
        		mainShare = c.getDeclaredField("globalShare");

        		//exitMethod = c.getDeclaredMethod("onExit");
        		break;
        	}
        	//if(Utils.getAnnotatedMethod(ExitMethod.class, c)!=null)exits.add(Utils.getAnnotatedMethod(ExitMethod.class, c));
        }
        for(Class<?> c:classes){
        	/*if(Utils.getAnnotatedMethod(ExitMethod.class, c)!=null)*exits.add(Utils.getAnnotatedMethod(ExitMethod.class, c));
        }
        } catch (Exception e) {
				// TODO Auto-generated catch block
			Global.log(levels.WARNING, e);
		}
	}*/
	
}
