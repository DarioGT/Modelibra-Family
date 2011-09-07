package org.modelibra.wicket.view.test;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.modelibra.wicket.view.ViewMetaTest;

/**
 * Base ListView used for testing ViewMeta. ViewMeta looks for components in
 * different places, and have methods to create instances of such components. In
 * order to test those methods, with real components, ViewModel and View
 * arguments should be created and populated specifically for each component,
 * which would clutter test methods (we do not test real component
 * instantiation, but the ViewMeta ability to get the component from the right
 * place). Provides no argument constructor, and implements populateItem,
 * convenient for test list views subclassing from it.
 * 
 * @see {@link ViewMetaTest}
 * 
 * @author Vedad Kirlic
 * 
 */
@SuppressWarnings("serial")
public class TestListView extends ListView {

	public TestListView() {
		// it will not be rendered
		super("doesntMatter");
	}

	@Override
	protected void populateItem(ListItem arg0) {
		// it will not be rendered
	}

}
