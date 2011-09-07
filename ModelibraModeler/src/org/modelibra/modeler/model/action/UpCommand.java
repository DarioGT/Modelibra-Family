package org.modelibra.modeler.model.action;

import java.util.Collection;
import java.util.List;

import org.modelibra.modeler.model.EntityModel;
import org.modelibra.modeler.model.event.ModelEvent;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-10
 */
public class UpCommand extends Command {

	private EntityModel parent;

	private Collection<EntityModel> parentChildren;

	private String parentChildrenName;

	private int currentPosition;

	private boolean done = false;

	private boolean undone = false;

	public UpCommand(Transaction aTransaction, EntityModel parent,
			Collection parentChildren, String parentChildrenName,
			int currentPosition) {
		super(aTransaction);
		this.parent = parent;
		this.parentChildren = parentChildren;
		this.parentChildrenName = parentChildrenName;
		this.currentPosition = currentPosition;
	}

	public void execute() {
		if (getTransaction() == null) {
			// raise exception
			// System.out.println("execute: " + parent.getName() +
			// ", move up a member of " + parentChildrenName + " at " +
			// currentPosition);
			String errorMsg = "A command cannot be called outside a transaction.";
			CommandException ce = new CommandException(errorMsg);
			throw ce;
			// executeUpCommand();
		} else {
			basicDo();
		}
	}

	void undo() {
		if (done) {
			if (getTransaction() == null)
				undoUpCommand();
			else
				basicUndo();
		}
	}

	void redo() {
		if (undone) {
			if (getTransaction() == null)
				redoUpCommand();
			else
				basicDo();
		}
	}

	private List<EntityModel> getParentChildList() {
		return (List<EntityModel>) parentChildren;
	}

	private boolean moveDown() {
		if ((currentPosition < (parentChildren.size() - 1))
				&& (currentPosition >= 0)) {
			List<EntityModel> parentChildList = getParentChildList();
			EntityModel sel = parentChildList.get(currentPosition);
			EntityModel next = parentChildList.get(currentPosition + 1);
			parentChildList.set(currentPosition + 1, sel);
			parentChildList.set(currentPosition, next);
			currentPosition++;
			return true;
		}
		return false;
	}

	public boolean moveUp() {
		if ((currentPosition < parentChildren.size()) && (currentPosition > 0)) {
			List<EntityModel> parentChildList = getParentChildList();
			EntityModel sel = parentChildList.get(currentPosition);
			EntityModel prev = parentChildList.get(currentPosition - 1);
			parentChildList.set(currentPosition - 1, sel);
			parentChildList.set(currentPosition, prev);
			currentPosition--;
			return true;
		}
		return false;
	}

	private void basicDo() {
		try {
			// System.out.println("basicDo: " + parent.getName() +
			// ", move up a member of " + parentChildrenName + " at " +
			// currentPosition);
			done = moveUp();
		} catch (NullPointerException e) {
			System.out.println("basicDo: " + parent.getName()
					+ ", move up a member of " + parentChildrenName + " at "
					+ currentPosition + ", " + e.getMessage());
		}
	}

	private void basicUndo() {
		try {
			// System.out.println("basicUndo: " + parent.getName() +
			// ", move down a member of " + parentChildrenName + " at " +
			// currentPosition);
			undone = moveDown();
		} catch (NullPointerException e) {
			System.out.println("basicUndo: " + parent.getName()
					+ ", move down a member of " + parentChildrenName + " at "
					+ currentPosition + ", " + e.getMessage());
		}
	}

	/*
	 * private void executeUpCommand() { log(); basicDo(); if (done)
	 * parent.notifyObservers(new ModelEvent("moveUp", parentChildrenName, new
	 * Integer(currentPosition))); }
	 */

	private void undoUpCommand() {
		basicUndo();
		if (undone)
			parent.notifyObservers(new ModelEvent("moveDown",
					parentChildrenName, new Integer(currentPosition)));
	}

	private void redoUpCommand() {
		basicDo();
		if (done)
			parent.notifyObservers(new ModelEvent("moveUp", parentChildrenName,
					new Integer(currentPosition)));
	}

	void firePropagateToModel() {
		// propagates from model to model are not yet used!
	}

	void fireRefreshViews() {
		if (done)
			parent.notifyObservers(new ModelEvent("moveUp", parentChildrenName,
					new Integer(currentPosition)));
	}

	void fireRefreshViewsForUndo() {
		if (undone)
			parent.notifyObservers(new ModelEvent("moveDown",
					parentChildrenName, new Integer(currentPosition)));
	}

}