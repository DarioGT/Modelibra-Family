package org.modelibra.swing.domain.model;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BoxLayout;

import org.modelibra.config.ModelConfig;
import org.modelibra.swing.app.App;
import org.modelibra.swing.app.MainFrame;
import org.modelibra.swing.widget.ModelibraFrame;
import org.modelibra.util.NatLang;

@SuppressWarnings("serial")
public class ModelEntryConceptsTableFrame extends ModelibraFrame {

	private MainFrame mainFrame;

	public ModelEntryConceptsTableFrame(App app, MainFrame mainFrame,
			List<ModelConfig> modelConfigList) {
		super(app);
		this.mainFrame = mainFrame;

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		NatLang natLang = app.getNatLang();
		setTitle(natLang.getText("Models"));
		Container container = getContentPane();
		ModelTablePanel modelTablePanel = new ModelTablePanel(this,
				modelConfigList);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(modelTablePanel);

		setSize(FRAME_SIZE);
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

}
