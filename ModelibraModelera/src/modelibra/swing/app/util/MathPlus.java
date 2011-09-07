package modelibra.swing.app.util;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2001-05-07
 */
public class MathPlus {

	public static int mod(int f1, int f2) {
		int q = (int) f1 / f2;
		int result = f1 - q * f2;
		return result;
	}

}
