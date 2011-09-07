/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.modelibra.util;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.modelibra.exception.MetaRuntimeException;
import org.modelibra.exception.ModelibraRuntimeException;

/**
 * Reflector, where Java reflection is centralized.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-10-16
 */
@SuppressWarnings("serial")
public class Reflector implements Serializable {

	private static ArrayList<String> errors = new ArrayList<String>();

	/**
	 * Gets reflector errors.
	 * 
	 * @return list of errors
	 */
	public static ArrayList<String> getErrors() {
		return errors;
	}

	/**
	 * Empties the reflector errors.
	 */
	public static void emptyErrors() {
		errors = new ArrayList<String>();
	}

	/**
	 * Gets a class object.
	 * 
	 * @param className
	 *            class name
	 * 
	 * @return class object
	 */
	public static Class<?> getClass(String className) {
		Class<?> claz = null;
		try {
			claz = Class.forName(className);
			// errors.add("Class name: " + claz.getName());
		} catch (ClassNotFoundException e) {
			errors.add("Class " + className + " is not found: "
					+ e.getMessage());
		}
		return claz;
	}

	/**
	 * Gets the class default constructor.
	 * 
	 * @param claz
	 *            class
	 * @return class constructor
	 */
	public static <T> Constructor<T> getConstructor(Class<T> claz) {
		Constructor<T> constructor = null;
		try {
			constructor = claz.getConstructor();
		} catch (NoSuchMethodException e) {
			errors.add("Constructor does not exist: " + e.getMessage());
		} catch (NullPointerException e) {
			errors.add("Class is null. ");
		}
		return constructor;
	}

	/**
	 * Gets the class default constructor for a given class name.
	 * 
	 * @param className
	 *            class name
	 * @return class constructor
	 */
	public static Constructor<?> getConstructor(String className) {
		Class<?> claz = getClass(className);
		return getConstructor(claz);
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
	public static <T> Constructor<T> getConstructor(Class<T> claz,
			Class<?>... paramClasses) {
		Constructor<T> constructor = null;
		try {
			constructor = claz.getConstructor(paramClasses);
		} catch (SecurityException e) {
			throw new MetaRuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new MetaRuntimeException(e);
		}
		return constructor;
	}

	/**
	 * Gets an instance (object) for a given class. Class must have default
	 * constructor.
	 * 
	 * @param claz
	 *            claz
	 * @return instance
	 */
	public static <T> T getInstance(Class<T> claz) {
		Constructor<T> constructor = getConstructor(claz);
		return getInstance(constructor);
	}

	/**
	 * Gets an instance (object) for a given class name. Class must have default
	 * constructor.
	 * 
	 * @param className
	 *            class name
	 * @return instance
	 */
	public static Object getInstance(String className) {
		Constructor<?> constructor = getConstructor(className);
		return getInstance(constructor);
	}

	/**
	 * Gets an instance (object) for a given class name and its String
	 * parameter.
	 * 
	 * @param className
	 *            class name
	 * @param stringParam
	 *            String param
	 * @return instance
	 */
	public static Object getInstance(String className, String stringParam) {
		Class<?> claz = getClass(className);
		Constructor<?> constructor = getConstructor(claz, String.class);
		return getInstance(constructor, stringParam);
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
	public static <T> T getInstance(Constructor<T> constructor,
			Object... paramValues) {
		T object = null;
		try {
			object = constructor.newInstance(paramValues);
		} catch (InvocationTargetException e) {
			errors.add("Constructor does not create an instance: "
					+ e.getMessage());
		} catch (InstantiationException e) {
			errors.add("Constructor does not create an instance: "
					+ e.getMessage());
		} catch (IllegalAccessException e) {
			errors.add("Constructor does not create an instance: "
					+ e.getMessage());
		} catch (NullPointerException e) {
			errors.add("Constructor is null. ");
		}
		return object;
	}

	/**
	 * Gets a class method.
	 * 
	 * @param className
	 *            class name
	 * @param methodName
	 *            method name
	 * @param paramClasses
	 *            parameter classes
	 * 
	 * @return a class method
	 */
	public static Method getMethod(String className, String methodName,
			Class<?>... paramClasses) {
		Class<?> claz = getClass(className);
		return getMethod(claz, methodName, paramClasses);
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
	public static Method getMethod(Class<?> claz, String methodName,
			Class<?>... paramClasses) {
		Method method = null;
		try {
			method = claz.getMethod(methodName, paramClasses);
		} catch (NoSuchMethodException e) {
			errors.add("Method " + methodName + " does not exist: "
					+ e.getMessage());
		} catch (NullPointerException e) {
			errors.add("Class or method name or parameter value is null. ");
		}
		return method;
	}

	/**
	 * Gets a class method return type (class).
	 * 
	 * @param claz
	 *            class
	 * @param methodName
	 *            method name
	 * @return class
	 */
	public static Class<?> getMethodReturnType(Class<?> claz,
			String methodName, Class<?>... paramClasses) {
		return getMethod(claz, methodName, paramClasses).getReturnType();
	}

	/**
	 * Gets a class method return type (class).
	 * 
	 * @param className
	 *            class name
	 * @param methodName
	 *            method name
	 * @return class
	 */
	public static Class<?> getMethodReturnType(String className,
			String methodName, Class<?>... paramClasses) {
		Class<?> claz = getClass(className);
		return getMethodReturnType(claz, methodName, paramClasses);
	}

	/**
	 * Executes a method.
	 * 
	 * @param claz
	 *            class
	 * @param methodName
	 *            method name
	 * @return method return object
	 */
	public static Object executeMethod(Class<?> claz, String methodName) {
		Object returnObject = null;
		Method method = getMethod(claz, methodName);
		if (method != null) {
			try {
				Object object = null; // static method only
				returnObject = method.invoke(object);
			} catch (InvocationTargetException e) {
				errors.add("Method " + methodName + " cannot be invoked: "
						+ e.getMessage());
			} catch (IllegalAccessException e) {
				errors.add("Method " + methodName + " cannot be invoked: "
						+ e.getMessage());
			} catch (NullPointerException e) {
				errors.add("Object or method name is null. ");
			}
		}
		return returnObject;
	}

	/**
	 * Executes a method.
	 * 
	 * @param claz
	 *            class
	 * @param methodName
	 *            method name
	 * @param paramValue
	 *            parameter value
	 * @param paramClass
	 *            parameter class
	 * @return method return object
	 */
	public static Object executeMethod(Class<?> claz, String methodName,
			Object paramValue, Class<?> paramClass) {
		Object returnObject = null;
		Method method = getMethod(claz, methodName, paramClass);
		if (method != null)
			try {
				Object[] args = new Object[1];
				args[0] = paramValue;
				Object object = null; // static method only
				returnObject = method.invoke(object, args);
				// errors.add("Method name: " + method.getName());
			} catch (InvocationTargetException e) {
				errors.add("Method " + methodName + " cannot be invoked: "
						+ e.getMessage());
				errors.add("Object class: " + claz.getSimpleName());
				errors.add("Method name: " + method.getName());
				errors.add("Param class: " + paramClass.getSimpleName());
				errors.add("Param: " + paramValue);
			} catch (IllegalAccessException e) {
				errors.add("Method " + methodName + " cannot be invoked: "
						+ e.getMessage());
			} catch (NullPointerException e) {
				errors
						.add("Object or method name or parameter value is null. ");
			}
		return returnObject;
	}

	/**
	 * Executes an object method. If the object does not exist return null
	 * without log.
	 * 
	 * @param object
	 *            object
	 * @param methodName
	 *            method name
	 * @return returned object
	 */
	public static Object executeMethod(Object object, String methodName) {
		if (object == null) {
			return null;
		}
		Object returnObject = null;
		Class<?> claz = object.getClass();
		Method method = getMethod(claz, methodName);
		if (method != null) {
			try {
				Object[] noArgs = new Object[0];
				returnObject = method.invoke(object, noArgs);
				// errors.add("Method name: " + method.getName());
			} catch (InvocationTargetException e) {
				errors.add("Method " + methodName + " cannot be invoked: "
						+ e.getMessage());
			} catch (IllegalAccessException e) {
				errors.add("Method " + methodName + " cannot be invoked: "
						+ e.getMessage());
			} catch (NullPointerException e) {
				errors.add("Object or method name is null. ");
			}
		}
		return returnObject;
	}

	/**
	 * Executes an object method. If the object does not exist return null
	 * without log.
	 * 
	 * @param object
	 *            object
	 * @param methodName
	 *            method name
	 * @param paramValue
	 *            parameter value
	 * @param paramClass
	 *            parameter class
	 * @return returned object
	 */
	public static Object executeMethod(Object object, String methodName,
			Object paramValue, Class<?> paramClass) {
		if (object == null) {
			return null;
		}
		Object returnObject = null;
		Class<?> claz = object.getClass();
		Method method = getMethod(claz, methodName, paramClass);
		if (method != null)
			try {
				returnObject = method.invoke(object, paramValue);
				// errors.add("Method name: " + method.getName());
			} catch (InvocationTargetException e) {
				errors.add("Method " + methodName + " cannot be invoked: "
						+ e.getMessage());
				errors.add("Object class: " + claz.getSimpleName());
				errors.add("Object: " + object);
				errors.add("Method name: " + method.getName());
				errors.add("Param class: " + paramClass.getSimpleName());
				errors.add("Param: " + paramValue);
			} catch (IllegalAccessException e) {
				errors.add("Method " + methodName + " cannot be invoked: "
						+ e.getMessage());
			} catch (NullPointerException e) {
				errors
						.add("Object or method name or parameter value is null. ");
			}
		return returnObject;
	}

	/**
	 * Executes an object method. If the object does not exist return null
	 * without log.
	 * 
	 * @param object
	 *            object
	 * @param methodName
	 *            method name
	 * @param paramValues
	 *            array of parameter values
	 * @return returned object
	 */
	public static Object executeMethod(Object object, String methodName,
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
				throw new MetaRuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new MetaRuntimeException(e);
			} catch (NullPointerException e) {
				throw new MetaRuntimeException(e);
			}
		return returnObject;
	}

	/**
	 * Executes an object method with null argument. If the object does not
	 * exist return null without log.
	 * 
	 * @param object
	 *            object
	 * @param methodName
	 *            method name
	 * @param paramClass
	 *            parameter class
	 * @return returned object
	 */
	public static Object executeMethodWithNull(Object object,
			String methodName, Class<?>... paramClasses) {
		if (object == null) {
			return null;
		}
		Object returnObject = null;
		Class<?> claz = object.getClass();
		Method method = getMethod(claz, methodName, paramClasses);
		if (method != null)
			try {
				Object[] args = new Object[paramClasses.length];
				for (int i = 0; i < args.length; i++) {
					args[i] = null;
				}
				returnObject = method.invoke(object, args);
			} catch (InvocationTargetException e) {
				errors.add("Method " + methodName + " cannot be invoked: "
						+ e.getMessage());
			} catch (IllegalAccessException e) {
				errors.add("Method " + methodName + " cannot be invoked: "
						+ e.getMessage());
			} catch (NullPointerException e) {
				errors
						.add("Object or method name or parameter class is null. ");
			}
		return returnObject;
	}

	/**
	 * Gets a map of field names. The key is the field name and the value is the
	 * field name with the first letter capitalized.
	 * 
	 * @param claz
	 *            class
	 * @return map of field names
	 */
	public static Map<String, String> getFieldNames(Class<?> claz) {
		Map<String, String> fieldsMap = null;
		try {
			Field[] fields = claz.getDeclaredFields();
			fieldsMap = new HashMap<String, String>();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String fieldName = field.getName();
				TextHandler textHandler = new TextHandler();
				String cFieldName = textHandler.firstLetterToUpper(fieldName);
				fieldsMap.put(fieldName, cFieldName);
			}
		} catch (NullPointerException e) {
			errors.add("Class is null. ");
		}
		return fieldsMap;
	}

	/**
	 * Gets a map of field names. The key is the field name and the value is the
	 * field name with the first letter capitalized.
	 * 
	 * @param className
	 *            class name
	 * @return map of field names
	 */
	public static Map<String, String> getFieldNames(String className) {
		Class<?> claz = getClass(className);
		return getFieldNames(claz);
	}

	/**
	 * Sets an object property. Property setter is required. Null value can be
	 * passed to this method.
	 * 
	 * @see Reflector#setPropertyToNull(Object, String)
	 * 
	 * @param object
	 *            object that contains property
	 * @param propertyName
	 *            name of the property to set
	 * @param value
	 *            new value for the property
	 */
	public static void setProperty(Object object, String propertyName,
			Object value) {
		if (value == null) {
			setPropertyToNull(object, propertyName);
			return;
		}

		TextHandler textHandler = new TextHandler();
		String setterMethod = "set"
				+ textHandler.firstLetterToUpper(propertyName);
		executeMethod(object, setterMethod, value);
	}

	/**
	 * Sets an object property to null. Property setter is required. This method
	 * requires for property type to be supplied. If you do not have property
	 * type at hand, and want to avoid resolving one via reflection consider
	 * using {@link Reflector#setPropertyToNull(Object, String)}.
	 * 
	 * @param object
	 *            object that contains property
	 * @param propertyName
	 *            name of the property to set to null
	 * @param propertyType
	 *            Class object identifying the declared type of the property
	 */
	public static void setPropertyToNull(Object object, String propertyName,
			Class<?> propertyType) {
		TextHandler textHandler = new TextHandler();
		String setFieldName = "set"
				+ textHandler.firstLetterToUpper(propertyName);
		executeMethodWithNull(object, setFieldName, propertyType);
	}

	/**
	 * Sets an object property to null. Property setter is required. This method
	 * will examine property type. If you already have property type at hand use
	 * {@link Reflector#setPropertyToNull(Object, String, Class)}.
	 * 
	 * @param object
	 *            object that contains property
	 * @param propertyName
	 *            name of the property to set to null
	 */
	public static void setPropertyToNull(Object object, String propertyName) {
		Class<?> fieldType = getFieldType(object.getClass(), propertyName);
		setPropertyToNull(object, propertyName, fieldType);
	}

	/**
	 * Gets Class object identifying the declared type for the filed defined by
	 * fieldName.
	 * 
	 * @param declaringClass
	 *            class in which the field is declared
	 * @param fieldName
	 *            name of the field
	 * @return Class object identifying the declared type for the field defined
	 *         by fieldName.
	 */
	private static Class<?> getFieldType(Class<?> declaringClass,
			String fieldName) {
		try {
			return declaringClass.getDeclaredField(fieldName).getType();
		} catch (SecurityException e) {
			throw new ModelibraRuntimeException(e);
		} catch (NoSuchFieldException e) {
			throw new ModelibraRuntimeException(e);
		}
	}

	/**
	 * Gets an object property. Property getter is required.
	 * 
	 * @param object
	 *            object that contains property
	 * @param propertyName
	 *            name of the property to set
	 * @return object property value
	 */
	public static Object getProperty(Object object, String propertyName) {
		TextHandler textHandler = new TextHandler();
		String getterMethod = "get"
				+ textHandler.firstLetterToUpper(propertyName);
		return Reflector.executeMethod(object, getterMethod);
	}

}