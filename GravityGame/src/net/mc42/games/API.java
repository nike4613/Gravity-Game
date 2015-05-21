package net.mc42.games;

import net.mc42.global.DeepAPI;
import net.mc42.global.utils.Information;
import net.mc42.global.utils.Startable;

public abstract class API extends DeepAPI{

	public static final Startable Game = new Startable(){
		public void start(){if(MainObject==null)throw new NullPointerException("No main class!!");Exitable.stop();MainClass.main(MainObject);}
		public void stop() {Exitable.start();System.exit(0);}
	};
	public static GameMain MainObject = null;
	public static final Information Info = new Information(){

		@Override
		public String getVersion() {
			// TODO Auto-generated method stub
			return "a0.0.46";
		}

		@Override
		public String getAuthor() {
			// TODO Auto-generated method stub
			return "Anairkoen Schno";
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return "Oggle42 API";
		}
		
	};
	public static final Get GC = new Get(){public Object get() {return MainClass.globalShare;}};
}
