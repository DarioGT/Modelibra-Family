package course.wicket.lecture.point.editor;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.container.DmListView;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;
import org.modelibra.wicket.widget.AjaxCodeEditorPanel;
import org.modelibra.wicket.widget.AjaxPropertyEditableMultilineLabel;
import org.modelibra.wicket.widget.AjaxPropertyTextAreaEditableLabel;

import course.lecture.point.Point;
import course.lecture.point.Points;

/**
 * AjaxPointEditorListPanel. List of point editors, one for each point and the
 * point type.
 * 
 * @author Vedad Kirlic
 * @version 2008-01-19
 */
public class AjaxPointEditorListPanel extends DmPanel {

	private static final long serialVersionUID = 1L;

	public AjaxPointEditorListPanel(final String id, final Points points) {
		super(id);
		setOutputMarkupId(true);

		View slidePointView = new View();
		slidePointView.setWicketId("slidePointEditorListView");

		ViewModel slidePointViewModel = new ViewModel();
		slidePointViewModel.setEntities(points);

		// slide points
		add(new DmListView(slidePointViewModel, slidePointView) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem item) {
				final Point point = (Point) item.getModelObject();
				String pointType = point.getType();
				final Point pointCopy = point.copy();
				Panel pointEditorPanel;

				// feedback
				final FeedbackPanel feedbackPanel = new FeedbackPanel(
						"feedback");
				feedbackPanel.setOutputMarkupId(true);
				item.add(feedbackPanel);

				// point editor
				View view = new View();
				view.setWicketId("pointEditor");

				ViewModel viewModel = new ViewModel();
				viewModel.setEntity(pointCopy);

				if (pointType.equals("explanation")) {
					viewModel.setPropertyCode("explanation");
					pointEditorPanel = new AjaxPropertyEditableMultilineLabel(
							viewModel, view) {
						private static final long serialVersionUID = 1L;

						@Override
						protected void onSubmit(AjaxRequestTarget target) {
							points.getErrors().empty();
							if (points.update(point, pointCopy)) {
								super.onSubmit(target);
							} else {
								addErrorsByKeys(points);
							}
							target.addComponent(feedbackPanel);
						}
					};
				} else if (pointType.equals("link")) {
					viewModel.setEntity(point);
					viewModel.setEntities(points);
					pointEditorPanel = new AjaxLinkEditorPanel("pointEditor",
							viewModel);
				} else if (pointType.equals("image")) {
					viewModel.setEntity(point);
					viewModel.setEntities(points);
					pointEditorPanel = new AjaxImageEditorPanel("pointEditor",
							viewModel);
				} else if (pointType.equals("code")) {
					viewModel.setPropertyCode("code");
					pointEditorPanel = new AjaxCodeEditorPanel(viewModel, view) {
						private static final long serialVersionUID = 1L;

						@Override
						protected void onSubmit(AjaxRequestTarget target) {
							points.getErrors().empty();
							if (points.update(point, pointCopy)) {
								super.onSubmit(target);
							} else {
								addErrorsByKeys(points);
							}
							target.addComponent(feedbackPanel);
						}
					};
				} else {
					viewModel.setPropertyCode("text");
					pointEditorPanel = new AjaxPropertyTextAreaEditableLabel(
							viewModel, view) {
						private static final long serialVersionUID = 1L;

						@Override
						protected void onSubmit(AjaxRequestTarget target) {
							points.getErrors().empty();
							if (points.update(point, pointCopy)) {
								super.onSubmit(target);
							} else {
								addErrorsByKeys(points);
							}
							target.addComponent(feedbackPanel);
						}
					};
				}
				item.add(pointEditorPanel);

				// remove link
				item.add(new AjaxLink("remove") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						points.remove(point);
						// update the parent of this AjaxPointEditorListPanel to
						// reflect change, since SlidePointEditorListView cannot
						// be updated via ajax
						target.addComponent(AjaxPointEditorListPanel.this);
					}
				});
			}
		});
	}

}
