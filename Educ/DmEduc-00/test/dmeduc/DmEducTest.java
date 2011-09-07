package dmeduc;

import org.modelibra.config.DomainConfig;

/**
 * Domain tests. In general, there may be more than one domain model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-19
 */
public class DmEducTest {

	private static DmEducTest dmEducTest;

	private DmEduc dmEduc;

	private DmEducTest() {
		super();
		open();
	}

	public static DmEducTest getSingleton() {
		if (dmEducTest == null) {
			dmEducTest = new DmEducTest();
		}
		return dmEducTest;
	}

	private void open() {
		DmEducConfig dmEducConfig = new DmEducConfig();
		DomainConfig domainConfig = dmEducConfig.getDomainConfig();
		dmEduc = new DmEduc(domainConfig);
	}

	public DmEduc getDmEduc() {
		return dmEduc;
	}

}
