package org.modelibra.modeler.app.pref;

/**
 *
 * @author Dzenan Ridjanovic
 * @version 2001-05-07
 */
import java.io.IOException;
import java.util.PropertyResourceBundle;

public class StringRes_en extends PropertyResourceBundle {

	StringRes_en() throws IOException {
		super(StringRes_en.class.getResourceAsStream("StringRes_en.properties"));
	}

}