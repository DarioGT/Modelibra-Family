package modelibra.swing.app.config;

import java.io.IOException;
import java.util.PropertyResourceBundle;

public class TextRes extends PropertyResourceBundle {

	TextRes() throws IOException {
		super(TextRes.class.getResourceAsStream("TextRes.properties"));
	}

}