package utils;


import java.lang.reflect.Field;

public class ReflectionHelper {
	public static Object createIntance(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
			Class.forName(className).getConstructors();
			return Class.forName(className).newInstance();
	}


	public static void setFieldValue(Object object, String fieldName, String value) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		
		Field field = object.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		
		if(field.getType().equals(String.class)){
			field.set(object, value);
		}
		if (field.getType().equals(int.class)){
			field.set(object, Integer.decode(value));
			field.setAccessible(false);
		}
		if ( field.getType().equals(long.class)){
			field.set(object, Long.decode(value));
			field.setAccessible(false);
		//} catch(…){}
		}
		
		
	}
		

}
