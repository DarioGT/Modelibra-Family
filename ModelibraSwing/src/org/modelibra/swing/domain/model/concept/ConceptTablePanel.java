package org.modelibra.swing.domain.model.concept;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.modelibra.IDomainModel;
import org.modelibra.IEntities;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.swing.domain.model.concept.entity.EntityTableFrame;
import org.modelibra.swing.widget.ModelibraFrame;
import org.modelibra.swing.widget.ModelibraPanel;
import org.modelibra.util.NatLang;

@SuppressWarnings("serial")
public class ConceptTablePanel extends ModelibraPanel {

	public ConceptTablePanel(final ModelibraFrame contentFrame,
			List<ConceptConfig> conceptConfigList) {
		final NatLang natLang = contentFrame.getApp().getNatLang();
		ConceptTableModel entryConceptTableModel = new ConceptTableModel(
				conceptConfigList) {
			protected String getText(String key) {
				return natLang.getText(key);
			}
		};
		final ConceptTable entryConceptTable = new ConceptTable(
				entryConceptTableModel);
		entryConceptTable.setSelectedRow(0);

		setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(entryConceptTable);
		add(scrollPane, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		add(buttonPanel, BorderLayout.SOUTH);

		JButton displayButton = new JButton(natLang.getText("display"));
		buttonPanel.add(displayButton);
		displayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConceptConfig currentConceptConfig = entryConceptTable
						.getCurrentConceptConfig();
				if (currentConceptConfig != null) {
					if (currentConceptConfig.isDisplay()) {
						ModelConfig modelConfig = currentConceptConfig
								.getModelConfig();
						IDomainModel model = contentFrame.getApp()
								.getDomainModel(modelConfig.getCode());
						IEntities<?> entities = model
								.getEntry(currentConceptConfig.getCode());

						ModelibraFrame entityTableFrame = new EntityTableFrame(
								contentFrame.getApp(), true, true, entities,
								currentConceptConfig.getPropertiesConfig()
										.getEssentialPropertyConfigList(),
								currentConceptConfig.getNeighborsConfig()
										.getList());
						contentFrame.displayDownRight(entityTableFrame);
						contentFrame.addChildFrame(entityTableFrame);
					}
				}
			}
		});

		JButton updateButton = new JButton(natLang.getText("update"));
		buttonPanel.add(updateButton);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConceptConfig currentConceptConfig = entryConceptTable
						.getCurrentConceptConfig();
				if (currentConceptConfig != null) {
					if (currentConceptConfig.isUpdate()) {
						ModelConfig modelConfig = currentConceptConfig
								.getModelConfig();
						IDomainModel model = contentFrame.getApp()
								.getDomainModel(modelConfig.getCode());
						IEntities<?> entities = model
								.getEntry(currentConceptConfig.getCode());

						ModelibraFrame entityTableFrame = new EntityTableFrame(
								contentFrame.getApp(), true, false, entities,
								currentConceptConfig.getPropertiesConfig()
										.getEssentialPropertyConfigList(),
								currentConceptConfig.getNeighborsConfig()
										.getList());
						contentFrame.displayDownRight(entityTableFrame);
						contentFrame.addChildFrame(entityTableFrame);
					}
				}
			}
		});
	}
}
