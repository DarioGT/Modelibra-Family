/**
 * 
 */
package sales.wicket.cheesestore.discounts;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

public class PercentageConverter implements IConverter {

	public Object convertToObject(String value, Locale locale) {
		try {
			return getNumberFormat(locale).parseObject(value);
		} catch (ParseException e) {
			throw new ConversionException(e);
		}
	}

	public String convertToString(Object value, Locale locale) {
		return getNumberFormat(locale).format((Double) value);
	}

	private NumberFormat getNumberFormat(Locale locale) {
		DecimalFormat fmt = new DecimalFormat("##");
		fmt.setMultiplier(100);
		fmt.setMaximumFractionDigits(2);
		fmt.setMinimumFractionDigits(0);
		return fmt;
	}
	
}
