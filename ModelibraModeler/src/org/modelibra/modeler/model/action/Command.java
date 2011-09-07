package org.modelibra.modeler.model.action;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2001-05-07
 */
public abstract class Command {

	private String name = "";

	private Transaction transaction = null;

	protected Command(Transaction aTransaction) {
		super();
		this.attach(aTransaction);
	}

	protected Command(Transaction aTransaction, String aName) {
		this(aTransaction);
		name = aName;
	}

	public void setName(String aName) {
		name = aName;
	}

	public String getName() {
		return name;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void attach(Transaction aTransaction) {
		if ((aTransaction != null) && (transaction == null)) {
			transaction = aTransaction;
			transaction.add(this);
		}
	}

	public void detach(Transaction aTransaction) {
		if ((aTransaction != null) && (transaction == aTransaction)) {
			Transaction oldTransaction = transaction;
			transaction = null;
			if (oldTransaction != null) {
				oldTransaction.remove(this);
			}
		}
	}

	public abstract void execute();

	abstract void undo();

	/**
	 * provides a default behavior by calling execute. Can be overridden if the
	 * behavior is different from a simple call to execute.
	 */

	void redo() {
		this.execute();
	}

	protected void log() {
		History.getSingleton().add(this);
	}

	protected void unlog() {
		History.getSingleton().remove(this);
	}

	abstract void firePropagateToModel();

	abstract void fireRefreshViews();

	abstract void fireRefreshViewsForUndo();
}