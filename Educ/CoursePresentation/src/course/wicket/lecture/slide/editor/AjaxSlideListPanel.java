package course.wicket.lecture.slide.editor;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.wicket.container.DmListView;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import course.lecture.Lecture;
import course.lecture.presentation.Presentation;
import course.lecture.slide.Slide;
import course.lecture.slide.Slides;

/**
 * Ajax Presentation Slides panel to list presentation slides in order to select
 * a slide to edit it or to add a new one.
 * 
 * @author Vedad Kirlic
 * @version 2007-12-29
 */
public class AjaxSlideListPanel extends DmPanel {

	private static final long serialVersionUID = 1L;

	public AjaxSlideListPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		// to enable ajax update
		setOutputMarkupId(true);

		final Presentation presentation = (Presentation) viewModel.getEntity();
		final Lecture lecture = (Lecture) viewModel.getModel();

		final Slides presentationSlides = presentation.getSlides();

		// presentation slides
		View presentationSlidesView = new View();
		presentationSlidesView.setWicketId("presentationSlideListView");

		ViewModel presentationSlidesViewModel = new ViewModel();
		presentationSlidesViewModel.setEntities(presentationSlides);

		add(new DmListView(presentationSlidesViewModel, presentationSlidesView) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem item) {
				final Slide slide = (Slide) item.getModelObject();

				// edit slide link
				AjaxLink editSlideLink = new AjaxLink("editSlide") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						// replace the current slide editor panel with one for
						// this item's slide
						replaceSlideEditor(target, slide);
					}
				};
				editSlideLink.add(new Label("slideTitle", new PropertyModel(
						slide, "title")));
				item.add(editSlideLink);

			}

			private void replaceSlideEditor(AjaxRequestTarget target,
					Slide selectedSlide) {
				Component currentSlideEditor = AjaxSlideListPanel.this
						.getParent().get("slideEditor");
				AjaxSlideEditorPanel newSlideEditor = new AjaxSlideEditorPanel(
						"slideEditor", selectedSlide, presentationSlides);
				newSlideEditor.setOutputMarkupId(true);
				currentSlideEditor.replaceWith(newSlideEditor);

				target.addComponent(newSlideEditor);
			}
		});

		// add slide link
		add(new AjaxLink("addSlide") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				Slide slide = new Slide(lecture);
				// set title to pass validation since we are adding to model
				// entities with pre and post set to true
				slide.setTitle("Slide " + (presentationSlides.size() + 1));
				presentationSlides.add(slide);
				// update this panel to reflect new slide in presentation slides
				// list
				Component currentSlideEditor = AjaxSlideListPanel.this
						.getParent().get("slideEditor");
				AjaxSlideEditorPanel newSlideEditor = new AjaxSlideEditorPanel(
						"slideEditor", slide, presentationSlides);
				// replace the current slide editor with one for the new slide
				currentSlideEditor.replaceWith(newSlideEditor);

				// update parent (PresentationEditorPanel) to reflect changes
				// both for the new slide editor and the list of presentation
				// slides
				target.addComponent(AjaxSlideListPanel.this.getParent());
			}
		});
	}

}
