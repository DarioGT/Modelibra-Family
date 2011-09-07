package course.wicket.app.upload;

import org.modelibra.wicket.security.SecureWebPage;

/**
 * Upload page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-17
 */
public class UploadPage extends SecureWebPage {

	private static final long serialVersionUID = 282240L;

	public UploadPage() {
		add(homePageLink("homePage"));
		add(new UploadPanel("uploadSection"));
	}

}