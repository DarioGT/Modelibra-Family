package org.modelibra.swing.domain.model.concept;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import org.modelibra.config.ConceptConfig;
import org.modelibra.swing.app.App;
import org.modelibra.swing.widget.ModelibraFrame;

@SuppressWarnings("serial")
public class ConceptTableFrame extends ModelibraFrame {

	public ConceptTableFrame(App app, List<ConceptConfig> conceptConfigList) {
		super(app);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		setTitle(app.getNatLang().getText("concepts"));
		getContentPane().add(new ConceptTablePanel(this, conceptConfigList));

		setSize(FRAME_SIZE);
	}

}
