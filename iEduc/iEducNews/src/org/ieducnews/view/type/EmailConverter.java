package org.ieducnews.view.type;

import java.util.Locale;

import javax.mail.internet.AddressException;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import org.ieducnews.model.type.Email;

/**
 * Converts String to org.modelibra.type.Email or vice versa.
 */
public class EmailConverter implements IConverter {

	private static final long serialVersionUID = 1;

	/**
	 * Constructs an Email converter.
	 */
	public EmailConverter() {
		super();
	}

	/**
	 * Converts String to org.modelibra.type.Email.
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
			return new Email(source);
		} catch (AddressException e) {
			throw new ConversionException("Not a valid Email: " + source);
		}
	}

	/**
	 * Converts org.modelibra.type.Email to String.
	 * 
	 * @param source
	 *            source
	 * @param locale
	 *            locale
	 */
	public String convertToString(Object source, Locale locale) {
		if (source != null) {
			if (source instanceof Email) {
				Email sourceEmail = (Email) source;
				return sourceEmail.toString();
			} else if (source instanceof String) {
				String sourceString = (String) source;
				if (sourceString.equals("")) {
					return null;
				} else {
					return sourceString;
				}
			} else {
				throw new ConversionException("Not a valid Email: " + source);
			}
		}
		return null;
	}

}