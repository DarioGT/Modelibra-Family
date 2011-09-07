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
package org.modelibra.wicket.type;

import java.util.Date;
import java.util.Locale;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import org.modelibra.exception.TypeRuntimeException;
import org.modelibra.util.Transformer;

/**
 * Converts String to java.util.Date or vice versa.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-30
 */
public class DateConverter implements IConverter {

	private static final long serialVersionUID = 107020L;

	private String datePattern;

	/**
	 * Constructs a Date converter.
	 */
	public DateConverter(String datePattern) {
		super();
		this.datePattern = datePattern;
	}

	/**
	 * Converts String to java.util.Date.
	 * 
	 * @param source
	 *            source
	 * @param locale
	 *            locale
	 */
	public Object convertToObject(String source, Locale locale) {
		if (source.equals("") || source == null) {
			return null;
		}
		try {
			return Transformer.date(source, datePattern);
		} catch (TypeRuntimeException e) {
			throw new ConversionException("Not a valid Date: " + source);
		}
	}

	/**
	 * Converts java.util.Date to String.
	 * 
	 * @param source
	 *            source
	 * @param locale
	 *            locale
	 */
	public String convertToString(Object source, Locale locale) {
		if (source != null) {
			if (source instanceof Date) {
				Date sourceDate = (Date) source;
				return Transformer.string(sourceDate, datePattern);
			} else if (source instanceof String) {
				String sourceString = (String) source;
				if (sourceString.equals("")) {
					return null;
				} else {
					return sourceString;
				}
			} else {
				throw new ConversionException("Not a valid Date: " + source);
			}
		}
		return null;
	}

}