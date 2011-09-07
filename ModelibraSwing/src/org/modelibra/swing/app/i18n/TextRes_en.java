package org.modelibra.swing.app.i18n;

import java.io.IOException;
import java.util.PropertyResourceBundle;

public class TextRes_en extends PropertyResourceBundle {

	public TextRes_en(String resource) throws IOException {
		super(TextRes_en.class.getResourceAsStream(resource));
	}

}