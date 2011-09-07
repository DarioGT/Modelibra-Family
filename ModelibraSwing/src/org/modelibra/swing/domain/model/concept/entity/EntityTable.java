package org.modelibra.swing.domain.model.concept.entity;

import java.util.Observable;
import java.util.Observer;

import org.modelibra.DomainModel;
import org.modelibra.IEntity;
import org.modelibra.action.AddAction;
import org.modelibra.action.EntitiesAction;
import org.modelibra.action.RemoveAction;
import org.modelibra.action.UpdateAction;
import org.modelibra.swing.widget.ModelibraTable;

@SuppressWarnings("serial")
public class EntityTable extends ModelibraTable implements Observer {

	private EntityTableModel entityTableModel;

	public EntityTable(EntityTableModel entityTableModel) {
		super(entityTableModel);
		this.entityTableModel = entityTableModel;
		DomainModel model = (DomainModel) entityTableModel.getEntities()
				.getModel();
		model.addObserver(this);
		setSelectedRow(0);
	}

	public IEntity<?> getCurrentEntity() {
		return entityTableModel.getEntities().locate(getSelectedRow());
	}

	// implemented from Observer
	public void update(Observable o, Object arg) {
		if (o == entityTableModel.getEntities().getModel()) {
			if (arg instanceof EntitiesAction) {
				EntitiesAction entitiesAction = (EntitiesAction) arg;
				if (entitiesAction instanceof AddAction) {
					entityTableModel.fireTableDataChanged();
					int ix = entityTableModel.getEntities().size() - 1;
					setSelectedRow(ix);
				} else if (entitiesAction instanceof UpdateAction) {
					int ix = getSelectedRow();
					entityTableModel.fireTableDataChanged();
					setSelectedRow(ix);
				} else if (entitiesAction instanceof RemoveAction) {
					int ix = getSelectedRow();
					entityTableModel.fireTableDataChanged();
					setSelectedRow(ix);
				}
			}
		}
	}

}
