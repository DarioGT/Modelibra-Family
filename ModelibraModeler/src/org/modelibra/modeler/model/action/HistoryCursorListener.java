package org.modelibra.modeler.model.action;

import java.util.EventObject;

/**
 * 
 * @author Dzenan Ridjanovic
 * @version 2001-05-07
 */
public interface HistoryCursorListener {

	public void lastReached(EventObject e);

	public void firstReached(EventObject e);

	public void inBetween(EventObject e);

}