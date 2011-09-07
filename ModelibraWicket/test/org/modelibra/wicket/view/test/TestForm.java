package org.modelibra.wicket.view.test;

import org.apache.wicket.markup.html.form.Form;
import org.modelibra.wicket.view.ViewMetaTest;

/**
 * Base Form used for testing ViewMeta. ViewMeta looks for components in
 * different places, and have methods to create instances of such components. In
 * order to test those methods, with real components, ViewModel and View
 * arguments should be created and populated specifically for each component,
 * which would clutter test methods (we do not test real component
 * instantiation, but the ViewMeta ability to get the component from the right
 * place). Provides no argument constructor, convenient for test forms
 * subclassing from it.
 * 
 * @see {@link ViewMetaTest}
 * 
 * @author Vedad Kirlic
 * 
 */
@SuppressWarnings("serial")
public class TestForm extends Form {

	public TestForm() {
		// it will not be rendered
		super("doesntMatter");
	}
}