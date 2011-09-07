package modelibra.wicket.component.view;

public class LookupConceptView extends ConceptView {

	/**
	 * Constructs a lookup concept view.
	 * 
	 * @param componentView
	 *            component view
	 * @param lookupConceptName
	 *            lookup concept name
	 */
	public LookupConceptView(final ComponentView componentView,
			final String lookupConceptName) {
		super(componentView, lookupConceptName);
		setLookupConcept(true);
		componentView.addLookupConceptView(this);
	}

}
