package dmeduc.wicket.app;

import org.modelibra.wicket.app.DomainApp;

import dmeduc.DmEduc;
import dmeduc.DmEducConfig;
import dmeduc.PersistentDmEduc;

/**
 * Domain web application.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-04-06
 */
public class DmEducApp extends DomainApp {

	private DmEduc dmEduc;

	/**
	 * Constructs the domain web application.
	 */
	public DmEducApp() {
		super();
		dmEduc = new DmEduc(new DmEducConfig().getDomainConfig());
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
