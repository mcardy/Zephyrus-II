package net.minezrc.zephyrus.core.util.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionUtils {
	
	/**
	 * Sets the value of a field
	 * @param obj The object to set the field in
	 * @param value The value to set the field to
	 * @param fieldName The field name
	 */
	public static void setField(Object obj, Object value, String fieldName) {
		try {
			getField(obj.getClass(), fieldName).set(obj, value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the value of a static field
	 * @param obj The object to set the field in
	 * @param value The value to set the field to
	 * @param fieldName The field name
	 */
	public static void setStaticField(Class<?> cls, Object value, String fieldName) {
		try {
			getField(cls, fieldName).set(null, value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static void setStaticFinalField(Class<?> cls, Object value, String fieldName) {
		try {
			Field field = getField(cls, fieldName);
			Field modifier = Field.class.getDeclaredField("modifiers");
			modifier.setAccessible(true);
			modifier.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			field.set(null, value);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static Class<?> getClass(String name) {
		try {
			return Class.forName(name);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	/**
	 * Gets the value of a field
	 * 
	 * @param obj The object to get the field from
	 * @param fieldName The field name to get
	 * @return null if there is no field or an exception was thrown
	 */
	public static Object getField(Object obj, String fieldName) {
		try {
			return getField(obj.getClass(), fieldName).get(obj);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Retrieves a field
	 * 
	 * @param cl The class to retrieve the field from
	 * @param fieldName The name of the field
	 * @return null if there is no field or an exception was thrown
	 */
	public static Field getField(Class<?> cl, String fieldName) {
		try {
			Field field = cl.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Invokes a method
	 * 
	 * @param cl The class to invoke the method from
	 * @param method The name of the method to invoke
	 * @return The result of the method
	 */
	public static Object invokeMethod(Object obj, String method, Class<?>[] params, Object... args) {
		try {
			return getMethod(obj.getClass(), method, params).invoke(obj, args);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Invokes a method
	 * 
	 * @param cl The class to invoke the method from
	 * @param method The name of the method to invoke
	 * @return The result of the method
	 */
	public static Object invokeMethod(Object obj, String method, Object... args) {
		try {
			return getMethod(obj.getClass(), method).invoke(obj, args);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Invokes a static method
	 * @param cls The class of the static method
	 * @param method The name of the method to invoke
	 * @return The result of the method
	 */
	public static Object invokeStaticMethod(Class<?> cls, String method, Class<?>[] params, Object... args) {
		try {
			return getMethod(cls, method, params).invoke(null, args);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Retrieves a method
	 * 
	 * @param cl The class to retrieve the method from
	 * @param method The name of the method
	 * @param args The argument types that the method should have
	 * @return null if no method was found
	 */
	public static Method getMethod(Class<?> cl, String method, Class<?>[] params) {
		for (Method m : cl.getDeclaredMethods()) {
			if (m.getName().equals(method) && classListEqual(params, m.getParameterTypes())) {
				m.setAccessible(true);
				return m;
			}
		}
		if (cl.getSuperclass() != null)
			return getMethod(cl.getSuperclass(), method, params);
		return null;
	}

	/**
	 * Retrieves a method
	 * 
	 * @param cl The class to retrieve the method from
	 * @param method The name of the method
	 * @return null if no method was found
	 */
	public static Method getMethod(Class<?> cl, String method) {
		for (Method m : cl.getDeclaredMethods()) {
			if (m.getName().equals(method)) {
				m.setAccessible(true);
				return m;
			}
		}
		if (cl.getSuperclass() != null)
			return getMethod(cl.getSuperclass(), method);
		return null;
	}
	
	public static Object newInstance(Class<?> cls) {
		try {
			return cls.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object newInstance(Class<?> cls, Class<?>[] params, Object... args) {
		try {
			return cls.getConstructor(params).newInstance(args);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static boolean classListEqual(Class<?>[] l1, Class<?>[] l2) {
		boolean equal = true;

		if (l1.length != l2.length) {
			return false;
		}

		for (int i = 0; i < l1.length; i++) {
			if (l1[i] != l2[i]) {
				return false;
			}
		}
		return equal;
	}

}
