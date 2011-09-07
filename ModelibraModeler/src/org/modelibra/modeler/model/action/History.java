package org.modelibra.modeler.model.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-10
 */
public class History {

	static History instance;

	private int cursor = 0;

	private Collection<Command> commands = new ArrayList<Command>();

	private Collection<HistoryCursorListener> historyCursorListeners = new ArrayList<HistoryCursorListener>();

	private History() {
		super();
	}

	public static History getSingleton() {
		if (instance == null) {
			instance = new History();
		}
		return instance;
	}

	public void add(Command aCommand) {
		if (!instance.commands.contains(aCommand)) {
			instance.removeAllCommandsToTheRightOfCursor();
			instance.commands.add(aCommand);
			instance.moveCursorForward();
			if (aCommand instanceof Transaction) {
				// System.out.println("addTransactionToHistory: " +
				// aCommand.getName() + ", " +
				// "cursor is " + instance.cursor + ", size is " +
				// instance.commands.size());
			} else {
				// System.out.println("addCommandToHistory: " +
				// "cursor is " + instance.cursor + ", size is " +
				// instance.commands.size());
			}
		}
	}

	public void remove(Command aCommand) {
		if (instance.commands.contains(aCommand)) {
			instance.removeAllCommandsToTheRightOfCursor();
			instance.commands.remove(aCommand);
			instance.moveCursorBackward();
		}
	}

	private void removeAllCommandsToTheRightOfCursor() {
		for (int i = instance.commands.size() - 1; i >= instance.cursor; i--) {
			instance.commands.remove(i);
		}
	}

	public void reset() {
		instance.cursor = 0;
		instance.commands.removeAll(commands);
		fireFirstReached();
		fireLastReached();
		// System.out.println("History reset
		// ======================================");
	}

	private void moveCursorForward() {
		instance.cursor++;
		if (instance.commands.size() > 0) {
			instance.fireInBetween();
		}
		if (instance.cursor == instance.commands.size()) {
			instance.fireLastReached();
		}
	}

	private void moveCursorBackward() {
		if (instance.cursor == 0) {
			return;
		}
		instance.cursor--;
		if (instance.commands.size() > 0) {
			instance.fireInBetween();
		}
		if (instance.cursor == 0) {
			instance.fireFirstReached();
		}
	}
	
	private List<Command> getCommandList() {
		return (List<Command>) commands;
	}
	
	private ArrayList<HistoryCursorListener> getHistoryCursorListenerList() {
		return (ArrayList<HistoryCursorListener>) historyCursorListeners;
	}

	private void fireLastReached() {
		Collection targets;
		HistoryCursorListener target;
		synchronized (this) {
			targets = (Collection) instance.getHistoryCursorListenerList().clone();
		}
		EventObject evt = new EventObject(this);
		for (Iterator x = targets.iterator(); x.hasNext();) {
			target = (HistoryCursorListener) x.next();
			target.lastReached(evt);
		}
	}

	private void fireFirstReached() {
		Collection targets;
		HistoryCursorListener target;
		synchronized (this) {
			targets = (Collection) instance.getHistoryCursorListenerList().clone();
		}
		EventObject evt = new EventObject(this);
		for (Iterator x = targets.iterator(); x.hasNext();) {
			target = (HistoryCursorListener) x.next();
			target.firstReached(evt);
		}
	}

	private void fireInBetween() {
		Collection targets;
		HistoryCursorListener target;
		synchronized (this) {
			targets = (Collection) instance.getHistoryCursorListenerList().clone();
		}
		EventObject evt = new EventObject(this);
		for (Iterator x = targets.iterator(); x.hasNext();) {
			target = (HistoryCursorListener) x.next();
			target.inBetween(evt);
		}
	}

	public void undo() {
		if (instance.commands.size() > 0) {
			instance.moveCursorBackward();
			Command command = (Command) instance.getCommandList().get(cursor);
			// System.out.println("undoInHistory: " +
			// "cursor is " + instance.cursor + ", size is " +
			// instance.commands.size());
			command.undo();
		}
	}

	public void redo() {
		if (instance.commands.size() > 0) {
			Command command = (Command) instance.getCommandList().get(cursor);
			// System.out.println("redoInHistory: " +
			// "cursor is " + (instance.cursor + 1) + ", size is " +
			// instance.commands.size());
			command.redo();
			instance.moveCursorForward();
		}
	}

	public String getNextUndoableName() {
		if ((instance.commands.size() > 0) && (instance.cursor > 0)) {
			return ((Command) instance.getCommandList().get(instance.cursor - 1))
					.getName();
		}
		return null;
	}

	public String getNextRedoableName() {
		if (instance.commands.size() > 0) {
			return ((Command) instance.getCommandList().get(instance.cursor)).getName();
		}
		return null;
	}

	public void addHistoryCursorListener(HistoryCursorListener hcl) {
		if (!instance.historyCursorListeners.contains(hcl)) {
			instance.historyCursorListeners.add(hcl);
		}
	}

	public void removeHistoryCursorListener(HistoryCursorListener hcl) {
		if (instance.historyCursorListeners.contains(hcl)) {
			instance.historyCursorListeners.remove(hcl);
		}
	}

}
