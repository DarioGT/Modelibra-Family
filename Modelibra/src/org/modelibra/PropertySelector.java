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
package org.modelibra;

/**
 * Definition of a property selector.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2007-10-08
 */
public class PropertySelector implements ISelector {

	private String propertyCode;

	private String relationalOperator; // e.g., EQUAL

	private Object[] values;

	private boolean caseSensitive = true;

	/**
	 * Defines a property selector.
	 * 
	 * @param propertyCode
	 *            property code
	 */
	public PropertySelector(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	/**
	 * Gets a property code used.
	 * 
	 * @return property code
	 */
	public String getPropertyCode() {
		return propertyCode;
	}

	/**
	 * Sets a relational operator, e.g., EQUAL.
	 * 
	 * @param relationalOperator
	 *            relational operator
	 */
	private void setRelationalOperator(String relationalOperator) {
		this.relationalOperator = relationalOperator;
	}

	/**
	 * Gets a relational operator, e.g., EQUAL.
	 * 
	 * @return relational operator
	 */
	public String getRelationalOperator() {
		return relationalOperator;
	}

	/**
	 * Sets a value.
	 * 
	 * @param value
	 *            value
	 */
	private void setValue(Object value) {
		if (values == null) {
			values = new Object[] { value };
		} else {
			this.values[0] = value;
		}
	}

	/**
	 * Gets a value, the first one if there is more than one.
	 * 
	 * @return value
	 */
	public Object getValue() {
		if (values != null && values.length > 0) {
			return values[0];
		}
		return null;
	}

	/**
	 * Sets an array of values.
	 * 
	 * @param values
	 *            array of values
	 */
	private void setValues(Object[] values) {
		this.values = values;
	}

	/**
	 * Gets an array of values.
	 * 
	 * @return array of values
	 */
	public Object[] getValues() {
		return values;
	}

	/**
	 * Sets the case sensitive switch, which is true by default. If false,
	 * selection is made ignoring case.
	 * 
	 * @param caseSensitive
	 *            <code>true</code> if case sensitive
	 */
	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	/**
	 * Checks the case sensitive switch, which is true by default. If false,
	 * selection is made ignoring case.
	 * 
	 * @return <code>true</code> if to propagate
	 */
	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	/**
	 * Creates a static ALL rule. Used to select all entities.
	 * 
	 * @return property rule
	 */
	public void defineAll() {
		setRelationalOperator(ALL);
	}

	/**
	 * Creates a static NONE rule. Used to select no entities.
	 * 
	 * @return property rule
	 */
	public void defineNone() {
		setRelationalOperator(NONE);
	}

	/**
	 * Creates a static NULL rule. Used to select entities that have NULL in the
	 * given entity property.
	 * 
	 * @param propertyCode
	 *            property code
	 * @return property rule
	 */
	public void defineNull() {
		setRelationalOperator(NULL);
	}

	/**
	 * Creates a static EQUAL rule. Used to select entities where the entity
	 * property is equal to the value.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param value
	 *            property value
	 * @return property rule
	 */
	public void defineEqual(Object value) {
		setRelationalOperator(EQUAL);
		setValue(value);
	}

	/**
	 * Creates a static CONTAIN rule. Used to select entities where the entity
	 * property contains the value. The property type is either String (the
	 * value type is String) or Collection (the value type is Object).
	 * 
	 * @param propertyCode
	 *            property code
	 * @param value
	 *            property value
	 * @return property rule
	 */
	public void defineContain(Object value) {
		setRelationalOperator(CONTAIN);
		setValue(value);
	}

	/**
	 * Creates a static CONTAIN SOME rule. Used to select entities where the
	 * entity property contains some of the values. The property type is either
	 * String (the value type is String) or Collection (the value type is
	 * Object).
	 * 
	 * @param propertyCode
	 *            property code
	 * @param values
	 *            array of property values
	 * @return property rule
	 */
	public void defineContainSome(Object[] values) {
		setRelationalOperator(CONTAIN_SOME);
		setValues(values);
	}

	/**
	 * Creates a static CONTAIN ALL rule. Used to select entities where the
	 * entity property contains all of the values. The property type is either
	 * String (the value type is String) or Collection (the value type is
	 * Object).
	 * 
	 * @param propertyCode
	 *            property code
	 * @param values
	 *            array of property values
	 * @return property rule
	 */
	public void defineContainAll(Object[] values) {
		setRelationalOperator(CONTAIN_ALL);
		setValues(values);
	}

	/**
	 * Creates a static BEGIN rule. Used to select entities where the entity
	 * property begins with the value. The property type is String and the value
	 * type is String.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param value
	 *            property value
	 * @return property rule
	 */
	public void defineBegin(Object value) {
		setRelationalOperator(BEGIN);
		setValue(value);
	}

	/**
	 * Creates a static END rule. Used to select entities where the entity
	 * property ends with the value. The property type is String and the value
	 * type is String.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param value
	 *            property value
	 * @return property rule
	 */
	public void defineEnd(Object value) {
		setRelationalOperator(END);
		setValue(value);
	}

	/**
	 * Creates a static MATCH rule. Used to select entities where the entity
	 * property matches entirely the regular expression value (String.matches).
	 * The property type is String and the value type is String. See:
	 * http://java.sun.com/j2se/1.5.0/docs/api/java/util/regex/Pattern.html#sum
	 * .
	 * 
	 * @param propertyCode
	 *            property code
	 * @param value
	 *            property value
	 * @return property rule
	 */
	public void defineMatch(Object value) {
		setRelationalOperator(MATCH);
		setValue(value);
	}

	/**
	 * Creates a static IN rule. Used to select entities where the entity
	 * property is equal to one of the values.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param values
	 *            array of property values
	 * @return property rule
	 */
	public void defineIn(Object[] values) {
		setRelationalOperator(IN);
		setValues(values);
	}

	/**
	 * Creates a static LESS THAN rule. Used to select entities where the entity
	 * property is less than the value. The property type must be Comparable.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param value
	 *            property value
	 * @return property rule
	 */
	public void defineLessThan(Object value) {
		setRelationalOperator(LESS_THAN);
		setValue(value);
	}

	/**
	 * Creates a static LESS OR EQUAL rule. Used to select entities where the
	 * entity property is less than or equal to the value. The property type
	 * must be Comparable.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param value
	 *            property value
	 * @return property rule
	 */
	public void defineLessEqual(Object value) {
		setRelationalOperator(LESS_EQUAL);
		setValue(value);
	}

	/**
	 * Creates a static GREATER THAN rule. Used to select entities where the
	 * entity property is greater than the value. The property type must be
	 * Comparable.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param value
	 *            property value
	 * @return property rule
	 */
	public void defineGreaterThan(Object value) {
		setRelationalOperator(GREATER_THAN);
		setValue(value);
	}

	/**
	 * Creates a static GREATER OR EQUAL rule. Used to select entities where the
	 * entity property is greater than or equal to the value. The property type
	 * must be Comparable.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param value
	 *            property value
	 * @return property rule
	 */
	public void defineGreaterEqual(Object value) {
		setRelationalOperator(GREATER_EQUAL);
		setValue(value);
	}

	/**
	 * Creates a static BETWEEN rule. Used to select entities where the entity
	 * property is between (including) the two values. The property type must be
	 * Comparable.
	 * 
	 * @param propertyCode
	 *            property code
	 * @param value1
	 *            property value
	 * @param value2
	 *            property value
	 * @return property rule
	 */
	public void defineBetween(Object value1, Object value2) {
		setRelationalOperator(BETWEEN);
		setValues(new Object[] { value1, value2 });
	}

}