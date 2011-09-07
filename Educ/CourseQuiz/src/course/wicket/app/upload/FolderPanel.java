package course.wicket.app.upload;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.file.Folder;

/**
 * File table panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-04-17
 */
public class FolderPanel extends Panel {

	private static final long serialVersionUID = 282220L;

	private static final Log log = LogFactory.getLog(FolderPanel.class);

	private Folder folder;

	private List<File> fileList;

	private FileListView fileListView;

	public FolderPanel(String id, Folder folder) {
		super(id);

		this.folder = folder;

		fileList = new ArrayList<File>();
		File[] files = folder.listFiles();
		if (files == null) {
			log.error("There is no upload folder!");
		} else {
			List<File> arrayList = Arrays.asList(files);
			fileList.addAll(arrayList);
			fileListView = new FileListView("fileList", fileList);
			add(fileListView);
		}
	}

	private class FileListView extends ListView {

		static final long serialVersionUID = 282221L;

		public FileListView(String name, List<File> files) {
			super(name, files);
		}

		protected void populateItem(ListItem listItem) {
			File file = (File) listItem.getModelObject();
			listItem.add(new Label("file", file.getName()));
		}
	}

	public void refresh() {
		fileListView.modelChanging();
		fileList.clear();
		File[] files = folder.listFiles();
		List<File> arrayList = Arrays.asList(files);
		fileList.addAll(arrayList);
	}

}
