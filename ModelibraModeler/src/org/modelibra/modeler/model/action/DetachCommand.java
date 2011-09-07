package org.modelibra.modeler.model.action;

import java.util.Collection;

import org.modelibra.modeler.model.EntityModel;
import org.modelibra.modeler.model.event.ModelEvent;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-10
 */
public class DetachCommand extends Command {

	private EntityModel parent;

	private EntityModel child;

	private Collection<EntityModel> parentChildren;

	private String parentChildrenName;

	private String childParentName;

	private boolean added = false;

	private boolean removed = false;

	private boolean attached = false;

	private boolean detached = false;

	public DetachCommand(Transaction aTransaction, EntityModel parent,
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
			if (parent != null) {
				// System.out.println("execute: " + child.getName() +
				// ", detach from " + parent.getName());
			}
			String errorMsg = "A command cannot be called outside a transaction.";
			CommandException ce = new CommandException(errorMsg);
			throw ce;
			// executeDetachCommand();
		} else {
			basicDo();
		}
	}

	void undo() {
		if (getTransaction() == null)
			undoDetachCommand();
		else
			basicUndo();
	}

	void redo() {
		if (getTransaction() == null)
			redoDetachCommand();
		else
			basicDo();
	}

	public void basicDo() {
		try {
			if (((EntityModel) child.getProperty(childParentName)) == parent
					&& (parent != null)) {
				// System.out.println("basicDo: " + child.getName() +
				// ", detach from " + parent.getName());
				child.setProperty(childParentName, null);
				detached = true;

				if (parentChildren.contains(child)) {
					// System.out.println("basicDo: " + parent.getName() +
					// ", remove " + child.getName());
					parentChildren.remove(child);
					removed = true;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("basicDo: " + child.getName() + ", detach from "
					+ parent.getName() + ", " + e.getMessage());
		}
	}

	public void basicUndo() {
		try {
			if (((EntityModel) child.getProperty(childParentName)) == null
					&& (parent != null)) {
				// System.out.println("basicUndo: " + child.getName() +
				// ", attach to " + parent.getName());
				child.setProperty(childParentName, parent);
				attached = true;

				if (!parentChildren.contains(child)) {
					// System.out.println("basicUndo: " + parent.getName() +
					// ", add " + child.getName());
					parentChildren.add(child);
					added = true;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("basicUndo: " + child.getName() + ", attach to "
					+ parent.getName() + ", " + e.getMessage());
		}
	}

	/*
	private void executeDetachCommand() {
		log();
		basicDo();
		if (detached)
			child.notifyObservers(new ModelEvent("detach", childParentName,
					parent));
		if (removed)
			parent.notifyObservers(new ModelEvent("remove", parentChildrenName,
					child));
	}
	*/

	private void undoDetachCommand() {
		basicUndo();
		if (attached)
			child.notifyObservers(new ModelEvent("attach", childParentName,
					parent));
		if (added)
			parent.notifyObservers(new ModelEvent("add", parentChildrenName,
					child));
	}

	private void redoDetachCommand() {
		basicDo();
		if (detached)
			child.notifyObservers(new ModelEvent("detach", childParentName,
					parent));
		if (removed)
			parent.notifyObservers(new ModelEvent("remove", parentChildrenName,
					child));
	}

	void firePropagateToModel() {
		// propagates from model to model are not yet used!
	}

	void fireRefreshViews() {
		if (detached)
			child.notifyObservers(new ModelEvent("detach", childParentName,
					parent));
		if (removed)
			parent.notifyObservers(new ModelEvent("remove", parentChildrenName,
					child));
	}

	void fireRefreshViewsForUndo() {
		if (attached)
			child.notifyObservers(new ModelEvent("attach", childParentName,
					parent));
		if (added)
			parent.notifyObservers(new ModelEvent("add", parentChildrenName,
					child));
	}

}