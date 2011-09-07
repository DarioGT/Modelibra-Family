package org.modelibra.swing.domain.model.concept.entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.RemoveAction;
import org.modelibra.swing.widget.ModelibraButton;
import org.modelibra.swing.widget.ModelibraFrame;
import org.modelibra.util.NatLang;

@SuppressWarnings("serial")
public abstract class EntityRemoveButton extends ModelibraButton {

	public EntityRemoveButton(final ModelibraFrame contentFrame,
			final IEntities<?> entities) {
		setButtonName(contentFrame.getApp().getNatLang());
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IEntity<?> entity = getEntity();
				if (entity != null) {
					message("empty");
					EntitiesAction action = new RemoveAction(contentFrame
							.getApp().getModelSession(), entities, getEntity());
					action.execute();
					if (!action.isExecuted()) {
						message("removeNot");
					}
				}
			}
		});
	}

	protected void setButtonName(NatLang natLang) {
		setText(natLang.getText("remove"));
	}

	protected abstract IEntity<?> getEntity();

	protected abstract void message(String key);

}
