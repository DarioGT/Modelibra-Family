package course.wicket.app.upload;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Upload panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-17
 */
public class UploadPanel extends Panel {

	private static final long serialVersionUID = 282250L;

	private UploadForm uploadForm;

	public UploadPanel(String id) {
		super(id);
		uploadForm = new UploadForm("uploadForm");
		add(uploadForm);
		add(new FeedbackPanel("uploadFeedback"));
	}

	public String getFileName() {
		return uploadForm.getFileName();
	}

}
