package org.modelibra.swing.domain.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.modelibra.IDomainModel;
import org.modelibra.ModelSession;
import org.modelibra.config.ModelConfig;
import org.modelibra.swing.app.App;
import org.modelibra.swing.domain.model.concept.ConceptTableFrame;
import org.modelibra.swing.widget.ModelibraPanel;
import org.modelibra.util.NatLang;

@SuppressWarnings("serial")
public class ModelTablePanel extends ModelibraPanel {

	private ModelTable modelTable;

	public ModelTablePanel(final ModelEntryConceptsTableFrame contentFrame,
			List<ModelConfig> modelConfigList) {
		ModelTableModel modelDisplayTableModel = new ModelTableModel(
				modelConfigList) {
			protected String getText(String key) {
				return contentFrame.getApp().getNatLang().getText(key);
			}
		};
		modelTable = new ModelTable(modelDisplayTableModel);
		modelTable.setSelectedRow(0);

		setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(modelTable);
		add(scrollPane, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		add(buttonPanel, BorderLayout.SOUTH);

		NatLang natLang = contentFrame.getApp().getNatLang();
		JButton conceptsButton = new JButton(natLang.getText("concepts"));
		buttonPanel.add(conceptsButton);
		if (modelConfigList.size() == 0) {
			conceptsButton.setVisible(false);
		}
		conceptsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				App app = contentFrame.getApp();
				ModelConfig modelConfig = getModelTable()
						.getCurrentModelConfig();

				IDomainModel model = app.getDomainModel(modelConfig.getCode());
				ModelSession modelSession = model.getNewSession();
				app.setModelSession(modelSession);
				contentFrame.getMainFrame().getMainMenuBar().setSession(
						modelSession);

				ConceptTableFrame conceptTableFrame = new ConceptTableFrame(
						app, modelConfig.getConceptsConfig()
								.getEntryConceptConfigList());
				displayDownRight(conceptTableFrame);
				contentFrame.addChildFrame(conceptTableFrame);
			}
		});
	}

	public ModelTable getModelTable() {
		return modelTable;
	}

}
