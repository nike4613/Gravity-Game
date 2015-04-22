package org.newdawn.slick.util;

import java.security.AccessController;
import java.security.PrivilegedAction;

import net.mc42.global.Global;

/**
 * A simple central logging system
 * 
 * @author kevin
 */
public final class Log {
	/** true if activated by the system property "org.newdawn.slick.forceVerboseLog" */
	private static boolean forcedVerbose = false;
	
	/**
	 * The debug property which can be set via JNLP or startup parameter to switch
	 * logging mode to verbose for games that were released without verbose logging
	 * value must be "true"
	 */
	private static final String forceVerboseProperty = "org.newdawn.slick.forceVerboseLog";
	
	/**
	 * the verbose property must be set to "true" to switch on verbose logging
	 */
	private static final String forceVerbosePropertyOnValue = "true";
	
	/**
	 * The log is a simple static utility, no construction
	 */
	private Log() {
		
	}
	
	/**
	 * Set the log system that will have all of the log info 
	 * sent to it.
	 * 
	 * @param system The system to use for logging.
	 */
	public static void setLogSystem(LogSystem system) {
	}
	
	/**
	 * Indicate that we want verbose logging.
	 * The call is ignored if verbose logging is forced by the system property
	 * "org.newdawn.slick.forceVerboseLog"
	 * 
	 * @param v True if we want verbose logging (INFO and DEBUG)
	 */
	public static void setVerbose(boolean v) {
		if (forcedVerbose)
			return;
	}

	/**
	 * Check if the system property org.newdawn.slick.verboseLog is set to true.
	 * If this is the case we activate the verbose logging mode
	 */
	public static void checkVerboseLogSetting() {
		try {
			AccessController.doPrivileged(new PrivilegedAction() {
	            public Object run() {
					String val = System.getProperty(Log.forceVerboseProperty);
					if ((val != null) && (val.equalsIgnoreCase(Log.forceVerbosePropertyOnValue))) {
						Log.setForcedVerboseOn();
					}
					
					return null;
	            }
			});
		} catch (Throwable e) {
			// ignore, security failure - probably an applet
		}
	}
	
	/**
	 * Indicate that we want verbose logging, even if switched off in game code.
	 * Only be called when system property "org.newdawn.slick.forceVerboseLog" is set to true.
	 * You must not call this method directly.
	 */
	public static void setForcedVerboseOn() {
		forcedVerbose = true;
	}
	
	/**
	 * Log an error
	 * 
	 * @param message The message describing the error
	 * @param e The exception causing the error
	 */
	public static void error(String message, Throwable e) {
		Global.setDepth(1);
		Global.log(Global.levels.SEVERE, message, (Exception) e);
		Global.setDepth(0);
	}

	/**
	 * Log an error
	 * 
	 * @param e The exception causing the error
	 */
	public static void error(Throwable e) {
		Global.setDepth(1);
		Global.log(Global.levels.SEVERE,  (Exception) e);
		Global.setDepth(0);
	}

	/**
	 * Log an error
	 * 
	 * @param message The message describing the error
	 */
	public static void error(String message) {
		Global.setDepth(1);
		Global.log(Global.levels.SEVERE, message);
		Global.setDepth(0);
	}

	/**
	 * Log a warning
	 * 
	 * @param message The message describing the warning
	 */
	public static void warn(String message) {
		Global.setDepth(1);
		Global.log(Global.levels.WARNING, message);
		Global.setDepth(0);
	}
	
	/**
	 * Log a warning
	 * 
	 * @param message The message describing the warning
	 * @param e The issue causing the warning
	 */
	public static void warn(String message, Throwable e) {
		Global.setDepth(1);
		Global.log(Global.levels.WARNING, message, (Exception) e);
		Global.setDepth(0);
	}

	/**
	 * Log an information message
	 * 
	 * @param message The message describing the infomation
	 */
	public static void info(String message) {
		Global.setDepth(1);
		Global.log(Global.levels.INFO, message);
		Global.setDepth(0);
	}

	/**
	 * Log a debug message
	 * 
	 * @param message The message describing the debug
	 */
	public static void debug(String message) {
		Global.setDepth(1);
		Global.log(Global.levels.DEBUG, message);
		Global.setDepth(0);
	}
}
