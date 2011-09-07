package org.modelibra.swing.domain.model.concept.entity.property;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.config.PropertyConfig;
import org.modelibra.swing.widget.ModelibraFrame;
import org.modelibra.swing.widget.ModelibraPanel;
import org.modelibra.type.PropertyClass;
import org.modelibra.util.TextHandler;

@SuppressWarnings("serial")
public class EntityPropertyLongTextPanel extends ModelibraPanel {

	public EntityPropertyLongTextPanel(ModelibraFrame contentFrame,
			boolean displayOnly, boolean add, IEntities<?> entities,
			IEntity<?> entity, PropertyConfig propertyConfig) {
		addPropertyLongText(contentFrame, displayOnly, add, entities, entity,
				propertyConfig);
	}

	protected void addPropertyLongText(final ModelibraFrame contentFrame,
			final boolean displayOnly, final boolean add,
			final IEntities<?> entities, final IEntity<?> entity,
			final PropertyConfig propertyConfig) {
		if (propertyConfig.getPropertyClass().equals(PropertyClass.getString())
				&& propertyConfig.getDisplayLengthInt() > MIN_LONG_TEXT_LENGTH) {
			int displayTextLength = entity.getConceptConfig().getModelConfig()
					.getDomainConfig().getShortTextDefaultLengthInt();
			TextHandler textHandler = new TextHandler();
			String propertyLongText = (String) entity
					.getProperty(propertyConfig.getCode());
			String propertyDisplayText;
			if (propertyLongText != null) {
				propertyDisplayText = textHandler.extractBeginPlusThreeDots(
						propertyLongText, displayTextLength);
			} else {
				propertyDisplayText = "";
			}
			JLabel displayTextLabel = new JLabel(propertyDisplayText);
			add(displayTextLabel);

			JButton textButton = new JButton("...");
			textButton.setPreferredSize(new Dimension(28, 14));
			textButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ModelibraFrame modelibraFrame = new EntityPropertyTextAreaFrame(
							contentFrame.getApp(), displayOnly, add, entities,
							entity, propertyConfig);
					if (contentFrame == null) {
						displayDownRight(modelibraFrame);
					} else {
						contentFrame.displayDownRight(modelibraFrame);
						contentFrame.addChildFrame(modelibraFrame);
					}
				}
			});
			add(textButton);
		}

	}

}
