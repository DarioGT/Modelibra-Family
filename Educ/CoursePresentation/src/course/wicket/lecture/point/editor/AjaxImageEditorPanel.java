package course.wicket.lecture.point.editor;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.ViewModel;

import course.lecture.point.Point;

/**
 * AjaxImageEditorPanel used for editing image point. Contains ContextImage for
 * image and edit link that opens modal window to edit text and
 * image of the link point.
 * 
 * @author Vedad Kirlic
 * @version 2007-12-29
 */
public class AjaxImageEditorPanel extends DmPanel {

	private static final long serialVersionUID = 1L;

	public AjaxImageEditorPanel(final String id, final ViewModel viewModel) {
		super(id);

		final Point point = (Point) viewModel.getEntity();

		// image
		final ContextImage image = new ContextImage("image", new PropertyModel(
				point, "image"));

		add(image);
		image.setOutputMarkupId(true);

		// modal window to edit image point
		final ModalWindow modal;
		add(modal = new ModalWindow("modal"));

		ViewModel modalViewModel = new ViewModel();
		modalViewModel.copyPropertiesFrom(viewModel);
		modalViewModel.setEntity(point);

		// set AjaxImageModalEditorPanel as content for modal window
		modal.setContent(new AjaxImageModalEditorPanel(modal.getContentId(),
				modalViewModel, modal));

		modal.setResizable(false);
		modal.setInitialWidth(70);
		modal.setWidthUnit("em");

		// update image component to reflect changes made in modal window when
		// closed
		modal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClose(AjaxRequestTarget target) {
				target.addComponent(image);
			}
		});

		// edit link that opens modal window - needed when there is no image
		add(new AjaxLink("edit") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				modal.show(target);
			}
		});

		// same as edit link but is triggered by onclick event on this
		// component, thus image too
		add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				modal.show(target);
			}
		});
	}

}
