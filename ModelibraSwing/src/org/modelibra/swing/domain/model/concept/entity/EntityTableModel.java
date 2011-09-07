package org.modelibra.swing.domain.model.concept.entity;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.ModelSession;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.UpdateAction;
import org.modelibra.config.PropertyConfig;

@SuppressWarnings("serial")
public abstract class EntityTableModel extends AbstractTableModel {

	private boolean displayOnly;
	private ModelSession modelSession;
	private IEntities<?> entities;
	private List<PropertyConfig> propertyConfigList;

	public EntityTableModel(boolean displayOnly, ModelSession modelSession,
			IEntities<?> entities) {
		this.displayOnly = displayOnly;
		this.modelSession = modelSession;
		this.entities = entities;
		propertyConfigList = entities.getConceptConfig().getPropertiesConfig()
				.getList();
	}

	public EntityTableModel(boolean displayOnly,
			final ModelSession modelSession, final IEntities<?> entities,
			final List<PropertyConfig> propertyConfigList) {
		this(displayOnly, modelSession, entities);
		this.propertyConfigList = propertyConfigList;
	}

	public IEntities<?> getEntities() {
		return entities;
	}

	private IEntity<?> getEntity(int number) {
		return entities.locate(number);
	}

	// implemented from AbstractTableModel
	public int getRowCount() {
		return entities.size();
	}

	// implemented from AbstractTableModel
	public int getColumnCount() {
		return propertyConfigList.size();
	}

	// implemented from AbstractTableModel
	public Object getValueAt(int r, int c) {
		int columnCount = 0;
		for (PropertyConfig propertyConfig : propertyConfigList) {
			if (c == columnCount++) {
				IEntity<?> entity = getEntity(r);
				if (entity != null) {
					return entity.getProperty(propertyConfig.getCode());
				}
			}
		}
		return null;
	}

	@Override
	public String getColumnName(int c) {
		int columnCount = 0;
		for (PropertyConfig propertyConfig : propertyConfigList) {
			if (c == columnCount++) {
				return getText(propertyConfig.getConceptConfig().getCode()
						+ "." + propertyConfig.getCode());
			}
		}
		return "?";
	}

	@Override
	public boolean isCellEditable(int r, int c) {
		if (!displayOnly) {
			int columnCount = 0;
			for (PropertyConfig propertyConfig : propertyConfigList) {
				if (c == columnCount++) {
					if (!propertyConfig.isUpdate()
							|| propertyConfig.isDerived()) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public Class<?> getColumnClass(int c) {
		if (getValueAt(0, c) != null) {
			int columnCount = 0;
			for (PropertyConfig propertyConfig : propertyConfigList) {
				if (c == columnCount++) {
					return propertyConfig.getPropertyClassObject();
				}
			}
		}
		return String.class;
	}

	@Override
	public void setValueAt(Object value, int r, int c) {
		IEntity<?> entity = getEntity(r);
		IEntity<?> entityCopy = entity.copy();
		int columnCount = 0;
		for (PropertyConfig propertyConfig : propertyConfigList) {
			if (c == columnCount++) {
				entityCopy.setProperty(propertyConfig.getCode(), value);
			}
		}
		noError();
		EntitiesAction action = new UpdateAction(modelSession, entities,
				entity, entityCopy);
		action.execute();
		if (!action.isExecuted()) {
			error("updateNot");
		}
	}

	protected abstract String getText(String key);

	protected abstract void error(String key);

	protected abstract void noError();

}
