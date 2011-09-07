package org.modelibra.modeler.model.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-10
 */
public class Transaction extends Command {

	private Collection<Command> commands = new ArrayList<Command>();

	public Transaction() {
		super(null);
	}

	public Transaction(String aName) {
		super(null, aName);
	}

	public Collection getCommands() {
		return commands;
	}

	public void add(Command aCommand) {
		if (!commands.contains(aCommand)) {
			commands.add(aCommand);
			aCommand.attach(this);
		}
	}

	public void remove(Command aCommand) {
		if (commands.contains(aCommand)) {
			commands.remove(aCommand);
			aCommand.detach(this);
		}
	}

	public void execute() {
		Command command = null;
		Collection c = commands;

		for (Iterator x = c.iterator(); x.hasNext();) {
			command = (Command) x.next();
			command.firePropagateToModel();
		}
		// to do reexecute over logged commands from firePropagateToModel()
		c = commands;

		for (Iterator x = c.iterator(); x.hasNext();) {
			command = (Command) x.next();
			command.fireRefreshViews();
		}
	}

	public void undo() {
		Command command = null;
		Collection c = commands;
		for (Iterator x = c.iterator(); x.hasNext();) {
			command = (Command) x.next();
			command.undo();
		}
		c = commands;
		for (Iterator x = c.iterator(); x.hasNext();) {
			command = (Command) x.next();
			command.fireRefreshViewsForUndo();
		}
	}

	public void redo() {
		Command command = null;
		Collection c = commands;
		for (Iterator x = c.iterator(); x.hasNext();) {
			command = (Command) x.next();
			command.redo();
		}
		c = commands;
		for (Iterator x = c.iterator(); x.hasNext();) {
			command = (Command) x.next();
			command.fireRefreshViews();
		}
	}

	public void firePropagateToModel() {
		Command command = null;
		Collection c = commands;
		for (Iterator x = c.iterator(); x.hasNext();) {
			command = (Command) x.next();
			command.firePropagateToModel();
		}
	}

	public void fireRefreshViews() {
		Command command = null;
		Collection c = commands;
		for (Iterator x = c.iterator(); x.hasNext();) {
			command = (Command) x.next();
			command.fireRefreshViews();
		}
	}

	public void fireRefreshViewsForUndo() {
		Command command = null;
		Collection c = commands;
		for (Iterator x = c.iterator(); x.hasNext();) {
			command = (Command) x.next();
			command.fireRefreshViewsForUndo();
		}
	}

}