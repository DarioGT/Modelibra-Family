package org.modelibra.modeler.app.session;

import org.modelibra.modeler.model.action.Transaction;
import org.modelibra.modeler.model.action.TransactionContext;
import org.modelibra.modeler.model.action.TransactionContextSupport;
import org.modelibra.modeler.model.action.TransactionListener;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2001-05-07
 */
public class Manager implements TransactionContext {

	private static Manager instance;

	private TransactionContextSupport delegate = new TransactionContextSupport();

	public Manager() {
		super();
	}

	public static Manager getSingleton() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

	public void startTransaction() {
		instance.delegate.startTransaction();
	}

	public void startTransaction(String aName) {
		instance.delegate.startTransaction(aName);
	}

	public void commit() {
		instance.delegate.commit();
	}

	public void rollback() {
		instance.delegate.rollback();
	}

	public Transaction getTransaction() {
		return instance.delegate.getTransaction();
	}

	public void addTransactionListener(TransactionListener l) {
		instance.delegate.addTransactionListener(l);
	}

	public void removeTransactionListener(TransactionListener l) {
		instance.delegate.removeTransactionListener(l);
	}

}