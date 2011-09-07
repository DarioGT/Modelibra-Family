package course.wicket.app.upload;

import java.io.File;
import java.util.Properties;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.lang.Bytes;
import org.modelibra.util.PathLocator;
import org.modelibra.util.PropertiesLoader;
import org.modelibra.wicket.util.LocalizedText;

/**
 * Upload form.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-17
 */
public class UploadForm extends Form {

	private static final long serialVersionUID = 282255L;

	public static final String UPLOAD_CONFIG_PROPERTIES_FILE_NAME = "uploadConfig.properties";

	public static final String CLASS_FOLDER_KEY = "class";

	public static final String UPLOAD_FOLDER_KEY = "upload";

	private FileUploadField uploadField;

	private Folder uploadFolder;

	private String fileName;

	public UploadForm(String id) {
		super(id);
		setMultiPart(true);
		uploadField = new FileUploadField("fileInput");
		// uploadField.setLabel(new ResourceModel("browse"));
		add(uploadField);
		setMaxSize(Bytes.kilobytes(10000));

		uploadFolder = new Folder(findUploadFolderPath());
	}

	public String getFileName() {
		return fileName;
	}

	protected void onSubmit() {
		FileUpload upload = uploadField.getFileUpload();
		if (upload != null) {
			fileName = upload.getClientFileName();
			File newFile = new File(uploadFolder, fileName);
			if (!newFile.exists()) {
				try {
					newFile.createNewFile();
					upload.writeTo(newFile);
					String uploadMessage = LocalizedText.getText(this,
							"upload.yes");
					UploadForm.this.info(upload.getClientFileName() + " "
							+ uploadMessage);
				} catch (Exception e) {
					String uploadMessage = LocalizedText.getText(this,
							"upload.no");
					throw new IllegalStateException(fileName + " "
							+ uploadMessage + ": " + e.getMessage());
				}
			} else {
				String uploadMessage = LocalizedText.getText(this,
						"upload.no.fileExists");
				UploadForm.this.info(fileName + " " + uploadMessage);
			}
		} else {
			String uploadMessage = LocalizedText.getText(this,
					"upload.no.noFileSelected");
			UploadForm.this.info(uploadMessage);
		}
	}

	private String findUploadFolderPath() {
		String uploadFolderPath = null;
		Properties properties = PropertiesLoader.load(UploadForm.class,
				UPLOAD_CONFIG_PROPERTIES_FILE_NAME);
		String classesFolderName = properties.getProperty(CLASS_FOLDER_KEY);
		String webInfFolderName = "WEB-INF";
		String webInfClasses = webInfFolderName + "/" + classesFolderName;
		String uploadFolderName = properties.getProperty(UPLOAD_FOLDER_KEY);

		PathLocator pathLocator = new PathLocator();
		uploadFolderPath = pathLocator.findAbsolutePath(UploadForm.class,
				webInfClasses, uploadFolderName);
		return uploadFolderPath;
	}

}
