package org.modelibra.modeler.model;

import java.util.Collection;
import java.util.Iterator;

import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.model.action.AttachCommand;
import org.modelibra.modeler.model.action.Command;
import org.modelibra.modeler.model.action.DetachCommand;
import org.modelibra.modeler.model.action.SetCommand;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-11
 */
public class ItemModel extends EntityModel {

	static final long serialVersionUID = 7168319479760000220L;

	protected int min = 0;

	protected int max = 1;

	protected boolean oid = false;

	protected boolean id = false;

	protected String defaultValue = new String("?");

	protected boolean increment = false;

	protected BoxModel boxModel;

	protected TypeModel typeModel;

	public ItemModel(BoxModel aBoxModel) {
		super();
		this.attach(aBoxModel);
	}

	public int getMin() {
		return min;
	}

	public String getMinString() {
		String result = null;
		Integer integer = new Integer(getMin());
		if (integer != null) {
			result = integer.toString();
		}
		return result;
	}

	public void setMin(int aMin) {
		if ((aMin < 0) || (aMin > this.getMax())) {
			return;
		}
		if (aMin == 1) {
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "min", new Integer(min),
					new Integer(aMin));
			command.execute();
		}
		// id can be null
		else if ((aMin == 0) && (!this.isOid())) {
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "min", new Integer(min),
					new Integer(aMin));
			command.execute();
		} else if ((!this.isOid()) && (!this.isId())) {
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "min", new Integer(min),
					new Integer(aMin));
			command.execute();
		}
	}

	public int getMax() {
		return max;
	}

	public String getMaxString() {
		String result = null;
		Integer integer = new Integer(getMax());
		if (integer != null) {
			result = integer.toString();
		}
		return result;
	}

	public void setMax(int aMax) {
		if ((aMax < 1) || (aMax < this.getMin())) {
			return;
		}
		if (aMax == 1) {
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "max", new Integer(max),
					new Integer(aMax));
			command.execute();
		} else if ((!this.isOid()) && (!this.isId())) {
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "max", new Integer(max),
					new Integer(aMax));
			command.execute();
		}
	}

	public boolean isOid() {
		return oid;
	}

	public void setOid(boolean anOid) {
		// if (anOid && boxModel.hasOid()) {
		// return;
		// }
		if (!anOid) {
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "oid", new Boolean(oid),
					new Boolean(anOid));
			command.execute();
		} else if (this.getMax() == 1) {
			if (this.getMin() == 0) {
				this.setMin(1);
			}
			if (this.isId()) {
				this.setId(false);
			}
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "oid", new Boolean(oid),
					new Boolean(anOid));
			command.execute();
		}
	}

	public boolean isId() {
		return id;
	}

	public void setId(boolean anId) {
		if (anId && this.isOid()) {
			return;
		}
		if (!anId) {
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "id", new Boolean(id),
					new Boolean(anId));
			command.execute();
		} else if (this.getMax() == 1) {
			if (this.getMin() == 0) {
				this.setMin(1);
			}
			Command command = new SetCommand(Manager.getSingleton()
					.getTransaction(), this, "id", new Boolean(id),
					new Boolean(anId));
			command.execute();
		}
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String aDefaultValue) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "defaultValue", defaultValue,
				aDefaultValue);
		command.execute();
	}

	public boolean isIncrement() {
		return increment;
	}

	public void setIncrement(boolean anIncrement) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "increment", increment, anIncrement);
		command.execute();
	}

	public BoxModel getBoxModel() {
		return boxModel;
	}

	public void attach(BoxModel aBoxModel) {
		Command command = new AttachCommand(Manager.getSingleton()
				.getTransaction(), aBoxModel, this, (aBoxModel == null) ? null
				: aBoxModel.getItems(), "items", "boxModel");
		command.execute();
	}

	public void detach(BoxModel aBoxModel) {
		Command command = new DetachCommand(Manager.getSingleton()
				.getTransaction(), aBoxModel, this, (aBoxModel == null) ? null
				: aBoxModel.getItems(), "items", "boxModel");
		command.execute();
	}

	public TypeModel getTypeModel() {
		return typeModel;
	}

	public void setTypeModel(TypeModel aType) {
		this.detach(this.getTypeModel());
		this.attach(aType);
	}

	public void attach(TypeModel aTypeModel) {
		Command command = new AttachCommand(Manager.getSingleton()
				.getTransaction(), aTypeModel, this,
				(aTypeModel == null) ? null : aTypeModel.getItems(), "items",
				"typeModel");
		command.execute();
	}

	public void detach(TypeModel aTypeModel) {
		Command command = new DetachCommand(Manager.getSingleton()
				.getTransaction(), aTypeModel, this,
				(aTypeModel == null) ? null : aTypeModel.getItems(), "items",
				"typeModel");
		command.execute();
	}

	// overriden from EntityModel
	public void setName(String aName) {
		if (this.hasUniqueName(aName)) {
			super.setName(aName);
		}
	}

	public boolean hasUniqueName(String aName) {
		ItemModel itemModel;
		Collection c = boxModel.getItems();
		for (Iterator x = c.iterator(); x.hasNext();) {
			itemModel = (ItemModel) x.next();
			if (itemModel.getName().equals(aName)) {
				return false;
			}
		}
		return true;
	}

	public void copy(ItemModel item) {
		if (item != null) {
			this.setMin(item.getMin());
			this.setMax(item.getMax());
			this.setOid(item.isOid());
			this.setId(item.isId());
			this.setDefaultValue(item.getDefaultValue());
			this.setIncrement(item.isIncrement());

			this.setName(item.getName());
			this.setAlias(item.getAlias());
			this.setNameInPlural(item.getNameInPlural());
			this.setColor(item.getColor());
		}
	}

}
