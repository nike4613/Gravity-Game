package net.mc42.global;

import java.security.Permission;

class ExitPrevention extends SecurityManager {

	  SecurityManager original;
	  
	  static ExitPrevention ep = null;

	  protected static void disableExit(){
		  if(ep==null)init();
		  System.setSecurityManager(ep);
	  }
	  
	  protected static void enableExit(){
		  if(ep==null)init();
		  System.setSecurityManager(ep.original);
	  }
	  
	  private static void init() {
		  // TODO Auto-generated method stub
		  ep = new ExitPrevention(System.getSecurityManager());
	  }

	  ExitPrevention(SecurityManager original) {
		  this.original = original;
	  }

	  /** Deny permission to exit the VM. */
	   public void checkExit(int status) {
	     throw( new SecurityException("DO NOT USE SYSTEM.EXIT()!!! Please use API.Game.stop() instead. Send this to the developers!") );
	  }

	  /** Allow this security manager to be replaced,
	  if fact, allow pretty much everything. */
	  public void checkPermission(Permission perm) {
	  }
}