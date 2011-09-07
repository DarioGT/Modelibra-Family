package modelibra;

import modelibra.designer.Designer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.config.DomainConfig;
import org.modelibra.persistency.IPersistentModel;
import org.modelibra.persistency.PersistentEntities;
import org.modelibra.persistency.xml.XmlEntities;

public class ModelibraData {

	private static Log log = LogFactory.getLog(ModelibraData.class);

	public static final String DOMAINS = "MetaDomains";
	public static final String TYPES = "MetaTypes";

	private static ModelibraData modelibraData;

	private DomainConfig domainConfig;

	private Modelibra modelibra;

	private PersistentModelibra persistentModelibra;

	private ModelibraData() {
		ModelibraConfig modelibraConfig = new ModelibraConfig();
		domainConfig = modelibraConfig.getDomainConfig();
		modelibra = new Modelibra(domainConfig);
		persistentModelibra = new PersistentModelibra(modelibra);
		getDesigner().setInitialized(true);
	}

	public static ModelibraData getOne() {
		if (modelibraData == null) {
			modelibraData = new ModelibraData();
		}
		return modelibraData;
	}

	public Designer getDesigner() {
		return getModelibra().getDesigner();
	}

	public void loadDomains(String filePath) {
		load(DOMAINS, filePath);
	}

	public void saveDomains(String filePath) {
		save(DOMAINS, filePath);
	}

	public void loadTypes(String filePath) {
		load(TYPES, filePath);
	}

	public void saveTypes(String filePath) {
		save(TYPES, filePath);
	}

	private void load(String entryCode, String filePath) {
		try {
			XmlEntities<?> data = getXmlEntry(entryCode);
			if (filePath != null) {
				data.setDataFilePath(filePath);
				getDesigner().setInitialized(false);
				data.load();
				getDesigner().setInitialized(true);
			}
		} catch (Exception e) {
			log.error("Load data error from ModelibraData.load: "
					+ e.getMessage());
		}
	}

	private void save(String entryCode, String filePath) {
		try {
			XmlEntities<?> data = getXmlEntry(entryCode);
			if (filePath != null) {
				data.setDataFilePath(filePath);
				data.save();
			}
		} catch (Exception e) {
			log.error("Load data error from ModelibraData.save: "
					+ e.getMessage());
		}
	}

	public Modelibra getModelibra() {
		return modelibra;
	}

	private PersistentModelibra getPersistentModelibra() {
		return persistentModelibra;
	}

	private IPersistentModel getPersistentDesigner() {
		String modelCode = "Designer";
		IPersistentModel persistentDesigner = getPersistentModelibra()
				.getPersistentModels().getPersistentModel(modelCode);
		return persistentDesigner;
	}

	private XmlEntities<?> getXmlEntry(String entryCode) {
		PersistentEntities persistentEntry = (PersistentEntities) getPersistentDesigner()
				.getPersistentEntry(entryCode);
		return (XmlEntities<?>) persistentEntry.getStoreEntities();
	}

	public void close() {
		if (getPersistentModelibra() != null) {
			getPersistentModelibra().close();
		}
	}

}
