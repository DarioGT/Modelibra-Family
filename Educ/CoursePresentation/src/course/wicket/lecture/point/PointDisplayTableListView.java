package course.wicket.lecture.point;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;
import org.modelibra.wicket.widget.CodeMultiLineLabelPanel;
import org.modelibra.wicket.widget.ContextImagePanel;
import org.modelibra.wicket.widget.ExternalLinkPanel;
import org.modelibra.wicket.widget.LabelPanel;
import org.modelibra.wicket.widget.MultiLineLabelPanel;

import course.Course;
import course.lecture.Lecture;
import course.lecture.point.Point;
import course.lecture.point.Points;
import course.wicket.app.CourseApp;

/**
 * Point display table list view.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2007-12-17
 */
public class PointDisplayTableListView extends ListView {

	private static final long serialVersionUID = 222020L;

	private CourseApp presentApp;

	private Course course;

	private Lecture lecture;

	public PointDisplayTableListView(String id, Points points) {
		super(id, points.getList());
		presentApp = (CourseApp) getApplication();
		course = presentApp.getCourse();
		lecture = course.getLecture();
	}

	protected void populateItem(ListItem item) {
		Point point = (Point) item.getModelObject();
		Panel pointPanel;
		if (point.getExplanation() != null) {
			ViewModel pointViewModel = new ViewModel();
			pointViewModel.setModel(lecture);
			pointViewModel.setEntity(point);
			pointViewModel.setPropertyCode("explanation");
			View pointView = new View();
			pointView.setWicketId("propertyValue");
			pointPanel = new MultiLineLabelPanel(pointViewModel, pointView);
		} else if (point.getUrl() != null) {
			ViewModel urlViewModel = new ViewModel();
			urlViewModel.setModel(lecture);
			urlViewModel.setEntity(point);
			urlViewModel.setPropertyCode("url");
			View urlView = new View();
			urlView.setWicketId("propertyValue");
			urlView.getUserProperties().addUserProperty("displayText",
					point.getText());
			pointPanel = new ExternalLinkPanel(urlViewModel, urlView);
		} else if (point.getImage() != null) {
			ViewModel imageViewModel = new ViewModel();
			imageViewModel.setModel(lecture);
			imageViewModel.setEntity(point);
			imageViewModel.setPropertyCode("image");
			View imageView = new View();
			imageView.setWicketId("propertyValue");
			pointPanel = new ContextImagePanel(imageViewModel, imageView);
		} else if (point.getCode() != null) {
			ViewModel codeViewModel = new ViewModel();
			codeViewModel.setModel(lecture);
			codeViewModel.setEntity(point);
			codeViewModel.setPropertyCode("code");
			View codeView = new View();
			codeView.setWicketId("propertyValue");
			pointPanel = new CodeMultiLineLabelPanel(codeViewModel, codeView);
		} else {
			ViewModel pointViewModel = new ViewModel();
			pointViewModel.setModel(lecture);
			pointViewModel.setEntity(point);
			pointViewModel.setPropertyCode("text");
			View pointView = new View();
			pointView.setWicketId("propertyValue");
			pointPanel = new LabelPanel(pointViewModel, pointView);
		}
		item.add(pointPanel);
	}

}
