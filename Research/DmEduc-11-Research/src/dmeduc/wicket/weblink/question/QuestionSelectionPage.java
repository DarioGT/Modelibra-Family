package dmeduc.wicket.weblink.question;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.PageLink;
import org.modelibra.wicket.concept.EntityDisplayTablePanel;
import org.modelibra.wicket.concept.selection.PropertySelectorPanel;
import org.modelibra.wicket.container.DmDisplayPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Question Selection page.
 * 
 * @author Vedad Kirlic
 * @version 2008-01-19
 */
@SuppressWarnings("serial")
public class QuestionSelectionPage extends DmDisplayPage {

	/**
	 * Constructs the Question Selection page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public QuestionSelectionPage(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		final ViewModel questionSelectionPageModel = new ViewModel();
		questionSelectionPageModel.copyPropertiesFrom(viewModel);

		// context view
		View contextView = new View();
		contextView.copyPropertiesFrom(view);
		contextView.setPage(this);

		// selection panel
		View questionSelectionPanelView = new View();
		questionSelectionPanelView.copyPropertiesFrom(view);
		questionSelectionPanelView.setContextView(view);
		questionSelectionPanelView.setWicketId("selectionSection");
		PropertySelectorPanel selectionPanel = new PropertySelectorPanel(
				questionSelectionPageModel, questionSelectionPanelView) {
			@Override
			protected Page getNewPageInstance(ViewModel viewModel, View view) {
				return new QuestionSelectionPage(viewModel, view);
			}
		};
		add(selectionPanel);

		// Questions panel
		View entityDisplayTablePanelView = new View();
		entityDisplayTablePanelView.copyPropertiesFrom(view);
		entityDisplayTablePanelView.setWicketId("questions");
		entityDisplayTablePanelView.setContextView(contextView);
		entityDisplayTablePanelView.setPage(this);
		entityDisplayTablePanelView.setUpdate(false);

		EntityDisplayTablePanel questionsPanel = new EntityDisplayTablePanel(
				questionSelectionPageModel, entityDisplayTablePanelView);
		add(questionsPanel);
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
				return new QuestionSelectionPage(viewModel, view);
			}

			public Class<? extends Page> getPageIdentity() {
				return QuestionSelectionPage.class;
			}
		});
		return link;
	}

}
