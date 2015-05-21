package net.mc42.global;

import net.mc42.global.utils.Startable;

public class DeepAPI {
	protected static final Startable Exitable = new Startable(){

		@Override
		public void start() {
			// TODO Auto-generated method stub
			ExitPrevention.enableExit();
		}

		@Override
		public void stop() {
			// TODO Auto-generated method stub
			ExitPrevention.disableExit();
		}
		
	};
}
