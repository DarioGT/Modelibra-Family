package course.wicket.lecture.presentation.editor;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;
import org.modelibra.wicket.widget.AjaxPropertyTextAreaEditableLabel;

import course.lecture.presentation.Presentation;
import course.lecture.presentation.Presentations;
import course.lecture.slide.Slides;
import course.wicket.lecture.slide.editor.AjaxSlideEditorPanel;
import course.wicket.lecture.slide.editor.AjaxSlideListPanel;

/**
 * Ajax Presentation Editor panel to edit presentation required properties,
 * slides and slide points, all within the context of the editor panel.
 * 
 * @author Vedad Kirlic
 * @version 2007-12-29
 */
public class AjaxPresentationEditorPanel extends DmPanel {

	private static final long serialVersionUID = 1L;

	public AjaxPresentationEditorPanel(final ViewModel viewModel,
			final View view) {
		super(viewModel, view);
		// to enable ajax update
		setOutputMarkupId(true);

		final Presentation presentation = (Presentation) viewModel.getEntity();
		final Presentations presentations = (Presentations) viewModel
				.getEntities();

		// feedback panel
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setOutputMarkupId(true);
		add(feedbackPanel);

		final Presentation presentationCopy = presentation.copy();

		// TODO following code is quick fix for this app until AjaxEditableLabel
		// is fixed in wicket. Should be replaced with commented code bellow
		// once AjaxEditableLabel is fixed

		View codeView = new View();
		codeView.setWicketId("code");
		ViewModel codeViewModel = new ViewModel();
		codeViewModel.setEntity(presentationCopy);
		codeViewModel.setPropertyCode("code");

		add(new AjaxPropertyTextAreaEditableLabel(codeViewModel, codeView) {
			private static final long serialVersionUID = 1L;

			{
				setCols(24);
				setRows(1);
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				presentations.getErrors().empty();
				if (presentations.update(presentation, presentationCopy)) {
					super.onSubmit(target);
				} else {
					addErrorsByKeys(presentations);
				}
				target.addComponent(feedbackPanel);
			}

		});

		View titleView = new View();
		titleView.setWicketId("title");
		ViewModel titleViewModel = new ViewModel();
		titleViewModel.setEntity(presentationCopy);
		titleViewModel.setPropertyCode("title");

		add(new AjaxPropertyTextAreaEditableLabel(titleViewModel, titleView) {
			private static final long serialVersionUID = 1L;

			{
				setCols(24);
				setRows(1);
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				presentations.getErrors().empty();
				if (presentations.update(presentation, presentationCopy)) {
					super.onSubmit(target);
				} else {
					addErrorsByKeys(presentations);
				}
				target.addComponent(feedbackPanel);
			}

		});

		View authorView = new View();
		authorView.setWicketId("author");
		ViewModel authorViewModel = new ViewModel();
		authorViewModel.setEntity(presentationCopy);
		authorViewModel.setPropertyCode("author");

		add(new AjaxPropertyTextAreaEditableLabel(authorViewModel, authorView) {
			private static final long serialVersionUID = 1L;

			{
				setCols(24);
				setRows(1);
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				presentations.getErrors().empty();
				if (presentations.update(presentation, presentationCopy)) {
					super.onSubmit(target);
				} else {
					addErrorsByKeys(presentations);
				}
				target.addComponent(feedbackPanel);
			}

		});

		// // editable code label
		// add(new AjaxEditableLabel("code", new PropertyModel(presentationCopy,
		// "code")) {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// protected void onSubmit(AjaxRequestTarget target) {
		// presentations.getErrors().empty();
		// if (presentations.update(presentation, presentationCopy)) {
		// super.onSubmit(target);
		// } else {
		// addErrorsByKeys(presentations);
		// }
		// target.addComponent(feedbackPanel);
		// }
		// });
		//
		// // editable title label
		// add(new AjaxEditableLabel("title", new
		// PropertyModel(presentationCopy,
		// "title")) {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// protected void onSubmit(AjaxRequestTarget target) {
		// presentations.getErrors().empty();
		// if (presentations.update(presentation, presentationCopy)) {
		// super.onSubmit(target);
		// } else {
		// addErrorsByKeys(presentations);
		// }
		// target.addComponent(feedbackPanel);
		// }
		// });
		//
		// // editable author label
		// add(new AjaxEditableLabel("author", new
		// PropertyModel(presentationCopy,
		// "author")) {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// protected void onSubmit(AjaxRequestTarget target) {
		// presentations.getErrors().empty();
		// if (presentations.update(presentation, presentationCopy)) {
		// super.onSubmit(target);
		// } else {
		// addErrorsByKeys(presentations);
		// }
		// target.addComponent(feedbackPanel);
		// }
		// });

		// Presentation slides.
		View presentationSlidesView = new View();
		presentationSlidesView.copyPropertiesFrom(view);
		presentationSlidesView.setWicketId("presentationSlides");

		ViewModel presentationSlidesViewModel = new ViewModel();
		presentationSlidesViewModel.copyPropertiesFrom(viewModel);

		add(new AjaxSlideListPanel(presentationSlidesViewModel,
				presentationSlidesView));

		// Slide editor panel is displayed if there is at least one presentation
		// slide.
		Slides presentationSlides = presentation.getSlides();
		if (presentationSlides.isEmpty()) {
			add(new EmptyPanel("slideEditor").setOutputMarkupId(true));
		} else {
			add(new AjaxSlideEditorPanel("slideEditor", presentationSlides
					.first(), presentationSlides));
		}
	}

}
