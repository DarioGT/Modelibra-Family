package dmeduc;

import org.modelibra.persistency.PersistentDomain;

/**
 * Creates the persistent domain.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-02
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
