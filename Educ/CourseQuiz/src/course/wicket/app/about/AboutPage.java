package course.wicket.app.about;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;

/**
 * Domain model page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-03
 */
public class AboutPage extends WebPage {

	private static final long serialVersionUID = 282190L;

	public AboutPage() {
		add(homePageLink("homePage"));

		add(new Image("CourseQuiz"));
	}

}
