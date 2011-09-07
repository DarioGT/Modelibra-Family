package travel.wicket.app.about;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;

/**
 * About page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-04
 */
public class AboutPage extends WebPage {
	
	private static final long serialVersionUID = 282190L;

	public AboutPage() {	
        add(new Image("travelImpression"));
	}

}
