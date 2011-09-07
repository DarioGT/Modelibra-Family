package org.modelibra.swing.domain.model.concept.entity.property;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;

import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.UpdateAction;
import org.modelibra.config.PropertyConfig;
import org.modelibra.swing.widget.ModelibraFrame;

@SuppressWarnings("serial")
public abstract class EntityPropertyPasswordField extends JPasswordField {

	private PropertyBridge propertyBridge;

	public EntityPropertyPasswordField(final ModelibraFrame contentFrame,
			boolean displayOnly, final boolean add,
			final IEntities<?> entities, final IEntity<?> entity,
			final PropertyConfig propertyConfig) {
		if (add) {
			propertyBridge = new PropertyBridge(entity, propertyConfig);
		} else {
			propertyBridge = new PropertyBridge(entity.copy(), propertyConfig);
		}
		setText(propertyBridge.getProperty());
		if (displayOnly) {
			setEditable(false);
		}
		if (propertyBridge.getPropertyConfig().isUpdate()) {
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					char[] pw = getPassword();
					String propertyValueString = String.valueOf(pw);
					propertyBridge.setProperty(propertyValueString);
					if (propertyBridge.isSetProperty()) {
						if (add) {
							message("accepted");
						} else {
							message("empty");
							EntitiesAction action = new UpdateAction(
									contentFrame.getApp().getModelSession(),
									entities, entity, propertyBridge
											.getEntity());
							action.execute();
							if (!action.isExecuted()) {
								message("updateNot");
							}
						}
					} else {
						message("invalidType");
					}
				}
			});
		}
	}

	public String getPropertyName() {
		return propertyBridge.getPropertyName();
	}

	protected abstract void message(String key);

}
