package first;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Observable;

@SuppressWarnings("serial")
public abstract class Entity<T extends IEntity<T>> extends Observable implements
		IEntity<T> {

	private Long oid;

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Long getOid() {
		return oid;
	}

	public Object getProperty(String propertyName) {
		String getterMethod = "get" + firstLetterToUpper(propertyName);
		return executeMethod(getterMethod);
	}

	private String firstLetterToUpper(String string) {
		return toUpperCase(string, 0);
	}

	/**
	 * Converts character at index location in string argument to upper case.
	 * 
	 * @param string
	 *            string - null is not allowed
	 * @param index
	 *            index of the character to convert to upper case. Must be in
	 *            range 0 <= index < string.length()
	 * @return String with character at index location converted to upper case.
	 * 
	 */
	private String toUpperCase(String string, int index) {
		if (string == null) {
			throw new IllegalArgumentException("'string' cannot be null");
		}
		if (index < 0) {
			throw new IllegalArgumentException("'index' cannot be negative:"
					+ index);
		}
		if (index >= string.length()) {
			return string;
		}
		return string.substring(0, index)
				+ Character.toUpperCase(string.charAt(index))
				+ string.substring(index + 1);
	}

	/**
	 * Executes an entity method without arguments.
	 * 
	 * @param methodName
	 *            method name
	 * @return returned object
	 */
	private Object executeMethod(String methodName) {
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
	private Method getMethod(Class<?> claz, String methodName,
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
	
	public void output() {
		System.out.println("oid: " + getOid());
	}

}
