package org.modelibra.swing.domain.model.concept.entity.property;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.UpdateAction;
import org.modelibra.config.PropertyConfig;
import org.modelibra.swing.widget.ModelibraFrame;
import org.modelibra.swing.widget.ModelibraPanel;
import org.modelibra.type.PropertyClass;
import org.modelibra.util.NatLang;

@SuppressWarnings("serial")
public class EntityPropertyTextAreaPanel extends ModelibraPanel {

	private ModelibraFrame contentFrame;

	private JLabel messageLabel;

	private JTextArea jTextArea;

	private PropertyBridge propertyBridge;

	public EntityPropertyTextAreaPanel(ModelibraFrame contentFrame,
			boolean displayOnly, final boolean add, IEntities<?> entities,
			IEntity<?> entity, PropertyConfig propertyConfig) {
		this.contentFrame = contentFrame;
		if (add) {
			propertyBridge = new PropertyBridge(entity, propertyConfig);
		} else {
			propertyBridge = new PropertyBridge(entity.copy(), propertyConfig);
		}
		setLayout(new BorderLayout());
		addPropertyLabels(propertyConfig, contentFrame.getApp().getNatLang());
		addPropertyTextArea(displayOnly, entities, entity, propertyConfig);
		if (!displayOnly) {
			addPropertyUpdateButton(add, entities, entity, propertyConfig);
		}
	}

	protected void addPropertyLabels(PropertyConfig propertyConfig,
			NatLang natLang) {
		JPanel messagePanel = new JPanel();
		messagePanel.add(new JLabel(natLang.getText(propertyConfig
				.getConceptConfig().getCode()
				+ "." + propertyConfig.getCode())
				+ ": "));
		messageLabel = new JLabel("");
		messagePanel.add(messageLabel);
		add(messagePanel, BorderLayout.NORTH);
	}

	protected void addPropertyTextArea(boolean displayOnly,
			IEntities<?> entities, IEntity<?> entity,
			PropertyConfig propertyConfig) {
		if (propertyConfig.getPropertyClass().equals(PropertyClass.getString())
				&& propertyConfig.getDisplayLengthInt() > MIN_LONG_TEXT_LENGTH) {
			jTextArea = new JTextArea();
			jTextArea.setText(propertyBridge.getProperty());
			if (displayOnly) {
				jTextArea.setEditable(false);
			}
			JScrollPane scrollPane = new JScrollPane(jTextArea);
			add(scrollPane, BorderLayout.CENTER);
		}
	}

	protected void addPropertyUpdateButton(final boolean add,
			final IEntities<?> entities, final IEntity<?> entity,
			PropertyConfig propertyConfig) {
		JPanel buttonPanel = new JPanel();
		JButton updateButton = new JButton(contentFrame.getApp().getNatLang()
				.getText("update"));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String propertyValueString = jTextArea.getText();
				propertyBridge.setProperty(propertyValueString);
				if (propertyBridge.isSetProperty()) {
					setMessage(contentFrame.getApp().getNatLang().getText(
							"empty"));
					if (!add) {
						EntitiesAction action = new UpdateAction(contentFrame
								.getApp().getModelSession(), entities, entity,
								propertyBridge.getEntity());
						action.execute();
						if (!action.isExecuted()) {
							setMessage(contentFrame.getApp().getNatLang()
									.getText("updateNot")
									+ " "
									+ getErrorMsgsByKeys(entities, contentFrame
											.getApp().getNatLang()));
						}
					}
				} else {
					setMessage(contentFrame.getApp().getNatLang().getText(
							"invalidType"));
				}
			}
		});
		buttonPanel.add(updateButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void setMessage(String message) {
		messageLabel.setText(message);
		contentFrame.pack();
	}

	public String getPropertyName() {
		return propertyBridge.getPropertyName();
	}

}
