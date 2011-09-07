package org.modelibra.modeler.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.model.action.AddCommand;
import org.modelibra.modeler.model.action.AttachCommand;
import org.modelibra.modeler.model.action.Command;
import org.modelibra.modeler.model.action.DetachCommand;
import org.modelibra.modeler.model.action.DownCommand;
import org.modelibra.modeler.model.action.RemoveCommand;
import org.modelibra.modeler.model.action.SetCommand;
import org.modelibra.modeler.model.action.UpCommand;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-10
 */
public class TypeModel extends EntityModel {

	static final long serialVersionUID = 7168319479760000390L;

	protected String dbmsType = new String("?");

	protected int length = 0;

	protected int noOfDec = 0;

	protected AppModel appModel;

	protected Collection<ItemModel> items = new ArrayList<ItemModel>();

	public TypeModel(AppModel anAppModel) {
		super();
		this.attach(anAppModel);
	}

	public String getDbmsType() {
		return dbmsType;
	}

	public String getJavaClass() {
		return this.getAlias();
	}

	public void setDbmsType(String aBaseType) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "dbmsType", dbmsType, aBaseType);
		command.execute();
	}

	public int getLength() {
		return length;
	}

	public void setLength(int aLength) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "length", new Integer(length),
				new Integer(aLength));
		command.execute();
	}

	public int getNoOfDec() {
		return noOfDec;
	}

	public void setNoOfDec(int aNoOfDec) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "noOfDec", new Integer(noOfDec),
				new Integer(aNoOfDec));
		command.execute();
	}

	public AppModel getAppModel() {
		return appModel;
	}

	// overriden from EntityModel
	public void setName(String aName) {
		if (this.hasUniqueName(aName)) {
			super.setName(aName);
		}
	}

	public boolean hasUniqueName(String aName) {
		TypeModel typeModel;
		Collection c = appModel.getTypes();
		for (Iterator x = c.iterator(); x.hasNext();) {
			typeModel = (TypeModel) x.next();
			if (typeModel.getName().equals(aName)) {
				return false;
			}
		}
		return true;
	}

	public Collection<ItemModel> getItems() {
		return items;
	}

	public List<ItemModel> getItemList() {
		return (List<ItemModel>) items;
	}

	public void add(ItemModel anItemModel) {
		Command command = new AddCommand(Manager.getSingleton()
				.getTransaction(), this, anItemModel, items, "items",
				"typeModel");
		command.execute();
	}

	public void remove(ItemModel anItemModel) {
		Command command = new RemoveCommand(Manager.getSingleton()
				.getTransaction(), this, anItemModel, items, "items",
				"typeModel");
		command.execute();
	}

	public void removeAllItems() {
		ItemModel itemModel;
		Collection c = this.items;
		for (Iterator x = c.iterator(); x.hasNext();) {
			itemModel = (ItemModel) x.next();
			x.remove();
			itemModel.detach(this);
		}
	}

	public ItemModel getItem(int ix) {
		return (ItemModel) getItemList().get(ix);
	}

	public ItemModel getItem(ItemModel item) {
		int ix = getItemList().indexOf(item);
		return this.getItem(ix);
	}

	public void moveItemUp(int ix) {
		Command command = new UpCommand(
				Manager.getSingleton().getTransaction(), this, items, "items",
				ix);
		command.execute();
	}

	public void moveItemDown(int ix) {
		Command command = new DownCommand(Manager.getSingleton()
				.getTransaction(), this, items, "items", ix);
		command.execute();
	}

	public void moveItemUp(ItemModel item) {
		int ix = getItemList().indexOf(item);
		this.moveItemUp(ix);
	}

	public void moveItemDown(ItemModel item) {
		int ix = getItemList().indexOf(item);
		this.moveItemDown(ix);
	}

	public void attach(AppModel anAppModel) {
		Command command = new AttachCommand(Manager.getSingleton()
				.getTransaction(), anAppModel, this,
				(anAppModel == null) ? null : anAppModel.getTypes(), "types",
				"appModel");
		command.execute();
	}

	public void detach(AppModel anAppModel) {
		Command command = new DetachCommand(Manager.getSingleton()
				.getTransaction(), anAppModel, this,
				(anAppModel == null) ? null : anAppModel.getTypes(), "types",
				"appModel");
		command.execute();
	}

}