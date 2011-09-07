package modelibra.wicket.model;

import java.net.URL;
import java.util.Date;
import java.util.Locale;

import org.apache.wicket.Session;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.IDomainModel;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.Oid;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.exception.ConfigRuntimeException;
import org.modelibra.type.Email;
import org.modelibra.type.PropertyClass;
import org.modelibra.util.TextHandler;
import org.modelibra.util.Transformer;

@SuppressWarnings("serial")
public class EntityPropertyModel extends PropertyModel {

	// private static final long serialVersionUID = 1L;

	private boolean readOnly = true;

	private boolean shortText = false;

	public EntityPropertyModel(Object object, String propertyCode) {
		super(object, propertyCode);
		if (!(getTarget() instanceof IEntity<?>)) {
			throw new IllegalArgumentException(
					"object argument have to be either IEntity or IModel that wraps IEntity");
		}
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setShortText(boolean shortText) {
		this.shortText = shortText;
	}

	public boolean isShortText() {
		return shortText;
	}

	@Override
	public void setObject(Object object) {
		String propertyCode = getPropertyCode();
		getEntity().setProperty(propertyCode, object);
	}

	@Override
	public Object getObject() {
		return getPropertyValueText(getEntity());
	}

	public IEntity<?> getEntity() {
		return (IEntity<?>) getTarget();
	}

	public String getPropertyCode() {
		return propertyExpression();
	}

	public PropertyConfig getPropertyConfig() {
		return getEntity().getConceptConfig().getPropertyConfig(
				getPropertyCode());
	}

	public ConceptConfig getConceptConfig() {
		return getPropertyConfig().getConceptConfig();
	}

	public String getPropertyKey() {
		return getConceptConfig().getCode() + "."
				+ getPropertyConfig().getCode();
	}

	private String getPropertyValueText(IEntity<?> entity) {
		IDomainModel model = entity.getModel();
		ModelConfig modelConfig = model.getModelConfig();
		PropertyConfig propertyConfig = getPropertyConfig();
		if (propertyConfig == null) {
			throw new ConfigRuntimeException("wrong property code");
		}

		Locale locale = Session.get().getLocale();

		Class<?> propertyClass = propertyConfig.getPropertyClassObject();
		String propertyCode = propertyConfig.getCode();
		String propertyValueText = null;
		if (propertyConfig.isReference()) {
			String neighborCode = propertyConfig.getReferenceNeighbor();
			NeighborConfig neighborConfig = entity.getConceptConfig()
					.getNeighborsConfig().getNeighborConfig(neighborCode);
			String neighborConceptCode = neighborConfig.getDestinationConcept();
			IEntities<?> neighborEntities = model.getEntry(neighborConceptCode);
			if (neighborEntities != null) {
				Long referenceOid = (Long) entity.getProperty(propertyCode);
				Oid neighborParentOid = new Oid(referenceOid);
				IEntity<?> neighborParent = neighborEntities
						.retrieveByOid(neighborParentOid);
				if (neighborParent != null) {
					PropertyConfig uniquelPropertyConfig = neighborParent
							.getConceptConfig().getPropertiesConfig()
							.getFirstUniquePropertyConfig();
					if (uniquelPropertyConfig != null
							&& uniquelPropertyConfig.getPropertyClass().equals(
									PropertyClass.getString())) {
						propertyValueText = (String) neighborParent
								.getProperty(uniquelPropertyConfig.getCode());
					}
				}
			}
		} else if (propertyClass == String.class) {
			propertyValueText = (String) entity.getProperty(propertyCode);
			if (propertyValueText != null && shortText) {
				int shortTextLength = modelConfig.getDomainConfig()
						.getShortTextDefaultLengthInt();
				if (propertyValueText.length() > shortTextLength) {
					TextHandler textHandler = new TextHandler();
					propertyValueText = textHandler.extractBeginPlusThreeDots(
							propertyValueText, shortTextLength);
				}
			}
			if (propertyConfig.isScramble()) {
				propertyValueText = "********";
			}
		} else if (propertyClass == Integer.class) {
			Integer propertyValue = (Integer) entity.getProperty(propertyCode);
			propertyValueText = Transformer.string(propertyValue);
		} else if (propertyClass == Long.class) {
			Long propertyValue = (Long) entity.getProperty(propertyCode);
			propertyValueText = Transformer.string(propertyValue);
		} else if (propertyClass == Float.class) {
			Float propertyValue = (Float) entity.getProperty(propertyCode);
			propertyValueText = Transformer.string(propertyValue, locale);
		} else if (propertyClass == Double.class) {
			Double propertyValue = (Double) entity.getProperty(propertyCode);
			propertyValueText = Transformer.string(propertyValue, locale);
		} else if (propertyClass == Boolean.class) {
			Boolean propertyValue = (Boolean) entity.getProperty(propertyCode);
			propertyValueText = Transformer.string(propertyValue);
		} else if (propertyClass == Date.class) {
			Date propertyValue = (Date) entity.getProperty(propertyCode);
			String datePattern = modelConfig.getDatePattern();
			if (propertyValue == null) {
				propertyValueText = ""; // add entity produces NPE in
										// Transformer -- quick fix
			} else {
				propertyValueText = Transformer.string(propertyValue,
						datePattern);
			}
		} else if (propertyClass == URL.class) {
			URL propertyValue = (URL) entity.getProperty(propertyCode);
			propertyValueText = Transformer.string(propertyValue);
		} else if (propertyClass == Email.class) {
			Email propertyValue = (Email) entity.getProperty(propertyCode);
			propertyValueText = Transformer.string(propertyValue);
		} else {
			Object propertyValue = entity.getProperty(propertyCode);
			if (propertyValue != null) {
				propertyValueText = propertyValue.toString();
			}
		}
		return propertyValueText;
	}

}
