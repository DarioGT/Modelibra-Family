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
package org.modelibra.wicket.concept.selection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.modelibra.ISelector;
import org.modelibra.PropertySelector;
import org.modelibra.config.PropertyConfig;

/**
 * Property selector bean. Used as model object for
 * SelectionPanel.SelectionForm.
 * 
 * @author Vedad Kirlic
 * @version 2008-01-19
 */
public class PropertySelectorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private PropertyConfig propertyConfig;

	private String rule;

	private boolean caseSensitive = true;

	private String text;

	private Number value1;

	private Number value2;

	private Date date1;

	private Date date2;

	public static final String[] STRING_RULES = { "STRING_" + ISelector.EQUAL,
			ISelector.CONTAIN, ISelector.CONTAIN_ALL, ISelector.CONTAIN_SOME,
			ISelector.BEGIN, ISelector.END };

	public static String[] NUMBER_RULES = { ISelector.EQUAL,
			ISelector.GREATER_EQUAL, ISelector.GREATER_THAN,
			ISelector.LESS_EQUAL, ISelector.LESS_THAN, ISelector.BETWEEN };

	/**
	 * Constructs property selector bean.
	 */
	public PropertySelectorBean() {
		super();
	}

	/**
	 * Checks if selection is caseSensitive.
	 * 
	 * @return caseSensitive <code>true</code> if selection is case sensitive
	 */
	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	/**
	 * Sets caseSensitive
	 * 
	 * @param caseSensitive
	 */
	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	/**
	 * Gets text.
	 * 
	 * @return text.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets text.
	 * 
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets value1
	 * 
	 * @return value1
	 */
	public Number getValue1() {
		return value1;
	}

	/**
	 * Sets value1
	 * 
	 * @param value
	 */
	public void setValue1(Number value) {
		this.value1 = value;
	}

	/**
	 * Gets value2
	 * 
	 * @return value2
	 */
	public Number getValue2() {
		return value2;
	}

	/**
	 * Sets value2
	 * 
	 * @param value
	 */
	public void setValue2(Number value) {
		this.value2 = value;
	}

	/**
	 * Gets date1
	 * 
	 * @return date1
	 */
	public Date getDate1() {
		return date1;
	}

	/**
	 * Sets date1
	 * 
	 * @param date
	 */
	public void setDate1(Date date) {
		this.date1 = date;
	}

	/**
	 * Gets date2
	 * 
	 * @return date2
	 */
	public Date getDate2() {
		return date2;
	}

	/**
	 * Sets date2
	 * 
	 * @param date
	 */
	public void setDate2(Date date) {
		this.date2 = date;
	}

	/**
	 * Gets selection rule
	 * 
	 * @return rule selection rule
	 */
	public String getRule() {
		return rule;
	}

	/**
	 * Sets selection rule
	 * 
	 * @return selection rule
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}

	/**
	 * Gets property selector constructed from this bean.
	 * 
	 * @return propertySelector
	 */
	public PropertySelector getPropertySelector() {
		PropertySelector propertySelector = new PropertySelector(propertyConfig
				.getCode());

		Class<?> claz = propertyConfig.getPropertyClassObject();
		if (claz.equals(String.class)) {
			if (getRule().endsWith(ISelector.EQUAL)) {
				propertySelector.defineEqual(text);
			} else if (getRule().equals(ISelector.CONTAIN)) {
				propertySelector.defineContain(text);
			} else if (getRule().equals(ISelector.CONTAIN_ALL)) {
				propertySelector
						.defineContainAll(parseExpressionAsStringArray());
			} else if (getRule().equals(ISelector.CONTAIN_SOME)) {
				propertySelector
						.defineContainSome(parseExpressionAsStringArray());
			} else if (getRule().equals(ISelector.BEGIN)) {
				propertySelector.defineBegin(text);
			} else if (getRule().equals(ISelector.END)) {
				propertySelector.defineEnd(text);
			}
			propertySelector.setCaseSensitive(isCaseSensitive());
		} else if (claz.getSuperclass().equals(Number.class)) {
			if (getRule().equals(ISelector.EQUAL)) {
				propertySelector.defineEqual(value1);
			} else if (getRule().equals(ISelector.GREATER_EQUAL)) {
				propertySelector.defineGreaterEqual(value1);
			} else if (getRule().equals(ISelector.GREATER_THAN)) {
				propertySelector.defineGreaterThan(value1);
			} else if (getRule().equals(ISelector.LESS_EQUAL)) {
				propertySelector.defineLessEqual(value1);
			} else if (getRule().equals(ISelector.LESS_THAN)) {
				propertySelector.defineLessThan(value1);
			} else if (getRule().equals(ISelector.BETWEEN)) {
				propertySelector.defineBetween(value1, value2);
			}
		} else if (claz.equals(Date.class)) {
			if (getRule().equals(ISelector.EQUAL)) {
				propertySelector.defineEqual(date1);
			} else if (getRule().equals(ISelector.GREATER_EQUAL)) {
				propertySelector.defineGreaterEqual(date1);
			} else if (getRule().equals(ISelector.GREATER_THAN)) {
				propertySelector.defineGreaterThan(date1);
			} else if (getRule().equals(ISelector.LESS_EQUAL)) {
				propertySelector.defineLessEqual(date1);
			} else if (getRule().equals(ISelector.LESS_THAN)) {
				propertySelector.defineLessThan(date1);
			} else if (getRule().equals(ISelector.BETWEEN)) {
				propertySelector.defineBetween(date1, date2);
			}
		}

		return propertySelector;
	}

	/**
	 * Extract values from string expression. " " is used as separator
	 * 
	 * @return string array
	 */
	private String[] parseExpressionAsStringArray() {
		String[] candidates = ((String) text).split(" ");
		ArrayList<String> strings = new ArrayList<String>();
		for (int i = 0; i < candidates.length; i++) {
			String string = candidates[i].trim();
			if (!string.isEmpty()) {
				strings.add(string);
			}
		}
		// candidates used as argument only for toArray method to extract
		// correct type
		return strings.toArray(candidates);
	}

}