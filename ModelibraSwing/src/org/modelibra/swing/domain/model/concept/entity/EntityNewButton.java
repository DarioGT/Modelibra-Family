package org.modelibra.swing.domain.model.concept.entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.config.ConceptConfig;
import org.modelibra.swing.widget.ModelibraButton;
import org.modelibra.swing.widget.ModelibraFrame;
import org.modelibra.util.NatLang;

@SuppressWarnings("serial")
public class EntityNewButton extends ModelibraButton {

	public EntityNewButton(final ModelibraFrame contentFrame,
			final boolean internalContext, final IEntities<?> entities) {
		setButtonName(contentFrame.getApp().getNatLang());
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConceptConfig conceptConfig = entities.getConceptConfig();
				EntityBridge entityBridge = new EntityBridge(entities,
						conceptConfig.getPropertiesConfig().getList(),
						conceptConfig.getNeighborsConfig()
								.getParentConfigList());
				IEntity<?> defaultEntity = entityBridge.createDefaultEntity();
				if (defaultEntity != null) {
					ModelibraFrame modelibraFrame = new EntityAttributesFrame(
							contentFrame.getApp(), internalContext, false,
							true, entities, defaultEntity, defaultEntity
									.getConceptConfig().getPropertiesConfig()
									.getPropertyConfigWithoutReferenceList(),
							defaultEntity.getConceptConfig()
									.getNeighborsConfig().getList());
					contentFrame.displayDownRight(modelibraFrame);
					contentFrame.addChildFrame(modelibraFrame);
				}
			}
		});
	}

	protected void setButtonName(NatLang natLang) {
		setText(natLang.getText("new"));
	}

}
