package org.modelibra.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

/**
 * TextHandler tests
 * 
 * @author Vedad Kirlic
 */
public class TextHandlerTest {

	// drop end

	@Test
	public void dropEnd() throws Exception {
		TextHandler textHandler = new TextHandler();
		String withoutEnd = textHandler.dropEnd("beginning middle end",
				"middle");
		assertEquals("beginning ", withoutEnd);
	}

	@Test
	public void dropEndWithTwoOcurrnecesOfEndString() throws Exception {
		TextHandler textHandler = new TextHandler();
		String withoutEnd = textHandler.dropEnd("beginning middle middle end",
				"middle");
		assertEquals("beginning middle ", withoutEnd);
	}

	@Test
	public void dropEndWithEndAtTheBeginning() throws Exception {
		TextHandler textHandler = new TextHandler();
		String withoutEnd = textHandler.dropEnd("beginning middle end",
				"beginning");
		assertEquals("", withoutEnd);
	}

	@Test
	public void dropEndWithEndSame() throws Exception {
		TextHandler textHandler = new TextHandler();
		String withoutEnd = textHandler.dropEnd("beginning middle end",
				"beginning middle end");
		assertEquals("", withoutEnd);
	}

	// drop begin

	@Test
	public void dropBegin() throws Exception {
		TextHandler textHandler = new TextHandler();
		String withoutEnd = textHandler.dropBegin("beginning middle end",
				"middle");
		assertEquals(" end", withoutEnd);
	}

	@Test
	public void dropBeginWithTwoOcurrneces() throws Exception {
		TextHandler textHandler = new TextHandler();
		String withoutEnd = textHandler.dropBegin(
				"beginning middle middle end", "middle");
		assertEquals(" middle end", withoutEnd);
	}

	@Test
	public void dropBeginWithBeginningAtTheEnd() throws Exception {
		TextHandler textHandler = new TextHandler();
		String withoutEnd = textHandler
				.dropBegin("beginning middle end", "end");

		assertEquals("", withoutEnd);
	}

	@Test
	public void dropBeginWithEndSame() throws Exception {
		TextHandler textHandler = new TextHandler();
		String withoutEnd = textHandler.dropBegin("beginning middle end",
				"beginning middle end");
		assertEquals("", withoutEnd);
	}

	// dropBegin(String, int)

	@Test
	public void lengthBasedDropBegin() throws Exception {
		TextHandler textHandler = new TextHandler();
		String dropBegin = textHandler.dropBegin("text", 2);
		assertEquals("xt", dropBegin);
	}

	@Test
	public void lengthBasedDropBeginDropFirst() throws Exception {
		TextHandler textHandler = new TextHandler();
		String dropBegin = textHandler.dropBegin("text", 1);
		assertEquals("ext", dropBegin);
	}

	@Test
	public void lengthBasedDropBeginDropAll() throws Exception {
		TextHandler textHandler = new TextHandler();
		String dropBegin = textHandler.dropBegin("text", 4);
		assertEquals("", dropBegin);
	}

	@Test
	public void lengthBasedDropBeginDropNothing() throws Exception {
		TextHandler textHandler = new TextHandler();
		String input = "text";
		String dropBegin = textHandler.dropBegin(input, 0);
		assertEquals(input, dropBegin);
	}

	// dropBegin(String, int) - exceptions

	@Test
	public void lengthBasedDropBeginLengthBigger() throws Exception {
		TextHandler textHandler = new TextHandler();
		String extracted = textHandler.dropBegin("text", 5);
		assertEquals("", extracted);
	}

	@Test(expected = IllegalArgumentException.class)
	public void lengthBasedDropBeginLengthNegative() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.dropBegin("text", -1);
	}

	@Test
	public void extractBegin() throws Exception {
		TextHandler textHandler = new TextHandler();
		String begin = textHandler.extractBegin("text", 3);
		assertEquals("tex", begin);
	}

	@Test
	public void extractBeginAll() throws Exception {
		TextHandler textHandler = new TextHandler();
		String input = "text";
		String begin = textHandler.extractBegin(input, 4);
		assertEquals(input, begin);
	}

	@Test
	public void extractBeginNone() throws Exception {
		TextHandler textHandler = new TextHandler();
		String begin = textHandler.extractBegin("text", 0);
		assertEquals("", begin);
	}

	@Test
	public void extractBeginTrims() throws Exception {
		TextHandler textHandler = new TextHandler();
		String begin = textHandler.extractBegin(" text", 2);
		assertEquals("te", begin);
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractBeginNull() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.extractBegin(null, 2);
	}

	@Test
	public void extractBeginLengthBigger() throws Exception {
		TextHandler textHandler = new TextHandler();
		String input = "text";
		int beginTextLength = input.length() + 1;		
		String begin = textHandler.extractBegin(input, beginTextLength);
		assertEquals(input, begin);
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractBeginLengthNegative() throws Exception {
		TextHandler textHandler = new TextHandler();
		String input = "text";
		String begin = textHandler.extractBegin(input, -1);
		assertEquals(input, begin);
	}

	@Test
	public void extractBeginPlusThreeDots() throws Exception {
		TextHandler textHandler = new TextHandler();
		String begin = textHandler.extractBeginPlusThreeDots("text", 3);
		assertEquals("tex...", begin);
	}

	@Test
	public void extractBeginPlusThreeDotsAll() throws Exception {
		TextHandler textHandler = new TextHandler();
		String begin = textHandler.extractBeginPlusThreeDots("text", 4);
		assertEquals("text...", begin);
	}

	// extractEnd

	@Test
	public void extractEnd() throws Exception {
		TextHandler textHandler = new TextHandler();
		String input = "text";
		String extracted = textHandler.extractEnd(input, 3);
		assertEquals("ext", extracted);
	}

	@Test
	public void extractEndAll() throws Exception {
		TextHandler textHandler = new TextHandler();
		String input = "text";
		String extracted = textHandler.extractEnd(input, 4);
		assertEquals(input, extracted);
	}

	@Test
	public void extractEndNone() throws Exception {
		TextHandler textHandler = new TextHandler();
		String extracted = textHandler.extractEnd("text", 0);
		assertEquals("", extracted);
	}

	@Test
	public void extractEndTrims() throws Exception {
		TextHandler textHandler = new TextHandler();
		String extracted = textHandler.extractEnd("text ", 2);
		assertEquals("xt", extracted);

	}

	@Test(expected = IllegalArgumentException.class)
	public void extractEndNull() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.extractEnd(null, 2);
	}

	@Test
	public void extractEndLengthBigger() throws Exception {
		TextHandler textHandler = new TextHandler();
		String input = "text";		
		int endTextLenghth = input.length() + 1;
		String extracted = textHandler.extractEnd(input, endTextLenghth);
		assertEquals(input, extracted);		
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractEndLengthNegative() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.extractEnd("text", -1);
	}

	// extractPackagePrefix
	@Test
	public void extractPackagePrefix() throws Exception {
		TextHandler textHandler = new TextHandler();
		String packagePrefix = textHandler.extractPackagePrefix(
				"java.lang.String", ".lang");
		assertEquals("java", packagePrefix);
	}

	@Test
	public void extractPackagePrefixAnchorAtTheBeginning() throws Exception {
		TextHandler textHandler = new TextHandler();
		String packagePrefix = textHandler.extractPackagePrefix(
				"java.lang.String", "java");
		assertEquals("", packagePrefix);
	}

	@Test(expected = StringIndexOutOfBoundsException.class)
	public void extractPackagePrefixNoAnchorInString() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.extractPackagePrefix("java.lang.String", "util");
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractPackagePrefixWithNullClassName() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.extractPackagePrefix(null, "util");
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractPackagePrefixWithNullAnchor() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.extractPackagePrefix("java.lang.String", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractPackagePrefixNotQualified() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.extractPackagePrefix("this is not qualified class name",
				"not");
	}

	// extractClassSimpleName

	@Test
	public void extractClassSimpleName() throws Exception {
		TextHandler textHandler = new TextHandler();
		String classSimpleName = textHandler
				.extractClassSimpleName("java.lang.String");
		assertEquals("String", classSimpleName);
	}

	@Test
	public void extractClassSimpleNameNoPackege() throws Exception {
		TextHandler textHandler = new TextHandler();
		String classSimpleName = textHandler.extractClassSimpleName("String");
		assertEquals("String", classSimpleName);
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractClassSimpleNameThrowsIAE() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.extractClassSimpleName(null);
	}

	// extractClassPackageName

	@Test
	public void extractClassPackageName() throws Exception {
		TextHandler textHandler = new TextHandler();
		String packageName = textHandler
				.extractClassPackageName("java.lang.String");
		assertEquals("java.lang.", packageName);
	}
	
	@Test
	public void extractClassPackageNameNoPackage() throws Exception {
		TextHandler textHandler = new TextHandler();
		String packageName = textHandler.extractClassPackageName("String");
		assertEquals("", packageName);
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractClassPackageNameThrowsIAE() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.extractClassPackageName(null);
	}

	// extractClassPackageNameWithSlashes

	@Test
	public void extractClassPackageNameWithSlashes() throws Exception {
		TextHandler textHandler = new TextHandler();
		String s = TextHandler.SEPARATOR;
		String packageName = textHandler
				.extractClassPackageNameWithSlashes("java.lang.String");
		assertEquals("java" + s + "lang" + s, packageName);
	}

	@Test
	public void extractClassPackageNameWithSlashesNoPackage() throws Exception {
		TextHandler textHandler = new TextHandler();
		String packageName = textHandler
				.extractClassPackageNameWithSlashes("String");
		assertEquals("", packageName);
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractClassPackageNameWithSlashesThrowsIAE() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.extractClassPackageNameWithSlashes(null);
	}

	// replaceDotWithSlash

	@Test
	public void replaceDotWithSlash() throws Exception {
		TextHandler textHandler = new TextHandler();
		String s = TextHandler.SEPARATOR;
		String replaced = textHandler.replaceDotWithSlash("org.modelibra");
		assertEquals("org" + s + "modelibra", replaced);
	}

	@Test
	public void replaceDotWithSlashNoDot() throws Exception {
		TextHandler textHandler = new TextHandler();
		String replaced = textHandler.replaceDotWithSlash("orgmodelibra");
		assertEquals("orgmodelibra", replaced);
	}

	@Test(expected = IllegalArgumentException.class)
	public void replaceDotWithSlashThrowsIAE() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.replaceDotWithSlash(null);
	}

	// toUpperCase

	@Test
	public void toUpperCaseFirst() throws Exception {
		TextHandler textHandler = new TextHandler();
		String replaced = textHandler.toUpperCase("modelibra", 0);
		assertEquals("Modelibra", replaced);
	}

	@Test
	public void toUpperCaseLast() throws Exception {
		TextHandler textHandler = new TextHandler();
		String replaced = textHandler.toUpperCase("modelibra", 8);
		assertEquals("modelibrA", replaced);
	}

	@Test(expected = IllegalArgumentException.class)
	public void toUpperCaseNull() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.toUpperCase(null, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void toUpperCaseIndexNegative() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.toUpperCase("modelibra", -1);
	}

	@Test
	public void toUpperCaseIndexBigger() throws Exception {
		TextHandler textHandler = new TextHandler();
		String input = "modelibra";
		String upperCase = textHandler.toUpperCase(input, 9);		
		assertEquals(input, upperCase);
	}

	// toLowerCase

	@Test
	public void toLowerCaseFirst() throws Exception {
		TextHandler textHandler = new TextHandler();
		String replaced = textHandler.toLowerCase("Modelibra", 0);
		assertEquals("modelibra", replaced);
	}

	@Test
	public void toLowerCaseLast() throws Exception {
		TextHandler textHandler = new TextHandler();
		String replaced = textHandler.toLowerCase("modelibrA", 8);
		assertEquals("modelibra", replaced);
	}

	@Test(expected = IllegalArgumentException.class)
	public void toLowerCaseNull() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.toLowerCase(null, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void toLowerCaseIndexNegative() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.toLowerCase("modelibra", -1);
	}

	@Test
	public void toLowerCaseIndexBigger() throws Exception {
		TextHandler textHandler = new TextHandler();
		String input = "modelibra";
		String lowerCase = textHandler.toLowerCase(input, 9);
		assertEquals(input, lowerCase);
	}

	// firstLetterToUpper

	@Test
	public void firstLetterToUpperWhenLower() throws Exception {
		TextHandler textHandler = new TextHandler();
		String replaced = textHandler.firstLetterToUpper("modelibra");
		assertEquals("Modelibra", replaced);
	}

	@Test
	public void firstLetterToUpperWhenUpper() throws Exception {
		TextHandler textHandler = new TextHandler();
		String replaced = textHandler.firstLetterToUpper("Modelibra");
		assertEquals("Modelibra", replaced);
	}

	@Test
	public void firstLetterToUpperWhenWhitespace() throws Exception {
		TextHandler textHandler = new TextHandler();
		String replaced = textHandler.firstLetterToUpper(" modelibra");
		assertEquals(" modelibra", replaced);
	}

	@Test(expected = IllegalArgumentException.class)
	public void firstLetterToUpperThrowsIAE() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.firstLetterToUpper(null);
	}

	// firstLetterToLower

	@Test
	public void firstLetterTotowerWhenLower() throws Exception {
		TextHandler textHandler = new TextHandler();
		String replaced = textHandler.firstLetterToLower("modelibra");
		assertEquals("modelibra", replaced);
	}

	@Test
	public void firstLetterToLowerWhenUpper() throws Exception {
		TextHandler textHandler = new TextHandler();
		String replaced = textHandler.firstLetterToLower("Modelibra");
		assertEquals("modelibra", replaced);
	}

	@Test
	public void firstLetterToLowerWhenWhitespace() throws Exception {
		TextHandler textHandler = new TextHandler();
		String replaced = textHandler.firstLetterToLower(" modelibra");
		assertEquals(" modelibra", replaced);
	}

	@Test(expected = IllegalArgumentException.class)
	public void firstLetterToLowerThrowsIAE() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.firstLetterToLower(null);
	}

	// allLettersToLower

	@Test
	public void allLettersToLower() throws Exception {
		TextHandler textHandler = new TextHandler();
		String toLower = textHandler.allLettersToLower("MODELIBRA");
		assertEquals("modelibra", toLower);
	}

	@Test(expected = IllegalArgumentException.class)
	public void allLettersToLowerThrowsIAE() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.allLettersToLower(null);
	}

	// TODO : tests for putInEnglishPlural

	// TODO : tests for stripNonValidXMLCharacters

	// removeFirstAndLastCharacter

	@Test
	public void removeFirstAndLastCharacter() throws Exception {
		TextHandler textHandler = new TextHandler();
		String result = textHandler.removeFirstAndLastCharacter("modelibra");
		assertEquals("odelibr", result);
	}

	@Test
	public void extractTextWithoutFirstAndLastCharactersThree()
			throws Exception {
		TextHandler textHandler = new TextHandler();
		String result = textHandler.removeFirstAndLastCharacter("mod");
		assertEquals("o", result);
	}

	@Test
	public void extractTextWithoutFirstAndLastCharactersTwo() throws Exception {
		TextHandler textHandler = new TextHandler();
		String result = textHandler.removeFirstAndLastCharacter("mo");
		assertEquals("", result);
	}

	@Test
	public void extractTextWithoutFirstAndLastCharactersEmpty()
			throws Exception {
		TextHandler textHandler = new TextHandler();
		String result = textHandler.removeFirstAndLastCharacter("");
		assertEquals("", result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractTextWithoutFirstAndLastCharactersNull() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.removeFirstAndLastCharacter(null);
	}

	// extractSeparatedSubtrings

	@Test
	public void extractSeparatedSubtrings() throws Exception {
		TextHandler textHandler = new TextHandler();
		List<String> separatedSubtrings = textHandler
				.extractSeparatedSubtrings("[1,2,3]", ",");
		List<String> expected = Arrays.asList("1", "2", "3");
		assertEquals(expected, separatedSubtrings);
	}

	@Test
	public void extractSeparatedSubtringsNoBrackets() throws Exception {
		TextHandler textHandler = new TextHandler();
		List<String> separatedSubtrings = textHandler
				.extractSeparatedSubtrings(" 1,2,3 ", ",");
		List<String> expected = Arrays.asList("1", "2", "3");
		assertEquals(expected, separatedSubtrings);
	}

	@Test
	public void extractSeparatedSubtringsMixedBrackets() throws Exception {
		TextHandler textHandler = new TextHandler();
		List<String> separatedSubtrings = textHandler
				.extractSeparatedSubtrings("$1,2,3}", ",");
		List<String> expected = Arrays.asList("1", "2", "3");
		assertEquals(expected, separatedSubtrings);
	}

	@Test
	public void extractSeparatedSubtringsOneElement() throws Exception {
		TextHandler textHandler = new TextHandler();
		List<String> separatedSubtrings = textHandler
				.extractSeparatedSubtrings("[2]", ",");
		List<String> expected = Arrays.asList("2");
		assertEquals(expected, separatedSubtrings);
	}

	@Test
	public void extractSeparatedSubtringsOneElementPlusSeparator()
			throws Exception {
		TextHandler textHandler = new TextHandler();
		List<String> separatedSubtrings = textHandler
				.extractSeparatedSubtrings("[2,]", ",");
		List<String> expected = Arrays.asList("2");
		assertEquals(expected, separatedSubtrings);
	}

	// This test demonstrates that currently strings that represent regex
	// metacharacters or escape sequences cannot be used as a separator
	// argument. Particularly, in this test, "." is used as separator. However
	// in regex "." matches a single character, without caring what that
	// character is (only exception are newline characters), and since
	// extractSeparatedSubtrings method uses String.split(String) method
	// internally, where string argument is regular expression, method will
	// return empty array. It would be the same as separating "sssss" by "s".
	// There are two solutions here. Either leave it as it is and note in method
	// javadoc that separator is regular expression or use
	// Pattern.quote(separator) internally to ignore regular expressions meta
	// characters and escape sequences.
	@Ignore
	@Test
	public void extractSeparatedSubtringsRegex() throws Exception {
		TextHandler textHandler = new TextHandler();
		List<String> separatedSubtrings = textHandler
				.extractSeparatedSubtrings(" 1.2.3 ", ".");
		List<String> expected = Arrays.asList("1", "2", "3");
		assertEquals(expected, separatedSubtrings);
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractSeparatedSubtringsNullFirst() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.extractSeparatedSubtrings(null, "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractSeparatedSubtringsNullSecond() throws Exception {
		TextHandler textHandler = new TextHandler();
		textHandler.extractSeparatedSubtrings("[2,3]", null);
	}
}
