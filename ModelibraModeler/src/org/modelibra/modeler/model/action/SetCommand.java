package org.modelibra.modeler.model.action;

import org.modelibra.modeler.model.EntityModel;
import org.modelibra.modeler.model.event.ModelEvent;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2001-05-07
 */
public class SetCommand extends Command {

	private EntityModel receiverEntity;

	private String propertyName;

	private Object oldValue;

	private Object newValue;

	private boolean done = false;

	private boolean undone = false;

	public SetCommand(Transaction aTransaction, EntityModel receiverEntity,
			String propertyName, Object oldValue, Object newValue) {
		super(aTransaction);
		this.receiverEntity = receiverEntity;
		this.propertyName = propertyName;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public void execute() {
		if (getTransaction() == null) {
			// raise exception
			// System.out.println("execute: " + receiverEntity.getName() +
			// ", set " + propertyName);
			String errorMsg = "A command cannot be called outside a transaction.";
			CommandException ce = new CommandException(errorMsg);
			throw ce;
			// executeSetCommand();
		} else {
			basicDo();
		}
	}

	void undo() {
		if (getTransaction() == null)
			undoSetCommand();
		else
			basicUndo();
	}

	void redo() {
		if (getTransaction() == null)
			redoSetCommand();
		else
			basicDo();
	}

	private void basicDo() {
		try {
			if ((receiverEntity.getProperty(propertyName)) != newValue) {
				// System.out.println("basicDo: " + receiverEntity.getName() +
				// ", set " + propertyName);
				receiverEntity.setProperty(propertyName, newValue);
				done = true;
			}
		} catch (NullPointerException e) {
			System.out.println("basicDo: " + receiverEntity.getName()
					+ ", set " + propertyName + ", " + e.getMessage());
		}
	}

	private void basicUndo() {
		try {
			if ((receiverEntity.getProperty(propertyName)) != oldValue) {
				// System.out.println("basicUndo: " + receiverEntity.getName() +
				// ", set " + propertyName);
				receiverEntity.setProperty(propertyName, oldValue);
				undone = true;
			}
		} catch (NullPointerException e) {
			System.out.println("basicUndo: " + receiverEntity.getName()
					+ ", set " + propertyName + ", " + e.getMessage());
		}
	}

	/*
	private void executeSetCommand() {
		log();
		basicDo();
		if (done)
			receiverEntity.notifyObservers(new ModelEvent("set", propertyName,
					newValue));
	}
	*/

	private void undoSetCommand() {
		basicUndo();
		if (undone)
			receiverEntity.notifyObservers(new ModelEvent("set", propertyName,
					oldValue));
	}

	private void redoSetCommand() {
		basicDo();
		if (done)
			receiverEntity.notifyObservers(new ModelEvent("set", propertyName,
					newValue));
	}

	void firePropagateToModel() {
		// propagates from model to model are not yet used!
	}

	void fireRefreshViews() {
		if (done)
			receiverEntity.notifyObservers(new ModelEvent("set", propertyName,
					newValue));
	}

	void fireRefreshViewsForUndo() {
		if (undone)
			receiverEntity.notifyObservers(new ModelEvent("set", propertyName,
					oldValue));
	}

}