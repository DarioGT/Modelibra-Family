package course.wicket.lecture.slide.editor;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;
import org.modelibra.wicket.widget.AjaxPropertyTextAreaEditableLabel;

import course.lecture.Lecture;
import course.lecture.point.Point;
import course.lecture.point.Points;
import course.lecture.slide.Slide;
import course.lecture.slide.Slides;
import course.wicket.lecture.point.editor.AjaxPointEditorListPanel;

/**
 * Ajax Slide Editor panel to edit a presentation slide.
 * 
 * @author Vedad Kirlic
 * @version 2008-02-07
 */
public class AjaxSlideEditorPanel extends DmPanel {

	private static final long serialVersionUID = 1L;

	private static final List<String> PROPERTY_TYPES = Arrays
			.asList(new String[] { "text", "code", "explanation", "image",
					"link" });

	private AjaxPointEditorListPanel slidePointsPanel;

	private String selectedType = "text";

	public AjaxSlideEditorPanel(final String id, final Slide slide,
			final Slides slides) {
		super(id);
		setOutputMarkupId(true);

		// set copied slide as model, then update original slide in onSubmit
		// listener methods
		final Slide slideCopy = slide.copy();
		setModel(new CompoundPropertyModel(slideCopy));

		// feedback panel
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		feedbackPanel.setOutputMarkupId(true);

		// remove link
		add(new AjaxLink("removeSlide") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				// get prior slide for new editor panel
				Slide priorSlide = slides.prior(slide);

				slides.remove(slide);

				Panel newSlideEditorPanel;
				if (slides.isEmpty()) {
					newSlideEditorPanel = new EmptyPanel(
							AjaxSlideEditorPanel.this.getId());
					newSlideEditorPanel.setOutputMarkupId(true);
				} else {
					// if removed slide was the first slide, set the prior slide
					// to be the first one
					if (priorSlide == null) {
						priorSlide = slides.first();
					}
					newSlideEditorPanel = new AjaxSlideEditorPanel(
							AjaxSlideEditorPanel.this.getId(), priorSlide,
							slides);
				}
				// replace the current slide editor panel (this) with the new one
				AjaxSlideEditorPanel.this.replaceWith(newSlideEditorPanel);
				// update the parent of newSlideEditorPanel to reflect changes in both
				// the slide editor panel and presentation slides list in sidebar
				target.addComponent(newSlideEditorPanel.getParent());
			}
		});

		// TODO following code is quick fix for this app until AjaxEditableLabel
		// is fixed in wicket. Should be replaced with commented code bellow
		// once AjaxEditableLabel is fixed

		View titleView = new View();
		titleView.setWicketId("title");
		ViewModel titleViewModel = new ViewModel();
		titleViewModel.setEntity(slideCopy);
		titleViewModel.setPropertyCode("title");

		add(new AjaxPropertyTextAreaEditableLabel(titleViewModel, titleView) {
			private static final long serialVersionUID = 1L;

			{
				setCols(64);
				setRows(1);
			}			
			
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				slides.getErrors().empty();
				if (slides.update(slide, slideCopy)) {
					super.onSubmit(target);
					// we need to update slide title in presentation slides list
					target.addComponent(AjaxSlideEditorPanel.this.getParent());
				} else {
					addErrorsByKeys(slides);
				}
				target.addComponent(feedbackPanel);
			}

		});
		
		View subTitleView = new View();
		subTitleView.setWicketId("subTitle");
		ViewModel subTitleViewModel = new ViewModel();
		subTitleViewModel.setEntity(slideCopy);
		subTitleViewModel.setPropertyCode("subTitle");

		add(new AjaxPropertyTextAreaEditableLabel(subTitleViewModel, subTitleView) {
			private static final long serialVersionUID = 1L;

			{
				setCols(64);
				setRows(1);
			}			
			
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				slides.getErrors().empty();
				if (slides.update(slide, slideCopy)) {
					super.onSubmit(target);
				} else {
					addErrorsByKeys(slides);
				}
				target.addComponent(feedbackPanel);
			}

		});
		

		// // slide title editable label
		// AjaxEditableLabel title = new AjaxEditableLabel("title") {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// protected void onSubmit(AjaxRequestTarget target) {
		// slides.getErrors().empty();
		// if (slides.update(slide, slideCopy)) {
		// super.onSubmit(target);
		// // we need to update slide title in presentation slides list
		// target.addComponent(AjaxSlideEditorPanel.this.getParent());
		// } else {
		// addErrorsByKeys(slides);
		// }
		// target.addComponent(feedbackPanel);
		// }
		// };
		// add(title);
		//
		// // slide subTitle editable label
		// add(new AjaxEditableLabel("subTitle") {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// protected void onSubmit(AjaxRequestTarget target) {
		// slides.getErrors().empty();
		// if (slides.update(slide, slideCopy)) {
		// super.onSubmit(target);
		// } else {
		// addErrorsByKeys(slides);
		// }
		//				target.addComponent(feedbackPanel);
		//			}
		//		});

		final Points points = slide.getPoints();
		final Lecture lecture = (Lecture) points.getModel();

		// point type choice
		DropDownChoice addPointTypeDDC = new DropDownChoice("addPointTypeDDC",
				new PropertyModel(this, "selectedType"), PROPERTY_TYPES);

		// onchange behavior to add point of selected type, when the selection
		// is changed
		addPointTypeDDC.add(new OnChangeAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				selectedType = (String) getComponent().getModelObject();
				Point point = new Point(lecture);
				point.setType(selectedType);
				point.setText("new " + selectedType + " point");
				points.add(point);
				target.addComponent(slidePointsPanel);
			}
		});
		add(addPointTypeDDC.setNullValid(false));

		// add point link; used if one wants to add more points of the
		// same (selected) type since with addPointTypeDDC a point is added only
		// if the selection is changed
		add(new AjaxLink("addPoint") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				Point point = new Point(lecture);
				point.setType(selectedType);
				point.setText("new " + selectedType + " point");
				points.add(point);
				target.addComponent(slidePointsPanel);
			}
		});

		// slide points
		slidePointsPanel = new AjaxPointEditorListPanel("slidePoints", slide
				.getPoints());
		add(slidePointsPanel);
	}

}
