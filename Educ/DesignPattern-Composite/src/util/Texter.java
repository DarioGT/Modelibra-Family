package util;

public class Texter {
	
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
	
	public String firstLetterToUpper(String string) {
		return toUpperCase(string, 0);
	}

}
