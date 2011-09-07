package org.ieducnews.view.type;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

/**
 * Converts String to java.net.URL or vice versa.
 */
public class UrlConverter implements IConverter {

	private static final long serialVersionUID = 1;

	/**
	 * Constructs a URL converter.
	 */
	public UrlConverter() {
		super();
	}

	/**
	 * Converts String to java.net.URL.
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
			return new URL(source);
		} catch (MalformedURLException e) {
			throw new ConversionException("Not a valid URL: " + source);
		}
	}

	/**
	 * Converts java.net.URL to String.
	 * 
	 * @param source
	 *            source
	 * @param locale
	 *            locale
	 */
	public String convertToString(Object source, Locale locale) {
		if (source != null) {
			if (source instanceof URL) {
				URL sourceURL = (URL) source;
				return sourceURL.toString();
			} else if (source instanceof String) {
				String sourceString = (String) source;
				if (sourceString.equals("")) {
					return null;
				} else {
					return sourceString;
				}
			} else {
				throw new ConversionException("Not a valid URL: " + source);
			}
		}
		return null;
	}

}