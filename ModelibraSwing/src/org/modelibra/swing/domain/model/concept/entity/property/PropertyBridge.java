package org.modelibra.swing.domain.model.concept.entity.property;

import java.net.URL;
import java.util.Date;

import org.modelibra.IEntity;
import org.modelibra.config.PropertyConfig;
import org.modelibra.exception.TypeRuntimeException;
import org.modelibra.type.Email;
import org.modelibra.type.PropertyClass;
import org.modelibra.util.Transformer;

public class PropertyBridge {

	private IEntity<?> entity;

	private PropertyConfig propertyConfig;

	private boolean getProperty;

	private boolean setProperty;

	public PropertyBridge(IEntity<?> entity, PropertyConfig propertyConfig) {
		this.entity = entity;
		this.propertyConfig = propertyConfig;
	}

	public boolean isGetProperty() {
		return getProperty;
	}

	public boolean isSetProperty() {
		return setProperty;
	}

	public String getProperty() {
		String text = null;
		PropertyConfig propertyConfig = getPropertyConfig();
		try {
			if (propertyConfig.isDisplay() && !propertyConfig.isReference()) {
				Object propertyValueObject = getEntity()
						.getProperty(getPropertyName());
				text = Transformer.string(propertyValueObject);
				getProperty = true;
			} else {
				getProperty = false;
				text = "";
			}
		} catch (TypeRuntimeException e) {
			getProperty = false;
			text = "";
		}
		return text;
	}

	public void setProperty(String propertyString) {
		PropertyConfig propertyConfig = getPropertyConfig();
		try {
			if (propertyConfig.isUpdate() && !propertyConfig.isReference()
					&& !propertyConfig.isDerived()) {
				if (propertyString.equals("") && !propertyConfig.isRequired()) {
					getEntity().setProperty(getPropertyName(), null);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getString())) {
					getEntity().setProperty(getPropertyName(), propertyString);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getInteger())) {
					Integer propertyValue = Transformer.integer(propertyString);
					getEntity().setProperty(getPropertyName(), propertyValue);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getLong())) {
					Long propertyValue = Transformer
							.longInteger(propertyString);
					getEntity().setProperty(getPropertyName(), propertyValue);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getBoolean())) {
					Boolean propertyValue = Transformer.logic(propertyString);
					getEntity().setProperty(getPropertyName(), propertyValue);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getFloat())) {
					Float propertyValue = Transformer
							.floatDecimal(propertyString);
					getEntity().setProperty(getPropertyName(), propertyValue);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getDouble())) {
					Double propertyValue = Transformer
							.doubleDecimal(propertyString);
					getEntity().setProperty(getPropertyName(), propertyValue);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getDate())) {
					Date propertyValue = Transformer.date(propertyString,
							getDatePattern());
					getEntity().setProperty(getPropertyName(), propertyValue);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getUrl())) {
					URL propertyValue = Transformer.url(propertyString);
					getEntity().setProperty(getPropertyName(), propertyValue);
				} else if (propertyConfig.getPropertyClass().equals(
						PropertyClass.getEmail())) {
					Email propertyValue = Transformer.email(propertyString);
					getEntity().setProperty(getPropertyName(), propertyValue);
				} else {
					setProperty = false;
					throw new TypeRuntimeException(propertyConfig
							.getPropertyClass()
							+ " is not a Modelibra property type.");
				}
				setProperty = true;
			} else {
				setProperty = false;
			}
		} catch (TypeRuntimeException e) {
			setProperty = false;
		}
	}

	public boolean getBooleanProperty() {
		boolean logic = false;
		PropertyConfig propertyConfig = getPropertyConfig();
		if (propertyConfig.isDisplay()
				&& !propertyConfig.isReference()
				&& propertyConfig.getPropertyClass().equals(
						PropertyClass.getBoolean())) {
			Boolean propertyValueBooleanObject = (Boolean) getEntity()
					.getProperty(getPropertyName());
			logic = Transformer.logic(propertyValueBooleanObject);
			getProperty = true;
		} else {
			getProperty = false;
		}
		return logic;
	}

	public void setProperty(boolean propertyBoolean) {
		PropertyConfig propertyConfig = getPropertyConfig();
		if (propertyConfig.isUpdate()
				&& !propertyConfig.isReference()
				&& !propertyConfig.isDerived()
				&& propertyConfig.getPropertyClass().equals(
						PropertyClass.getBoolean())) {
			Boolean propertyValue = Transformer.logic(propertyBoolean);
			getEntity().setProperty(getPropertyName(), propertyValue);
			setProperty = true;
		} else {
			setProperty = false;
		}
	}

	public IEntity<?> getEntity() {
		return entity;
	}

	public PropertyConfig getPropertyConfig() {
		return propertyConfig;
	}

	public String getPropertyName() {
		return propertyConfig.getCode();
	}

	public String getDatePattern() {
		return getEntity().getConceptConfig().getModelConfig().getDatePattern();
	}

}
