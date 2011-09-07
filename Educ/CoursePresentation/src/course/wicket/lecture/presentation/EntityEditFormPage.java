package course.wicket.lecture.presentation;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.PageLink;
import org.modelibra.wicket.container.DmUpdatePage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import course.wicket.lecture.presentation.editor.AjaxPresentationEditorPanel;

/**
 * Specific EntityEditFormPage to replace generic EntityEditFormPage for
 * presentation concept entities.
 * 
 * @author Vedad Kirlic
 * @version 2007-12-28
 */
public class EntityEditFormPage extends DmUpdatePage {

	static final long serialVersionUID = 1L;

	public EntityEditFormPage(ViewModel viewModel, View view) {
		super(viewModel, view);
		view.setWicketId("presentationEditor");
		add(new AjaxPresentationEditorPanel(viewModel, view));
	}

	/**
	 * Constructs a page of this class at the time of the link click.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 * 
	 */
	public static PageLink link(final ViewModel viewModel, final View view) {
		PageLink link = new PageLink(view.getWicketId(), new IPageLink() {
			static final long serialVersionUID = 1L;

			public Page getPage() {
				return new EntityEditFormPage(viewModel, view);
			}

			public Class<? extends Page> getPageIdentity() {
				return EntityEditFormPage.class;
			}
		});
		return link;
	}

}
