package net.mc42.global;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Utils {
	public static String capitalizeFirstLetter(String original){
	    if(original.length() == 0)
	        return original;
	    return original.substring(0, 1).toUpperCase() + original.substring(1);
	}
	public static Method getAnnotatedMethod(Class<? extends Annotation> anno, Class<?> c, String name){
		
		//Global.log(Global.levels.DEBUG, anno.getName());
		
		for(Method m:c.getMethods()){
			if(m.isAnnotationPresent(anno) && m.getName().equals(name)){
				return m;
			}
			//if(m.isAnnotationPresent(anno))Global.log(Global.levels.DEBUG, m.getName() + "=" + name + ": " + (m.getName().equals(name)));
			//Global.log(Global.levels.DEBUG, m.getName() + "=" + name + ": " + m.isAnnotationPresent(anno));
		}
		
		return null;
	}
	public static Method getAnnotatedMethod(Class<? extends Annotation> anno, Class<?> c){

		//Global.log(Global.levels.INFO, anno.getName());
		
		for(Method m:c.getMethods()){
			if(m.isAnnotationPresent(anno)){
				return m;
			}
			//if(m.isAnnotationPresent(anno))Global.log(Global.levels.DEBUG, m.getName() + "=" + name + ": " + (m.getName().equals(name)));
			//Global.log(Global.levels.INFO, m.getName() + ": " + m.isAnnotationPresent(anno));
		}
		
		return null;
	}
	
	
}

