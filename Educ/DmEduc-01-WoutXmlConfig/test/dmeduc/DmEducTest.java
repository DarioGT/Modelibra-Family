package dmeduc;

import org.modelibra.config.DomainConfig;

/**
 * Domain tests. In general, there may be more than one domain model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-11-24
 */
public class DmEducTest {

	private static DmEducTest dmEducTest;

	private DmEduc dmEduc;

	private DmEducTest() {
		open();
	}

	public static DmEducTest getSingleton() {
		if (dmEducTest == null) {
			dmEducTest = new DmEducTest();
		}
		return dmEducTest;
	}

	private void open() {
		DmEducSpecificConfig dmEducConfig = new DmEducSpecificConfig();
		DomainConfig domainConfig = dmEducConfig.getSpecificDomainConfig();
		dmEduc = new DmEduc(domainConfig);
		new PersistentDmEduc(dmEduc);
	}

	public DmEduc getDmEduc() {
		return dmEduc;
	}

}
