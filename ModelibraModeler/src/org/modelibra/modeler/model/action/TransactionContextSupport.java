package org.modelibra.modeler.model.action;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-12-10
 */
public class TransactionContextSupport implements TransactionContext {

	private Transaction transaction = null;

	private boolean transactionStarted = false;

	private Collection<TransactionListener> transactionListeners = new ArrayList<TransactionListener>();

	public TransactionContextSupport() {
		super();
	}

	public void startTransaction() {
		startTransaction("");
	}

	public void startTransaction(String aName) {
		if (!transactionStarted) {
			// System.out.println("Transaction started
			// ------------------------------");
			transaction = new Transaction(aName);
			transactionStarted = true;
		}
	}

	public void commit() {
		if (transactionStarted) {
			// System.out.println("Transaction commited
			// -----------------------------");
			transaction.log();
			transaction.execute();
			transactionStarted = false;
			transaction = null;
		}
	}

	public void rollback() {
		if (transactionStarted) {
			// System.out.println("Transaction rollbacked
			// ---------------------------");
			transaction.unlog();
			transactionStarted = false;
			transaction = null;
		}
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public synchronized void addTransactionListener(TransactionListener tl) {
		if (!transactionListeners.contains(tl)) {
			transactionListeners.add(tl);
		}
	}

	public synchronized void removeTransactionListener(TransactionListener tl) {
		if (transactionListeners.contains(tl)) {
			transactionListeners.remove(tl);
		}
	}

	/*
	 * private void fireTransactionStarted() { Collection targets;
	 * TransactionListener target; synchronized (this) { targets = (Collection)
	 * transactionListeners.clone(); } EventObject evt = new EventObject(this);
	 * for (Iterator x = targets.iterator(); x.hasNext();) { target =
	 * (TransactionListener) x.next(); target.transactionStarted(evt); } }
	 */

	/*
	 * private void fireTransactionCommitted() { Collection targets;
	 * TransactionListener target; synchronized (this) { targets = (Collection)
	 * transactionListeners.clone(); } EventObject evt = new EventObject(this);
	 * for (Iterator x = targets.iterator(); x.hasNext();) { target =
	 * (TransactionListener) x.next(); target.transactionCommitted(evt); } }
	 */

}