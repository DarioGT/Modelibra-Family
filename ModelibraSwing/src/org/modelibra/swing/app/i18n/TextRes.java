package org.modelibra.swing.app.i18n;

import java.io.IOException;
import java.util.PropertyResourceBundle;

public class TextRes extends PropertyResourceBundle {

	public TextRes(String resource) throws IOException {
		super(TextRes.class.getResourceAsStream(resource));
	}

}