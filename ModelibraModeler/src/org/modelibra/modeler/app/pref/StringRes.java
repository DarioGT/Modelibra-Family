package org.modelibra.modeler.app.pref;

/**
 *
 * @author Dzenan Ridjanovic
 * @version 2001-05-07
 */
import java.io.IOException;
import java.util.PropertyResourceBundle;

public class StringRes extends PropertyResourceBundle {

	StringRes() throws IOException {
		super(StringRes.class.getResourceAsStream("StringRes.properties"));
	}

}
