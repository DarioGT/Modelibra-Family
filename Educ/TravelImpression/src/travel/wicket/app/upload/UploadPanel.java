package travel.wicket.app.upload;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Upload panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-08-30
 */
public class UploadPanel extends Panel {

	private static final long serialVersionUID = 282250L;

	public UploadPanel(String id) {
		super(id);

		add(new FeedbackPanel("uploadFeedback"));

		add(new UploadForm("uploadForm"));
	}

}
