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
		
		for(Method m:c.getMethods()){
			if(m.isAnnotationPresent(anno)){
				return m;
			}
		}
		
		return null;
	}
}
