package course.wicket.lecture.slide;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import course.lecture.slide.Slide;
import course.wicket.lecture.point.PointDisplayTableListView;

/**
 * Slide display slide panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-23
 */
public class SlideDisplaySlidePanel extends Panel {

	private static final long serialVersionUID = 222030L;

	public SlideDisplaySlidePanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());

		Slide slide = (Slide) viewModel.getEntity();
		add(new Label("title", new PropertyModel(slide, "title")));
		add(new Label("subTitle", new PropertyModel(slide, "subTitle")));

		add(new PointDisplayTableListView("pointDisplayTableListView", slide
				.getPoints()));
	}

}
