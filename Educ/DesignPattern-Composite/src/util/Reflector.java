package util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflector {
	
	/**
	 * Executes an entity method without arguments.
	 * 
	 * @param methodName
	 *            method name
	 * @return returned object
	 */
	public Object executeMethod(String methodName) {
		Object returnObject = null;
		Class<?> claz = getClass();
		Method method = getMethod(claz, methodName);
		if (method != null) {
			try {
				Object[] noArgs = new Object[0];
				returnObject = method.invoke(this, noArgs);
				// errors.add("Method name: " + method.getName());
			} catch (InvocationTargetException e) {
				System.out.println("Method " + methodName
						+ " cannot be invoked: " + e.getMessage());
			} catch (IllegalAccessException e) {
				System.out.println("Method " + methodName
						+ " cannot be invoked: " + e.getMessage());
			} catch (NullPointerException e) {
				System.out.println("Object or method name is null. ");
			}
		}
		return returnObject;
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
			System.out.println("Method " + methodName + " does not exist: "
					+ e.getMessage());
		} catch (NullPointerException e) {
			System.out
					.println("Class or method name or parameter value is null. ");
		}
		return method;
	}

}
