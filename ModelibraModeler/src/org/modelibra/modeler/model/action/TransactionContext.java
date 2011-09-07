package org.modelibra.modeler.model.action;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2001-05-07
 */
public interface TransactionContext {

	public void startTransaction();

	public void startTransaction(String aName);

	public void commit();

	public void rollback();

	public Transaction getTransaction();

	public void addTransactionListener(TransactionListener l);

	public void removeTransactionListener(TransactionListener l);
}