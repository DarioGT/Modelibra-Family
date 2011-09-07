package course.wicket.lecture.point.editor;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.ViewModel;

import course.lecture.point.Point;

/**
 * AjaxLinkEditorPanel used for editing link point. Contains ExternalLink for
 * link and edit link that opens modal window to edit text and url of the link
 * point.
 * 
 * @author Vedad Kirlic
 * @version 2007-12-29
 */
public class AjaxLinkEditorPanel extends DmPanel {

	private static final long serialVersionUID = 1L;

	public AjaxLinkEditorPanel(final String id, final ViewModel viewModel) {
		super(id);

		final Point point = (Point) viewModel.getEntity();

		// link
		final ExternalLink link = new ExternalLink("link", new PropertyModel(
				point, "url"), new PropertyModel(point, "text"));
		add(link);
		link.setOutputMarkupId(true);

		// modal window to edit link (url + text)
		final ModalWindow modal;
		add(modal = new ModalWindow("modal"));

		ViewModel modalViewModel = new ViewModel();
		modalViewModel.copyPropertiesFrom(viewModel);
		modalViewModel.setEntity(point);

		// set AjaxLinkModalEditorPanel as content for modal window
		modal.setContent(new AjaxLinkModalEditorPanel(modal.getContentId(),
				modalViewModel, modal));

		modal.setResizable(false);
		modal.setInitialWidth(70);
		modal.setWidthUnit("em");

		// update link component to reflect changes made in modal window when
		// closed
		modal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClose(AjaxRequestTarget target) {
				target.addComponent(link);
			}
		});

		// edit link that opens modal window
		add(new AjaxLink("edit") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				modal.show(target);
			}
		});
	}

}
