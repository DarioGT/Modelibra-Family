package course.wicket.quiz.document;

import java.util.Properties;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.util.file.File;
import org.modelibra.util.PropertiesLoader;

import course.quiz.document.Document;
import course.quiz.document.Documents;
import course.wicket.app.upload.UploadForm;

/**
 * Document display table list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-27
 */
public class DocumentDisplayTableListView extends ListView {

	private static final long serialVersionUID = 282010L;

	public DocumentDisplayTableListView(String id, Documents documents) {
		super(id, documents.getList());
	}

	protected void populateItem(ListItem item) {
		Document document = (Document) item.getModelObject();

		item.add(new Label("quizName", document.getTest().getName()));

		String filePath = findPublicUploadFolderPath() + document.getFileName();
		item.add(new DownloadLink("documentLink", new File(filePath)));

		item.add(new Label("creationDate", document.getUploadDateText()));
	}

	private String findPublicUploadFolderPath() {
		String uploadFolderPath = null;
		Properties properties = PropertiesLoader.load(UploadForm.class,
				UploadForm.UPLOAD_CONFIG_PROPERTIES_FILE_NAME);
		String uploadFolderName = properties
				.getProperty(UploadForm.UPLOAD_FOLDER_KEY);
		uploadFolderPath = uploadFolderName + "/";
		return uploadFolderPath;
	}

}
