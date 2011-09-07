package org.modelibra.modeler.model;

import org.modelibra.modeler.app.session.Manager;
import org.modelibra.modeler.model.action.Command;
import org.modelibra.modeler.model.action.SetCommand;
import org.modelibra.modeler.model.event.ModelEvent;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2001-05-07
 */
public abstract class ElementModel extends EntityModel {

	protected boolean visible = true;

	protected boolean selected = false;

	public ElementModel() {
		super();
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean aVisible) {
		Command command = new SetCommand(Manager.getSingleton()
				.getTransaction(), this, "visible", new Boolean(visible),
				new Boolean(aVisible));
		command.execute();
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean aSelected) {
		// This action happens often. Should it be a command?
		selected = aSelected;
		this.notifyObservers(new ModelEvent("set", "selected", new Boolean(
				aSelected)));
		/*
		 * Command command = new
		 * SetCommand(Manager.getSingleton().getTransaction(), this, "selected",
		 * new Boolean(selected), new Boolean(aSelected)); command.execute();
		 */
	}

	protected void copy(ElementModel elementModel) {
		if (elementModel != null) {
			this.setVisible(elementModel.isVisible());
			this.setSelected(elementModel.isSelected());
			super.copy(elementModel);
		}
	}

}