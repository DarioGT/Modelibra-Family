package model.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflector {

	/**
	 * Executes an object method. If the object does not exist return null.
	 * 
	 * @param object
	 *            object
	 * @param methodName
	 *            method name
	 * @param paramValues
	 *            array of parameter values
	 * @return returned object
	 */
	public Object executeMethod(Object object, String methodName,
			Object... paramValues) {
		if (object == null) {
			return null;
		}
		Object returnObject = null;
		Class<?> claz = object.getClass();
		Class<?>[] paramClasses = new Class[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			paramClasses[i] = paramValues[i].getClass();
		}
		Method method = getMethod(claz, methodName, paramClasses);
		if (method != null)
			try {
				returnObject = method.invoke(object, paramValues);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (NullPointerException e) {
				throw new RuntimeException(e);
			}
		return returnObject;
	}

	/**
	 * Gets an instance (object) for a constructor and its parameter objects.
	 * 
	 * @param constructor
	 *            constructor
	 * @param paramValues
	 *            array of parameter objects
	 * @return instance
	 */
	public <T> T getInstance(Constructor<T> constructor, Object... paramValues) {
		T object = null;
		try {
			object = constructor.newInstance(paramValues);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (NullPointerException e) {
			throw new RuntimeException(e);
		}
		return object;
	}

	/**
	 * Gets the class constructor for a class and its parameter classes.
	 * 
	 * @param claz
	 *            class
	 * @param paramClasses
	 *            array of parameter classes
	 * @return class constructor
	 */
	public <T> Constructor<T> getConstructor(Class<T> claz,
			Class<?>... paramClasses) {
		Constructor<T> constructor = null;
		try {
			constructor = claz.getConstructor(paramClasses);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		return constructor;
	}

	/**
	 * Gets a class method.
	 * 
	 * @param claz
	 *            class
	 * @param methodName
	 *            method name
	 * @param paramClasses
	 *            array of parameter classes
	 * 
	 * @return a class method
	 */
	public Method getMethod(Class<?> claz, String methodName,
			Class<?>... paramClasses) {
		Method method = null;
		try {
			method = claz.getMethod(methodName, paramClasses);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (NullPointerException e) {
			throw new RuntimeException(e);
		}
		return method;
	}

}
