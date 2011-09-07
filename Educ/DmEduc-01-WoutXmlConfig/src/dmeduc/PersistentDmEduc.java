package dmeduc;

import org.modelibra.persistency.PersistentDomain;

/**
 * Creates the persistent domain.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-11-24
 */
@SuppressWarnings("serial")
public class PersistentDmEduc extends PersistentDomain {

	/**
	 * Constructs the persistent domain.
	 */
	public PersistentDmEduc(DmEduc dmEduc) {
		super(dmEduc);
	}

}
