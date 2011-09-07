package org.modelibra.swing.domain.model.concept.entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.swing.widget.ModelibraButton;
import org.modelibra.swing.widget.ModelibraFrame;
import org.modelibra.util.NatLang;

@SuppressWarnings("serial")
public abstract class EntityButton extends ModelibraButton {

	public EntityButton(final ModelibraFrame contentFrame,
			final boolean internalContext, final boolean displayOnly,
			final IEntities<?> entities) {
		setButtonName(displayOnly, contentFrame.getApp().getNatLang());
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IEntity<?> entity = getEntity();
				if (entity != null) {
					ModelibraFrame modelibraFrame = new EntityAttributesFrame(
							contentFrame.getApp(), internalContext,
							displayOnly, false, entities, entity, entity
									.getConceptConfig().getPropertiesConfig()
									.getPropertyConfigWithoutReferenceList(),
							entity.getConceptConfig().getNeighborsConfig()
									.getList());
					contentFrame.displayDownRight(modelibraFrame);
					contentFrame.addChildFrame(modelibraFrame);
				}
			}
		});
	}

	protected void setButtonName(boolean displayOnly, NatLang natLang) {
		if (displayOnly) {
			setText(natLang.getText("display"));
		} else {
			setText(natLang.getText("edit"));
		}
	}

	protected abstract IEntity<?> getEntity();

}
