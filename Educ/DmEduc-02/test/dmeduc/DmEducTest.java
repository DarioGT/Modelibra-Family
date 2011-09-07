package dmeduc;

import org.modelibra.config.DomainConfig;
import org.modelibra.config.ModelConfig;

/**
 * Domain tests. In general, there may be more than one domain model.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-19
 */
public class DmEducTest {

	private static DmEducTest dmEducTest;

	private DmEduc dmEduc;

	private PersistentDmEduc persistentDmEduc;

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
		for (ModelConfig modelConfig : domainConfig.getModelsConfig()) {
			modelConfig.setPersistenceRelativePath(modelConfig
					.getPersistenceRelativePath()
					+ DmEducConfig.SEPARATOR
					+ dmEducConfig.getModelibraProperties()
							.getTestDirectoryName());
		}
		dmEduc = new DmEduc(domainConfig);
		persistentDmEduc = new PersistentDmEduc(dmEduc);
	}

	public DmEduc getDmEduc() {
		return dmEduc;
	}

	public void close() {
		if (persistentDmEduc != null) {
			persistentDmEduc.close();
		}
	}

}
