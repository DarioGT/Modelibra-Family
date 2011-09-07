package modelibra.wicket.component.view;

public class ChildConceptView extends ConceptView {

	/**
	 * Constructs a child concept view.
	 * 
	 * @param parentConceptView
	 *            parent concept view
	 * @param childConceptName
	 *            child concept name
	 */
	public ChildConceptView(final ConceptView parentConceptView,
			final String childConceptName) {
		super(parentConceptView.getComponentView(), childConceptName);
		setComponentView(parentConceptView.getComponentView());
		setParentConceptView(parentConceptView);
		parentConceptView.addChildConceptView(this);
	}

	/**
	 * Constructs a child concept view.
	 * 
	 * @param parentConceptView
	 *            parent concept view
	 * @param childNeighborName
	 *            child neighbor name
	 * @param childConceptName
	 *            child concept name
	 */
	public ChildConceptView(final ConceptView parentConceptView,
			final String childNeighborName, final String childConceptName) {
		super(parentConceptView.getComponentView(), childConceptName);
		setComponentView(parentConceptView.getComponentView());
		setParentConceptView(parentConceptView);
		parentConceptView.addChildConceptView(this);
		setNeighborName(childNeighborName);
	}

}
