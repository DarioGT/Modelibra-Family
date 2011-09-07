package modelibra.wicket.component.view;

public class RootConceptView extends ConceptView {

	/**
	 * Constructs a root concept view.
	 * 
	 * @param componentView
	 *            component view
	 * @param rootConceptName
	 *            root concept name
	 */
	public RootConceptView(final ComponentView componentView,
			final String rootConceptName) {
		super(componentView, rootConceptName);
		componentView.setRootConceptView(this);
	}

}
