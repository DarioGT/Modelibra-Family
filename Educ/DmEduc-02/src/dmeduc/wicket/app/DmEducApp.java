package dmeduc.wicket.app;

import org.modelibra.wicket.app.DomainApp;

import dmeduc.DmEduc;
import dmeduc.DmEducConfig;
import dmeduc.PersistentDmEduc;

/**
 * Domain web application.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-07-02
 */
@SuppressWarnings("serial")
public class DmEducApp extends DomainApp {

	private DmEduc dmEduc;

	/**
	 * Constructs the domain web application.
	 */
	public DmEducApp() {
		super();
		DmEducConfig dmEducConfig = new DmEducConfig();
		dmEduc = new DmEduc(dmEducConfig.getDomainConfig());
		setPersistentDomain(new PersistentDmEduc(dmEduc));
	}

	/**
	 * Gets the dmEduc domain.
	 * 
	 * @return dmEduc domain
	 */
	public DmEduc getDmEduc() {
		return dmEduc;
	}

}
