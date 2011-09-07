package org.modelibra.wicket.concept.selection;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.PageLink;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmDisplayPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity Selection page. Uses PropertySelectorPanel for selection and
 * EntityDisplayTablePanel for display.
 * 
 * @author Vedad Kirlic
 * @version 2008-03-16
 */
@SuppressWarnings("serial")
public class EntitySelectionPage extends DmDisplayPage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs the Entity Selection page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntitySelectionPage(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		final ViewModel entitySelectionPageModel = new ViewModel();
		entitySelectionPageModel.copyPropertiesFrom(viewModel);

		// context view
		View contextView = new View();
		contextView.copyPropertiesFrom(view);
		contextView.setPage(this);

		// selection panel
		View entitySelectionPanelView = new View();
		entitySelectionPanelView.copyPropertiesFrom(view);
		entitySelectionPanelView.setContextView(view);
		entitySelectionPanelView.setWicketId("selectionSection");

		add(new PropertySelectorPanel(entitySelectionPageModel,
				entitySelectionPanelView) {

			private static final long serialVersionUID = 1L;

			@Override
			protected Page getNewPageInstance(ViewModel viewModel, View view) {
				return new EntitySelectionPage(viewModel, view);
			}
		});

		// Display panel
		View entityDisplayTablePanelView = new View();
		entityDisplayTablePanelView.copyPropertiesFrom(view);
		entityDisplayTablePanelView.setWicketId("displaySection");
		entityDisplayTablePanelView.setContextView(contextView);
		entityDisplayTablePanelView.setPage(this);
		entityDisplayTablePanelView.setUpdate(false);

		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();
		add(app.getViewMeta(modelCode).getPanel("EntityDisplayTablePanel",
				entitySelectionPageModel, entityDisplayTablePanelView));
	}

	/**
	 * Constructs a link to this page.
	 * 
	 * @param linkId
	 *            link Wicket id
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public static PageLink link(final String linkId, final ViewModel viewModel,
			final View view) {
		PageLink link = new PageLink(linkId, new IPageLink() {
			public Page getPage() {
				return new EntitySelectionPage(viewModel, view);
			}

			public Class<? extends Page> getPageIdentity() {
				return EntitySelectionPage.class;
			}
		});
		return link;
	}

}
