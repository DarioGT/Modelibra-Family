package org.modelibra.swing.domain.model.concept.entity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.exception.ModelibraRuntimeException;
import org.modelibra.swing.domain.model.concept.entity.neighbor.EntityNeighborButton;
import org.modelibra.swing.widget.ModelibraFrame;
import org.modelibra.swing.widget.ModelibraPanel;
import org.modelibra.util.NatLang;

@SuppressWarnings("serial")
public class EntityTablePanel extends ModelibraPanel {

	private boolean internalContext;

	private ModelibraFrame contentFrame;

	private JLabel messageLabel;

	private EntityTable entityTable;

	public EntityTablePanel(ModelibraFrame contentFrame,
			boolean internalContext, boolean displayOnly,
			IEntities<?> entities, List<PropertyConfig> propertyConfigList,
			List<NeighborConfig> neighborConfigList) {
		this.internalContext = internalContext;
		this.contentFrame = contentFrame;
		entities.getErrors().empty();
		setLayout(new BorderLayout());

		JPanel messagePanel = new JPanel();
		messageLabel = new JLabel("");
		messagePanel.add(messageLabel);

		if (propertyConfigList.size() == 0) {
			throw new ModelibraRuntimeException(
					"There are no essential properties for this concept.");
		}

		entityTable = createTable(displayOnly, entities, propertyConfigList);
		entityTable.setSelectedRow(0);
		JScrollPane tableScrollPane = new JScrollPane(entityTable);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);

		add(messagePanel, BorderLayout.NORTH);
		add(tableScrollPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		addDisplayButton(buttonPanel, entities);
		if (!displayOnly) {
			addNewButton(buttonPanel, entities);
			addEditButton(buttonPanel, entities);
			addRemoveButton(buttonPanel, entities);
		}
		// Neighbor buttons for navigation
		if (neighborConfigList != null) {
			addNeighborButtons(buttonPanel, displayOnly, neighborConfigList);
		}
	}

	protected EntityTable createTable(boolean displayOnly,
			final IEntities<?> entities, List<PropertyConfig> propertyConfigList) {
		EntityTableModel entityTableModel = new EntityTableModel(displayOnly,
				contentFrame.getApp().getModelSession(), entities,
				propertyConfigList) {
			protected String getText(String key) {
				return contentFrame.getApp().getNatLang().getText(key);
			}

			protected void error(String key) {
				setMessage(contentFrame.getApp().getNatLang().getText(key)
						+ " "
						+ getErrorMsgsByKeys(entities, contentFrame.getApp()
								.getNatLang()));
			}

			protected void noError() {
				setMessage("");
			}
		};
		return new EntityTable(entityTableModel);
	}

	protected void addDisplayButton(JPanel buttonPanel, IEntities<?> entities) {
		buttonPanel.add(new EntityButton(contentFrame, internalContext, true,
				entities) {
			protected IEntity<?> getEntity() {
				return entityTable.getCurrentEntity();
			}
		});
	}

	protected void addNewButton(JPanel buttonPanel, final IEntities<?> entities) {
		if (internalContext) {
			buttonPanel.add(new EntityNewButton(contentFrame, internalContext,
					entities));
		}
	}

	protected void addEditButton(JPanel buttonPanel, IEntities<?> entities) {
		buttonPanel.add(new EntityButton(contentFrame, internalContext, false,
				entities) {
			protected IEntity<?> getEntity() {
				return entityTable.getCurrentEntity();
			}
		});
	}

	protected void addRemoveButton(JPanel buttonPanel,
			final IEntities<?> entities) {
		buttonPanel.add(new EntityRemoveButton(contentFrame, entities) {
			protected IEntity<?> getEntity() {
				return entityTable.getCurrentEntity();
			}

			protected void message(String key) {
				NatLang natLang = contentFrame.getApp().getNatLang();
				setMessage(natLang.getText(key) + " "
						+ getErrorMsgsByKeys(entities, natLang));
			}
		});
	}

	protected void addNeighborButtons(JPanel buttonPanel, boolean displayOnly,
			List<NeighborConfig> neighborConfigList) {
		for (NeighborConfig neighborConfig : neighborConfigList) {
			buttonPanel.add(new EntityNeighborButton(contentFrame, displayOnly,
					neighborConfig) {
				protected IEntity<?> getEntity() {
					return entityTable.getCurrentEntity();
				}
			});
		}
	}

	public void setMessage(String message) {
		messageLabel.setText(message);
		contentFrame.pack();
	}

}
