package org.modelibra.modeler.model.action;

import java.util.Collection;

import org.modelibra.modeler.model.EntityModel;
import org.modelibra.modeler.model.event.ModelEvent;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-09
 */
public class AddCommand extends Command {

	private EntityModel parent;

	private EntityModel child;

	private Collection<EntityModel> parentChildren;

	private String parentChildrenName;

	private String childParentName;

	private boolean added = false;

	private boolean removed = false;

	private boolean attached = false;

	private boolean detached = false;

	public AddCommand(Transaction aTransaction, EntityModel parent,
			EntityModel child, Collection parentChildren,
			String parentChildrenName, String childParentName) {
		super(aTransaction);
		this.parent = parent;
		this.child = child;
		this.parentChildren = parentChildren;
		this.parentChildrenName = parentChildrenName;
		this.childParentName = childParentName;
	}

	public void execute() {
		if (getTransaction() == null) {
			// raise exception
			// System.out.println("execute: " + parent.getName() +
			// ", add " + child.getName());
			String errorMsg = "A command cannot be called outside a transaction.";
			CommandException ce = new CommandException(errorMsg);
			throw ce;
			// executeAddCommand();
		} else {
			basicDo();
		}
	}

	void undo() {
		if (getTransaction() == null)
			undoAddCommand();
		else
			basicUndo();
	}

	void redo() {
		if (getTransaction() == null)
			redoAddCommand();
		else
			basicDo();
	}

	private void basicDo() {
		try {
			if (!parentChildren.contains(child)) {
				// System.out.println("basicDo: " + parent.getName() +
				// ", add " + child.getName());
				parentChildren.add(child); // e.g., boxes.add(aBoxModel);
				added = true;

				if (((EntityModel) child.getProperty(childParentName)) != parent) {
					// System.out.println("basicDo: " + child.getName() +
					// ", attach to " + parent.getName());
					child.setProperty(childParentName, parent);
					attached = true;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("basicDo: " + parent.getName() + ", add "
					+ child.getName() + ", " + e.getMessage());
		}
	}

	private void basicUndo() {
		try {
			if (parentChildren.contains(child)) {
				// System.out.println("basicUndo: " + parent.getName() +
				// ", remove " + child.getName());
				parentChildren.remove(child); // e.g.,
				// boxes.remove(aBoxModel);
				removed = true;

				if (((EntityModel) child.getProperty(childParentName)) == parent) {
					// System.out.println("basicUndo: " + child.getName() +
					// ", detach from " + parent.getName());
					child.setProperty(childParentName, null);
					detached = true;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("basicUndo: " + parent.getName() + ", remove "
					+ child.getName() + ", " + e.getMessage());
		}
	}

	/*
	 * private void executeAddCommand() { log(); basicDo(); if (added)
	 * parent.notifyObservers(new ModelEvent("add", parentChildrenName, child));
	 * if (attached) child.notifyObservers(new ModelEvent("attach",
	 * childParentName, parent)); }
	 */

	private void undoAddCommand() {
		basicUndo();
		if (removed)
			parent.notifyObservers(new ModelEvent("remove", parentChildrenName,
					child));
		if (detached)
			child.notifyObservers(new ModelEvent("detach", childParentName,
					parent));
	}

	private void redoAddCommand() {
		basicDo();
		if (added)
			parent.notifyObservers(new ModelEvent("add", parentChildrenName,
					child));
		if (attached)
			child.notifyObservers(new ModelEvent("attach", childParentName,
					parent));
	}

	void firePropagateToModel() {
		// propagates from model to model are not yet used!
	}

	void fireRefreshViews() {
		if (added)
			parent.notifyObservers(new ModelEvent("add", parentChildrenName,
					child));
		if (attached)
			child.notifyObservers(new ModelEvent("attach", childParentName,
					parent));
	}

	void fireRefreshViewsForUndo() {
		if (removed)
			parent.notifyObservers(new ModelEvent("remove", parentChildrenName,
					child));
		if (detached)
			child.notifyObservers(new ModelEvent("detach", childParentName,
					parent));
	}

}