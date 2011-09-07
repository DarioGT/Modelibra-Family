package travel.wicket.app.upload;

import java.io.File;
import java.util.Properties;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.lang.Bytes;
import org.modelibra.util.PathLocator;
import org.modelibra.util.PropertiesLoader;
import org.modelibra.wicket.security.AppSession;
import org.modelibra.wicket.util.LocalizedText;

import travel.impression.message.Message;
import travel.impression.message.Messages;
import travel.impression.photo.Photo;
import travel.impression.traveler.Traveler;

/**
 * Upload form.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-28
 */
public class UploadForm extends Form {

	private static final long serialVersionUID = 282230L;

	public static final String UPLOAD_CONFIG_PROPERTIES_FILE_NAME = "uploadConfig.properties";

	public static final String CLASS_FOLDER_KEY = "class";

	public static final String UPLOAD_FOLDER_KEY = "upload";

	public static final String HTTP_BASE_KEY = "http";

	private FileUploadField uploadField;

	private Folder uploadPrivateFolder;

	private Folder uploadPublicFolder;

	private FolderPanel folderPrivatePanel;

	private FolderPanel folderPublicPanel;

	public UploadForm(String id) {
		super(id);
		setMultiPart(true);
		uploadField = new FileUploadField("fileInput");
		// uploadField.setLabel(new ResourceModel("browse"));
		add(uploadField);
		setMaxSize(Bytes.kilobytes(10000));

		uploadPrivateFolder = new Folder(findUploadPrivateFolderPath());
		folderPrivatePanel = new FolderPanel("privateFolderSection",
				uploadPrivateFolder);
		add(folderPrivatePanel);

		uploadPublicFolder = new Folder(findUploadPublicFolderPath());
		folderPublicPanel = new FolderPanel("publicFolderSection",
				uploadPublicFolder);
		add(folderPublicPanel);

		add(new Button("privateUpload") {
			static final long serialVersionUID = 282231L;

			public void onSubmit() {
				FileUpload upload = uploadField.getFileUpload();
				if (upload != null) {
					File newFile = new File(uploadPrivateFolder, upload
							.getClientFileName());
					if (!newFile.exists()) {
						try {
							newFile.createNewFile();
							upload.writeTo(newFile);
							String uploadMessage = LocalizedText.getText(this,
									"upload.yes");
							UploadForm.this.info(upload.getClientFileName()
									+ " " + uploadMessage);
							folderPrivatePanel.refresh();
						} catch (Exception e) {
							String uploadMessage = LocalizedText.getText(this,
									"upload.no");
							throw new IllegalStateException(upload
									.getClientFileName()
									+ " "
									+ uploadMessage
									+ ": "
									+ e.getMessage());
						}
					} else {
						String uploadMessage = LocalizedText.getText(this,
								"upload.no.fileExists");
						UploadForm.this.info(upload.getClientFileName() + " "
								+ uploadMessage);
					}
				}
			}
		}.setDefaultFormProcessing(false));
	}

	protected void onSubmit() {
		FileUpload upload = uploadField.getFileUpload();
		if (upload != null) {
			String fileName = upload.getClientFileName();
			File newFile = new File(uploadPublicFolder, fileName);
			if (!newFile.exists()) {
				try {
					newFile.createNewFile();
					upload.writeTo(newFile);
					String uploadMessage = LocalizedText.getText(this,
							"upload.yes");
					UploadForm.this.info(upload.getClientFileName() + " "
							+ uploadMessage);
					folderPublicPanel.refresh();

					AppSession appSession = (AppSession) getSession();
					Traveler traveler = (Traveler) appSession.getSignedInUser();
					Messages messages = traveler.getMessages();
					Message message = messages.getLastMessage();
					if (message != null) {
						Photo photo = new Photo(message);
						String fileLink = findUploadLink() + fileName;
						photo.setWebLink(fileLink);
						message.getPhotos().add(photo);
					}
				} catch (Exception e) {
					String uploadMessage = LocalizedText.getText(this,
							"upload.no");
					throw new IllegalStateException(upload.getClientFileName()
							+ " " + uploadMessage + ": " + e.getMessage());
				}
			} else {
				String uploadMessage = LocalizedText.getText(this,
						"upload.no.fileExists");
				UploadForm.this.info(upload.getClientFileName() + " "
						+ uploadMessage);
			}
		}
	}

	private String findUploadPrivateFolderPath() {
		String uploadPrivateFolderPath = null;
		Properties properties = PropertiesLoader.load(UploadForm.class,
				UPLOAD_CONFIG_PROPERTIES_FILE_NAME);
		String classesFolderName = properties.getProperty(CLASS_FOLDER_KEY);
		String uploadFolderName = properties.getProperty(UPLOAD_FOLDER_KEY);

		PathLocator pathLocator = new PathLocator();
		uploadPrivateFolderPath = pathLocator.findAbsolutePath(
				UploadForm.class, classesFolderName, uploadFolderName);
		return uploadPrivateFolderPath;
	}

	private String findUploadPublicFolderPath() {
		String uploadPublicFolderPath = null;
		Properties properties = PropertiesLoader.load(UploadForm.class,
				UPLOAD_CONFIG_PROPERTIES_FILE_NAME);
		String classesFolderName = properties.getProperty(CLASS_FOLDER_KEY);
		String webInfFolderName = "WEB-INF";
		String webInfClasses = webInfFolderName + "/" + classesFolderName;
		String uploadFolderName = properties.getProperty(UPLOAD_FOLDER_KEY);

		PathLocator pathLocator = new PathLocator();
		uploadPublicFolderPath = pathLocator.findAbsolutePath(UploadForm.class,
				webInfClasses, uploadFolderName);
		return uploadPublicFolderPath;
	}

	private String findUploadLink() {
		String appBase = null;
		Properties properties = PropertiesLoader.load(UploadForm.class,
				UPLOAD_CONFIG_PROPERTIES_FILE_NAME);
		String httpBase = properties.getProperty(HTTP_BASE_KEY);
		String uploadFolderName = properties.getProperty(UPLOAD_FOLDER_KEY);
		appBase = httpBase + "/" + uploadFolderName + "/";
		return appBase;
	}

}
