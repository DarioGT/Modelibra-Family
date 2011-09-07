package org.modelibra.modeler.model.action;

import java.util.EventObject;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2001-05-07
 */
public interface TransactionListener {

	public void transactionStarted(EventObject e);

	public void transactionCommitted(EventObject e);

}