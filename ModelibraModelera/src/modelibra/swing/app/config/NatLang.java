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
package modelibra.swing.app.config;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.util.Log4jConfigurator;

/**
 * Natural language for supporting i18n versions.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-01-20
 */
public class NatLang {

	private static Log log = LogFactory.getLog(NatLang.class);

	private static final String TEXT_RESOURCES = "modelibra.swing.app.config.TextRes";

	private Locale locale;

	private ResourceBundle resource;

	/**
	 * Gets the current locale.
	 * 
	 * @return current locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * Gets the text based on a given text key for the current language.
	 * 
	 * @param key
	 *            text key
	 * @return current language text
	 */
	public String getText(String key) {
		String lookFor = null;
		try {
			lookFor = resource.getString(key);
		} catch (MissingResourceException e) {
			System.out.println(e.getMessage() + " // Missing string: " + key);
			lookFor = "Missing string: " + key;
		}
		return lookFor;
	}

	/**
	 * Sets the language with the defualt location for text resources.
	 * 
	 * @param language
	 *            language
	 */
	public void setNaturalLanguage(String language) {
		setNaturalLanguage(language, TEXT_RESOURCES);
	}

	/**
	 * Sets the language with the location for text resources.
	 * 
	 * @param language
	 *            language
	 * @param textResources
	 *            text resources
	 */
	public void setNaturalLanguage(String language, String textResources) {
		try {
			if ((language == null) || (language.equals("en"))) {
				locale = Locale.ENGLISH;
			} else if (language.equals("fr")) {
				locale = Locale.FRENCH;
			} else if (language.equals("ba")) {
				locale = new Locale("ba"); // bosnian language
			} else if (language.equals("hr")) {
				locale = new Locale("hr"); // croatian language
			} else if (language.equals("sr")) {
				locale = new Locale("sr"); // serbian language
			} else { // other languages are not supported, English used
				locale = Locale.ENGLISH;
			}
			resource = ResourceBundle.getBundle(textResources, locale);
		} catch (MissingResourceException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		Log4jConfigurator log4jConfigurator = new Log4jConfigurator();
		log4jConfigurator.configure();

		NatLang lang = new NatLang();
		lang.setNaturalLanguage("en");
		Locale locale = lang.getLocale();
		log.info("--- Language ---");
		log.info("Country: " + locale.getCountry());
		log.info("Language: " + locale.getLanguage());
		String text = lang.getText("selectDirectory");
		log.info("Selected text: " + text);
	}

}
