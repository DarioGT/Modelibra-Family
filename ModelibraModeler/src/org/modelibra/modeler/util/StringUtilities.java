package org.modelibra.modeler.util;

/**
 * 
 * @author Vincent Dussault
 * @version 2003
 */
public class StringUtilities {

	public static String withFirstCharLowerCase(String text) {
		if (text != null && text.length() > 1) {
			char firstChar = Character.toLowerCase(text.charAt(0));
			return firstChar + text.substring(1);
		} else if (text != null && text.length() == 1) {
			return text.toLowerCase();
		} else {
			return text;
		}
	}

	public static String withFirstCharUpperCase(String text) {
		if (text != null && text.length() > 1) {
			char firstChar = Character.toUpperCase(text.charAt(0));
			return firstChar + text.substring(1);
		} else if (text != null && text.length() == 1) {
			return text.toUpperCase();
		} else {
			return text;
		}
	}

	public static String replace(String searchable, String oldText,
			String newText) {
		StringBuffer sb = new StringBuffer(searchable);
		for (int i = 0; i < sb.length(); i++) {
			int remainingCharsCount = (sb.length() - i);
			if (remainingCharsCount >= oldText.length()) {
				String substring = sb.substring(i, i + oldText.length());
				if (substring.equals(oldText)) {
					sb.replace(i, i + oldText.length(), newText);
					if (oldText.length() < newText.length()) {
						i += newText.length() - oldText.length();
					}
				}
			}
		}
		return sb.toString();
	}

	public static boolean contains(String searchable, String toLookFor) {
		StringBuffer sb = new StringBuffer(searchable);
		for (int i = 0; i < sb.length(); i++) {
			int remainingCharsCount = (sb.length() - i);
			if (remainingCharsCount >= toLookFor.length()) {
				String substring = sb.substring(i, i + toLookFor.length());
				if (substring.equals(toLookFor)) {
					return true;
				}
			}
		}
		return false;
	}

	public static String stringBetween(String searchable, String before,
			String after) {
		StringBuffer sb = new StringBuffer(searchable);
		boolean beforeExists = false;
		String stringBetween = null;
		int indexOfFirstCharAfterBefore = 0;
		for (int i = 0; i < sb.length(); i++) {
			int remainingCharsCount = (sb.length() - i);
			if (remainingCharsCount >= before.length()) {
				String substring = sb.substring(i, i + before.length());
				if (substring.equals(before)) {
					beforeExists = true;
					indexOfFirstCharAfterBefore = i + before.length();
				}
			}
		}
		if (beforeExists) {
			for (int i = indexOfFirstCharAfterBefore; i < sb.length(); i++) {
				int remainingCharsCount = (sb.length() - i);
				if (remainingCharsCount >= after.length()) {
					String substring = sb.substring(i, i + after.length());
					if (substring.equals(after)) {
						stringBetween = sb.substring(
								indexOfFirstCharAfterBefore, i);
						return stringBetween;
					}
				}
			}
		}
		return null;
	}

	public static String removePrefix(String prefix, String name) {
		int index = name.indexOf(prefix);
		if (index != 0)
			return name;
		name = name.substring(prefix.length());
		return name;
	}

	public static String firstLetterToUpper(String text) {
		String firstLetter = text.substring(0, 1);
		String cFirstLetter = firstLetter.toUpperCase();
		String firstLetterToUpper = text
				.replaceFirst(firstLetter, cFirstLetter);
		return firstLetterToUpper;
	}

	public static String firstLetterToLower(String text) {
		String firstLetter = text.substring(0, 1);
		String cFirstLetter = firstLetter.toLowerCase();
		String firstLetterToLower = text
				.replaceFirst(firstLetter, cFirstLetter);
		return firstLetterToLower;
	}

	public String allLettersToLower(String text) {
		return text.toLowerCase();
	}

	public static void main(String[] args) {
		String before = "<customTag>";
		String after = "</customTag>";
		String text = "Je suis un <customTag>d�veloppeur Java</customTag> z�l�.";
		String stringBetween = StringUtilities.stringBetween(text, before,
				after);
		System.out.println(stringBetween);
		String newText = "�tudiant qui est aussi un " + stringBetween + " tr�s";
		String newString = StringUtilities.replace(text, before + stringBetween
				+ after, newText);
		System.out.println(newString);
	}

}