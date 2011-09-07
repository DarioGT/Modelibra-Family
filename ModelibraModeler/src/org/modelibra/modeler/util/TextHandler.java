/*
 * dmLite -- Domain Model Lite
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
package org.modelibra.modeler.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Extracts a text from an input text.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-01-16
 */
public class TextHandler {

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
		if (endPosition > 0) {
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
		int endPosition = text.lastIndexOf(end);
		if (endPosition > 0) {
			// Drop the begin.
			withoutBegin = text.substring(endPosition + 1);
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
		String withoutBegin = text;
		int endPosition = beginLength - 1;
		if (endPosition > 0) {
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
	public String extractBegin(String text, int beginTextLenghth) {
		if (text == null) {
			return null;
		}
		String inputText = text.trim();
		String beginText = inputText;
		if (inputText.length() > beginTextLenghth) {
			// Drop the end.
			beginText = text.substring(0, beginTextLenghth);
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
		String beginText = extractBegin(text, beginTextLenghth);
		if (beginText != null) {
			if (!beginText.equals(text)) {
				beginText = beginText + "...";
			}
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
			return null;
		}
		String inputText = text.trim();
		String endText = inputText;
		int inputTextLength = inputText.length();
		if (inputTextLength > endTextLenghth) {
			int beginTextLength = inputTextLength - endTextLenghth;
			endText = text.substring(beginTextLength);
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
	public String extractClassSimpleName(String className) {
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
		return dotPath.replace(".", SEPARATOR);
	}

	/**
	 * Transforms the first letter of the text to the upper case.
	 * 
	 * @param text
	 *            input text
	 * @return text with the first letter in the upper case
	 */
	public String firstLetterToUpper(String text) {
		try {
			String firstLetter = text.substring(0, 1);
			String cFirstLetter = firstLetter.toUpperCase();
			String firstLetterToUpper = text.replaceFirst(firstLetter,
					cFirstLetter);
			return firstLetterToUpper;
		} catch (Exception e) {
			return text;
		}
	}

	/**
	 * Transforms the first letter of the text to the lower case.
	 * 
	 * @param text
	 *            input text
	 * @return text with the first letter in the lower case
	 */
	public String firstLetterToLower(String text) {
		try {
			String firstLetter = text.substring(0, 1);
			String cFirstLetter = firstLetter.toLowerCase();
			String firstLetterToLower = text.replaceFirst(firstLetter,
					cFirstLetter);
			return firstLetterToLower;
		} catch (Exception e) {
			return text;
		}
	}

	/**
	 * Transforms the text to the lower case.
	 * 
	 * @param text
	 *            input text
	 * @return text with all letters in the lower case
	 */
	public String allLettersToLower(String text) {
		try {
			return text.toLowerCase();
		} catch (Exception e) {
			return text;
		}
	}

	/**
	 * Extracts a text without the first and last characters. An input text
	 * should have at least three characters, otherwise the result is null.
	 * 
	 * @param text
	 *            input text
	 * @return text without the first and last characters
	 */
	public String extractTextWithoutFirstAndLastCharacters(String text) {
		String withoutFirstLast = null;
		if (text.length() > 2) {
			String lastCharacterString = text.substring(text.length() - 1, text
					.length());
			String withoutLast = dropEnd(text, lastCharacterString);
			withoutFirstLast = withoutLast.substring(1);
		}
		return withoutFirstLast;
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
	 * Extracts a list of substrings from a text that looks like [1,2]. An input
	 * text should have at least three characters, otherwise the result is an
	 * empty list.
	 * 
	 * @param text
	 *            input text
	 * @return list of number strings
	 */
	public List<String> extractSeparatedSubtrings(String text, String separator) {
		List<String> numberStrings = new ArrayList<String>();
		if (text.length() > 2) {
			String withoutFirstLast = extractTextWithoutFirstAndLastCharacters(text);
			String[] substrings = withoutFirstLast.split(separator);
			for (String substring : substrings) {
				numberStrings.add(substring);
			}
		}
		return numberStrings;
	}

	public static void main(String[] args) {
		TextHandler textExtractor = new TextHandler();
		String validationType = "countries.xml";
		String endText = textExtractor.extractEnd(validationType, 4);
		System.out.println("End text: " + endText);
		String beginText = textExtractor.extractBegin(validationType,
				validationType.length() - 4);
		System.out.println("Begin text: " + beginText);
	}

}