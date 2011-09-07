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

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.Validate;

import com.sun.xml.internal.ws.util.StringUtils;

/**
 * Extracts a text from an input text.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-10-10
 */
@SuppressWarnings("serial")
public class TextHandler implements Serializable {

	public static final String SEPARATOR = File.separator;

	/**
	 * Drops the ending string from the input text.
	 * 
	 * @param text
	 *            input text
	 * @param end
	 *            end text
	 * @return input text with the end string dropped
	 */
	public String dropEnd(String text, String end) {		
		String withoutEnd = text;
		int endPosition = text.lastIndexOf(end);
		if (endPosition > -1) {
			// Drop the end.
			withoutEnd = text.substring(0, endPosition);
		}
		return withoutEnd;
	}

	/**
	 * Drops the beginning string from the input text.
	 * 
	 * @param text
	 *            input text
	 * @param end
	 *            end text
	 * @return input text with the beginning string dropped
	 */
	public String dropBegin(String text, String end) {
		String withoutBegin = text;
		int endPosition = text.indexOf(end);
		if (endPosition > -1) {
			// Drop the begin.
			withoutBegin = text.substring(endPosition + end.length());
		}
		return withoutBegin;
	}

	/**
	 * Drops the beginning string from the input text.
	 * 
	 * @param text
	 *            input text
	 * @param beginLength
	 *            length of the beginning string
	 * @return input text with the beginning string dropped
	 */
	public String dropBegin(String text, int beginLength) {
		if (beginLength > text.length()) {
			return "";
		} else if (beginLength < 0) {
			throw new IllegalArgumentException("'beginLength' cannot be <0");
		}
		String withoutBegin = text;
		int endPosition = beginLength - 1;
		if (endPosition > -1) {
			// Drop the begin.
			withoutBegin = text.substring(endPosition + 1);
		}
		return withoutBegin;
	}

	/**
	 * Extract the beginning of the input text given the beginning text length.
	 * 
	 * @param text
	 *            input text
	 * @param beginTextLenghth
	 *            begin text lenghth
	 * @return beginning text
	 */
	public String extractBegin(String text, int beginTextLength) {
		if (text == null) {
			throw new IllegalArgumentException("'text' cannot be null");
		} else if (beginTextLength > text.length()) {
			return text;			
		} else if (beginTextLength < 0) {
			throw new IllegalArgumentException(
					"'beginTextLength' cannot be negative");
		}
		String inputText = text.trim();
		String beginText = inputText;
		if (inputText.length() > beginTextLength) {
			// Drop the end.
			beginText = inputText.substring(0, beginTextLength);
		}
		return beginText;
	}

	/**
	 * Extract the beginning of the input text given the beginning text length,
	 * and add three dots to indicate that there is more.
	 * 
	 * @param text
	 *            input text
	 * @param beginTextLenghth
	 *            begin text lenghth
	 * @return beginning text
	 */
	public String extractBeginPlusThreeDots(String text, int beginTextLenghth) {
		if(beginTextLenghth > text.length()){
			return text;
		}
		String beginText = extractBegin(text, beginTextLenghth);
		if (beginText != null) {
			beginText = beginText + "...";
		}
		return beginText;
	}

	/**
	 * Extract the end of the input text given the end text length.
	 * 
	 * @param text
	 *            input text
	 * @param endTextLenghth
	 *            end text lenghth
	 * @return end text
	 */
	public String extractEnd(String text, int endTextLenghth) {
		if (text == null) {
			throw new IllegalArgumentException("'text' cannot be null");
		} else if (endTextLenghth > text.length()) {
			return text;			
		} else if (endTextLenghth < 0) {
			throw new IllegalArgumentException(
					"'endTextLenghth' cannot be negative");
		}
		String inputText = text.trim();
		String endText = inputText;
		int inputTextLength = inputText.length();
		if (inputTextLength > endTextLenghth) {
			int beginTextLength = inputTextLength - endTextLenghth;
			endText = inputText.substring(beginTextLength);
		}
		return endText;
	}

	/**
	 * Extracts the class simple name from a class full name.
	 * 
	 * @param className
	 *            class full name
	 * @return class simple name
	 */
	public String extractPackagePrefix(String className, String anchor) {
		if (className == null) {
			throw new IllegalArgumentException("'className' cannot be null");
		}
		if (anchor == null) {
			throw new IllegalArgumentException("'anchor' cannot be null");
		}
		if (!isQualifiedClassName(className)) {
			throw new IllegalArgumentException("'className': " + className
					+ ", is not qualified class name");
		}
		int anchorPosition = className.indexOf(anchor);
		return className.substring(0, anchorPosition);
	}

	/**
	 * Extracts the class simple name from a class full name.
	 * 
	 * @param className
	 *            class full name
	 * @return class simple name
	 */
	public String extractClassSimpleName(String className) {
		if (className == null) {
			throw new IllegalArgumentException("'className' cannot be null");
		}
		int dotPosition = className.lastIndexOf(".");
		return className.substring(dotPosition + 1);
	}

	/**
	 * Extracts the class package name from a class full name.
	 * 
	 * @param className
	 *            class full name
	 * @return class package name
	 */
	public String extractClassPackageName(String className) {
		if (className == null) {
			throw new IllegalArgumentException("'className' cannot be null");
		}
		String classSimpleName = extractClassSimpleName(className);
		return dropEnd(className, classSimpleName);
	}

	/**
	 * Extracts the class package name with slashes from a class full name.
	 * 
	 * @param className
	 *            class full name
	 * @return class package name with slashes
	 */
	public String extractClassPackageNameWithSlashes(String className) {
		String classPackageName = extractClassPackageName(className);
		return classPackageName.replace(".", SEPARATOR);
	}

	/**
	 * Replaces a dot with a directory/file separator.
	 * 
	 * @param dotPath
	 *            path with dots as separators
	 * @return path with directory/file separators
	 */
	public String replaceDotWithSlash(String dotPath) {
		if (dotPath == null) {
			throw new IllegalArgumentException("'dotPath' cannot be null");
		}
		return dotPath.replace(".", SEPARATOR);
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
	public String toUpperCase(String string, int index) {
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
	 * Converts character at index location in string argument to lower case.
	 * 
	 * @param string
	 *            string - null is not allowed
	 * @param index
	 *            index of the character to convert to lower case. Must be in
	 *            range 0 <= index < string.length()
	 * @return String with character at index location converted to lower case.
	 * 
	 */
	public String toLowerCase(String string, int index) {
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
				+ Character.toLowerCase(string.charAt(index))
				+ string.substring(index + 1);
	}

	/**
	 * Converts first letter to upper case.
	 * 
	 * @param string
	 *            string - null is not allowed
	 * @return String with first character converted to upper case.
	 */
	public String firstLetterToUpper(String string) {
		return toUpperCase(string, 0);
	}

	/**
	 * Converts first letter to lower case.
	 * 
	 * @param string
	 *            string - null is not allowed
	 * @return String with first character converted to lower case.
	 */
	public String firstLetterToLower(String string) {
		return toLowerCase(string, 0);
	}

	/**
	 * Transforms the text to the lower case.
	 * 
	 * @param text
	 *            input text
	 * @return text with all letters in the lower case
	 */
	public String allLettersToLower(String text) {
		if (text == null) {
			throw new IllegalArgumentException("'text' cannot be null");
		}
		return text.toLowerCase();
	}

	/**
	 * Puts the English text in plural form.
	 * 
	 * @param text
	 *            input text
	 * @return text in plural
	 */
	public String putInEnglishPlural(String text) {
		String plural = null;
		try {
			if (text.length() > 0) {
				String lastCharacterString = text.substring(text.length() - 1,
						text.length());
				if (lastCharacterString.equals("x")) {
					plural = text + "es";
				} else if (lastCharacterString.equals("z")) {
					plural = text + "zes";
				} else if (lastCharacterString.equals("y")) {
					String withoutLast = dropEnd(text, lastCharacterString);
					plural = withoutLast + "ies";
				} else {
					plural = text + "s";
				}
			}
		} catch (Exception e) {
			return text;
		}
		return plural;
	}

	/**
	 * This method, found in the <a href=
	 * "http://cse-mjmcl.cse.bris.ac.uk/blog/2007/02/14/1171465494443.html"
	 * >Weblog of MarcMcLaren</a>, ensures that the output String has only valid
	 * XML Unicode characters as specified by the XML 1.0 standard. For
	 * reference, please see <a
	 * href="http://www.w3.org/TR/2000/REC-xml-20001006#NT-Char">the
	 * standard</a>. This method will return an empty String if the input is
	 * null or empty.
	 * 
	 * @param in
	 *            the String whose non-valid characters we want to remove.
	 * @return the in String, stripped of non-valid characters
	 */
	public String stripNonValidXMLCharacters(String in) {
		StringBuffer out = new StringBuffer(); // Used to hold the output.
		char current; // Used to reference the current character.

		if (in == null || ("".equals(in)))
			return ""; // vacancy test.
		for (int i = 0; i < in.length(); i++) {
			current = in.charAt(i); // NOTE: No IndexOutOfBoundsException caught
			// here; it should not happen.
			if ((current == 0x9) || (current == 0xA) || (current == 0xD)
					|| ((current >= 0x20) && (current <= 0xD7FF))
					|| ((current >= 0xE000) && (current <= 0xFFFD))
					|| ((current >= 0x10000) && (current <= 0x10FFFF)))
				out.append(current);
		}
		return out.toString();
	}

	/**
	 * Removes first and last character from the text. If string has less then
	 * three characters empty string is returned.
	 * 
	 * @param text
	 *            input text - null is not allowed
	 * @return text without the first and last character
	 */
	public String removeFirstAndLastCharacter(String text) {
		if (text == null) {
			throw new IllegalArgumentException("'text' cannot be null");
		}
		String withoutFirstLast = null;
		if (text.length() > 2) {
			String lastCharacterString = text.substring(text.length() - 1, text
					.length());
			String withoutLast = dropEnd(text, lastCharacterString);
			withoutFirstLast = withoutLast.substring(1);
		} else {
			return "";
		}
		return withoutFirstLast;
	}

	/**
	 * Extracts a list of substrings from a text that looks like [1,2]. An input
	 * text should have at least three characters, otherwise the result is an
	 * empty list.
	 * 
	 * @param text
	 *            input text
	 * @return list of number strings
	 */
	public List<String> extractSeparatedSubtrings(String text, String separator) {
		if (text == null) {
			throw new IllegalArgumentException("'text' cannot be null");
		}
		if (separator == null) {
			throw new IllegalArgumentException("'separator' cannot be null");
		}
		List<String> numberStrings = new ArrayList<String>();
		if (text.length() > 2) {
			String withoutFirstLast = removeFirstAndLastCharacter(text);
			String[] substrings = withoutFirstLast.split(separator);
			for (String substring : substrings) {
				numberStrings.add(substring);
			}
		}
		return numberStrings;
	}

	boolean isQualifiedClassName(String string) {
		String quote = Pattern.quote(".");
		String[] strings = string.split(quote);
		if (strings.length == 0) {
			return false;
		}
		int i = 0;
		for (; i < strings.length - 1; i++) {
			if (!strings[i].toLowerCase().equals(strings[i])) {
				return false;
			}
		}
		if (Character.isLowerCase(strings[i].charAt(0))) {
			return false;
		}
		return true;
	}	
}