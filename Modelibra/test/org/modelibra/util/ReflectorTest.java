package org.modelibra.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.modelibra.exception.ModelibraRuntimeException;

/**
 * Tests for org.modelibra.util.Reflector
 * 
 * @author Vedad Kirlic
 * 
 */
public class ReflectorTest {

	// get class

	@Test
	public void stringBasedGetClass() throws Exception {
		Class<?> claz = Reflector.getClass(Person.class.getName());
		assertEquals(Person.class, claz);
	}

	@Ignore
	@Test(expected = ModelibraRuntimeException.class)
	public void stringBasetGetClassThrowsException() throws Exception {
		Reflector.getClass("non.existant.Class");
	}

	// get constructor

	@Test
	public void getStringBasedDefaultConstructor() throws Exception {
		String className = Person.class.getName();
		Constructor<?> constructor = Reflector.getConstructor(className);
		Constructor<Person> expectedConstructor = Person.class.getConstructor();
		assertEquals(expectedConstructor, constructor);
	}

	@Test
	public void getDefaultConstructor() throws Exception {
		Constructor<Person> constructor = Reflector
				.getConstructor(Person.class);
		Constructor<Person> expectedConstructor = Person.class.getConstructor();
		assertEquals(expectedConstructor, constructor);
	}

	@Test
	public void getOneArgConstructor() throws Exception {
		Constructor<Person> constructor = Reflector.getConstructor(
				Person.class, String.class);
		Constructor<Person> expectedConstructor = Person.class
				.getConstructor(String.class);
		assertEquals(expectedConstructor, constructor);
	}

	@Test
	public void getTwoArgsConstructor() throws Exception {
		Constructor<Person> constructor = Reflector.getConstructor(
				Person.class, String.class, String.class);
		Constructor<Person> expectedConstructor = Person.class.getConstructor(
				String.class, String.class);
		assertEquals(expectedConstructor, constructor);
	}

	// get instance

	@Test
	public void getInstanceForDefaultConstructor() throws Exception {
		Person instance = Reflector.getInstance(Person.class.getConstructor());
		assertNotNull(instance);
		assertEquals(Person.class, instance.getClass());
		assertNull(instance.firstName);
		assertNull(instance.lastName);
	}

	@Test
	public void getInstanceForOneArgConstructor() throws Exception {
		String firstName = "Vedad";
		Constructor<Person> constructor = Person.class
				.getConstructor(String.class);
		Person instance = Reflector.getInstance(constructor, firstName);
		assertNotNull(instance);
		assertEquals(Person.class, instance.getClass());
		assertEquals(firstName, instance.firstName);
		assertNull(instance.lastName);
	}

	@Test
	public void getInstanceForTwoArgsConstructor() throws Exception {
		String firstName = "Vedad";
		String lastName = "Kirlic";
		Constructor<Person> constructor = Person.class.getConstructor(
				String.class, String.class);
		Person instance = Reflector.getInstance(constructor, firstName,
				lastName);
		assertNotNull(instance);
		assertEquals(Person.class, instance.getClass());
		assertEquals(firstName, instance.firstName);
		assertEquals(lastName, instance.lastName);
	}

	@Test
	public void stringBasedGetInstance() throws Exception {
		Object instance = Reflector.getInstance(Person.class.getName());
		assertNotNull(instance);
		assertEquals(Person.class, instance.getClass());
	}

	// get method

	@Test
	public void stringBasedGetMethodWithNoArgs() throws Exception {
		String className = Person.class.getName();
		String methodName = "getFirstName";
		Method method = Reflector.getMethod(className, methodName);
		Method expectedMethod = Person.class.getMethod(methodName);
		assertEquals(expectedMethod, method);
	}

	@Test
	public void stringBasedGetMethodWithOneArg() throws Exception {
		String className = Person.class.getName();
		String methodName = "setFirstName";
		Class<String> paramClass = String.class;
		Method method = Reflector.getMethod(className, methodName, paramClass);
		Method expectedMethod = Person.class.getMethod(methodName, paramClass);
		assertEquals(expectedMethod, method);
	}

	@Test
	public void stringBasedGetMethodWithTwoArgs() throws Exception {
		String className = Person.class.getName();
		String methodName = "setFirstLastName";
		Class<String> firstParamClass = String.class;
		Class<String> secondParamClass = String.class;
		Method method = Reflector.getMethod(className, methodName,
				firstParamClass, secondParamClass);
		Method expectedMethod = Person.class.getMethod(methodName,
				firstParamClass, secondParamClass);
		assertEquals(expectedMethod, method);
	}

	@Test
	public void getMethodWithNoArgs() throws Exception {
		String methodName = "getFirstName";
		Method method = Reflector.getMethod(Person.class, methodName);
		Method expectedMethod = Person.class.getMethod(methodName);
		assertEquals(expectedMethod, method);
	}

	@Test
	public void getMethodWithOneArg() throws Exception {
		String methodName = "setFirstName";
		Class<String> paramClass = String.class;
		Method method = Reflector.getMethod(Person.class, methodName,
				paramClass);
		Method expectedMethod = Person.class.getMethod(methodName, paramClass);
		assertEquals(expectedMethod, method);
	}

	@Test
	public void getMethodWithTwoArgs() throws Exception {
		String methodName = "setFirstLastName";
		Class<String> firstParamClass = String.class;
		Class<String> secondParamClass = String.class;
		Method method = Reflector.getMethod(Person.class, methodName,
				firstParamClass, secondParamClass);
		Method expectedMethod = Person.class.getMethod(methodName,
				firstParamClass, secondParamClass);
		assertEquals(expectedMethod, method);
	}

	// get method return type

	@Test
	public void getNoArgMethodReturnType() throws Exception {
		Class<?> methodReturnType = Reflector.getMethodReturnType(Person.class,
				"getFirstName");
		assertEquals(String.class, methodReturnType);
	}

	@Test
	public void getOneArgMethodReturnType() throws Exception {
		Class<String> paramClass = String.class;
		Class<?> methodReturnType = Reflector.getMethodReturnType(Person.class,
				"getPerson", paramClass);
		assertEquals(Person.class, methodReturnType);
	}

	@Test
	public void getTwoArgMethodReturnType() throws Exception {
		Class<String> param1Class = String.class;
		Class<String> param2Class = String.class;
		Class<?> methodReturnType = Reflector.getMethodReturnType(Person.class,
				"getPerson", param1Class, param2Class);
		assertEquals(Person.class, methodReturnType);
	}

	@Test
	public void stringBasedGetMethodReturnType() throws Exception {
		String className = Person.class.getName();
		Class<?> methodReturnType = Reflector.getMethodReturnType(className,
				"getFirstName");
		assertEquals(String.class, methodReturnType);
	}

	@Test
	public void stringBasedGetOneArgMethodReturnType() throws Exception {
		String className = Person.class.getName();
		Class<String> paramClass = String.class;
		Class<?> methodReturnType = Reflector.getMethodReturnType(className,
				"getPerson", paramClass);
		assertEquals(Person.class, methodReturnType);
	}

	@Test
	public void stringBasedGetTwoArgMethodReturnType() throws Exception {
		String className = Person.class.getName();
		Class<String> param1Class = String.class;
		Class<String> param2Class = String.class;
		Class<?> methodReturnType = Reflector.getMethodReturnType(className,
				"getPerson", param1Class, param2Class);
		assertEquals(Person.class, methodReturnType);
	}

	// execute method

	@Test
	public void executeStaticMethod() throws Exception {
		Object returnedObject = Reflector.executeMethod(Person.class,
				"getPerson");
		assertEquals(Person.class, returnedObject.getClass());
	}

	@Test
	public void executeOneArgStaticMethod() throws Exception {
		String paramValue = "Vedad";
		Class<String> paramClass = String.class;
		Object returnedObject = Reflector.executeMethod(Person.class,
				"getPerson", paramValue, paramClass);
		assertEquals(Person.class, returnedObject.getClass());
		Person person = (Person) returnedObject;
		assertEquals(paramValue, person.getFirstName());
	}

	@Test
	public void executeMethod() throws Exception {
		String firstName = "Vedad";
		Person person = new Person(firstName);
		Object returnedObject = Reflector.executeMethod(person, "getFirstName");
		assertEquals(firstName, returnedObject);
	}

	@Test
	public void executeOneArgMethod() throws Exception {
		String paramValue = "Vedad";
		Person person = new Person();
		Reflector.executeMethod(person, "setFirstName", paramValue);
		assertEquals(paramValue, person.getFirstName());
	}

	@Test
	public void executeOneArgMethodWithParamClass() throws Exception {
		String paramValue = "Vedad";
		Person person = new Person();
		Class<String> paramClass = String.class;
		Reflector.executeMethod(person, "setFirstName", paramValue, paramClass);
		assertEquals(paramValue, person.getFirstName());
	}

	@Test
	public void executeTwoArgMethod() throws Exception {
		String param1Value = "Vedad";
		String param2Value = "Kirlic";
		Person person = new Person();
		Reflector.executeMethod(person, "setFirstLastName", param1Value,
				param2Value);
		assertEquals(param1Value, person.getFirstName());
		assertEquals(param2Value, person.getLastName());
	}

	@Test
	public void executeTwoArgMethodWithArray() throws Exception {
		String param1Value = "Vedad";
		String param2Value = "Kirlic";
		Object[] paramValues = { param1Value, param2Value };
		Person person = new Person();
		Reflector.executeMethod(person, "setFirstLastName", paramValues);
		assertEquals(param1Value, person.getFirstName());
		assertEquals(param2Value, person.getLastName());
	}

	@Test
	public void executeMethodWithNull() throws Exception {
		Person person = new Person("Vedad");
		Class<String> paramClass = String.class;
		Reflector.executeMethodWithNull(person, "setFirstName", paramClass);
		assertNull(person.getFirstName());
	}

	@Test
	public void executeTwoArgMethodWithNull() throws Exception {
		Person person = new Person("Vedad", "Kirlic");
		Class<String> param1Class = String.class;
		Class<String> param2Class = String.class;
		Reflector.executeMethodWithNull(person, "setFirstLastName",
				param1Class, param2Class);
		assertNull(person.getFirstName());
		assertNull(person.getLastName());
	}

	// set property

	@Test
	public void setProperty() throws Exception {
		Person person = new Person();
		String newValue = "Vedad";
		Reflector.setProperty(person, "firstName", newValue);
		assertEquals(newValue, person.getFirstName());
	}
	
	@Test
	public void setPropertyWithNull() throws Exception {
		Person person = new Person("Vedad");		
		Reflector.setProperty(person, "firstName", null);
		assertNull(person.getFirstName());
	}

	@Test
	public void setPropertyToNull() throws Exception {
		Person person = new Person("Vedad");
		Reflector.setPropertyToNull(person, "firstName");
		assertNull(person.getFirstName());
	}
	
	@Test
	public void setPropertyToNullWithFieldType() throws Exception {
		Person person = new Person("Vedad");
		Reflector.setPropertyToNull(person, "firstName", String.class);
		assertNull(person.getFirstName());
	}

	// get property

	@Test
	public void getProperty() throws Exception {
		Person person = new Person("Vedad");
		Object propertyValue = Reflector.getProperty(person, "firstName");
		assertEquals("Vedad", propertyValue);
	}

	@Test
	public void getFieldNames() throws Exception {
		Map<String, String> fieldNames = Reflector.getFieldNames(Person.class);
		Map<String, String> expectedFiledNames = new HashMap<String, String>();
		expectedFiledNames.put("firstName", "FirstName");
		expectedFiledNames.put("lastName", "LastName");
		assertEquals(expectedFiledNames, fieldNames);
	}

	@Test
	public void stringBasedGetFieldNames() throws Exception {
		String className = Person.class.getName();
		Map<String, String> fieldNames = Reflector.getFieldNames(className);
		Map<String, String> expectedFiledNames = new HashMap<String, String>();
		expectedFiledNames.put("firstName", "FirstName");
		expectedFiledNames.put("lastName", "LastName");
		assertEquals(expectedFiledNames, fieldNames);
	}

	/**
	 * Class to be used for reflection testing
	 * 
	 */
	static class Person {

		private String firstName;

		private String lastName;

		public Person() {
		}

		public Person(String firstName) {
			this.setFirstName(firstName);
		}

		public Person(String firstName, String lastName) {
			this.setFirstName(firstName);
			this.setLastName(lastName);
		}

		public void setFirstName(String name) {
			this.firstName = name;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setFirstLastName(String firstName, String lastName) {
			setFirstName(firstName);
			setLastName(lastName);
		}

		public static Person getPerson() {
			return new Person();
		}

		public static Person getPerson(String firstName) {
			return new Person(firstName, "NN");
		}

		public static Person getPerson(String firstName, String lastName) {
			return new Person(firstName, lastName.toUpperCase());
		}
	}
}
