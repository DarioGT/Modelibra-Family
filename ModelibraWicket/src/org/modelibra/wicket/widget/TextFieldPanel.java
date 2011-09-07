/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.modelibra.wicket.widget;

import java.util.Locale;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.FormComponentFeedbackBorder;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.IConverter;
import org.modelibra.IEntity;
import org.modelibra.config.ModelConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.type.PropertyClass;
import org.modelibra.wicket.type.DateConverter;
import org.modelibra.wicket.type.EmailConverter;
import org.modelibra.wicket.type.UrlConverter;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Text field panel.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2007-10-26
 */
public class TextFieldPanel extends Panel {

	private static final long serialVersionUID = 101910L;

	protected TextField textField;

	/**
	 * Constructs a text field panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public TextFieldPanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
			IEntity<?> entity;
			String action = viewModel.getAction();
			if (action != null && action.equals("update")) {
				entity = viewModel.getUpdateEntity();
			} else {
				entity = viewModel.getEntity();
			}
			CompoundPropertyModel entityModel = new CompoundPropertyModel(
					entity);
			final PropertyConfig propertyConfig = viewModel.getPropertyConfig();
			String propertyCode = propertyConfig.getCode();
			/*if (propertyConfig.isReference()) {
				String neighborCode = propertyConfig.getReferenceNeighbor();
				NeighborConfig neighborConfig = entity.getConceptConfig()
						.getNeighborsConfig().getNeighborConfig(neighborCode);
				String neighborConceptCode = neighborConfig
						.getDestinationConcept();
				IEntities neighborEntities = viewModel.getModel().getEntry(
						neighborConceptCode);
				if (neighborEntities != null) {
					Long referenceOid = (Long) entity.getProperty(propertyCode);
					Oid neighborParentOid = new Oid(referenceOid);
					IEntity neighborParent = neighborEntities
							.retrieveByOid(neighborParentOid);
					if (neighborParent != null) {
						PropertyConfig firstEssentialPropertyConfig = neighborParent
								.getConceptConfig().getPropertiesConfig()
								.getFirstEssentialPropertyConfig();
						if (firstEssentialPropertyConfig != null) {
							String firstEssentialPropertyCode = firstEssentialPropertyConfig
									.getCode();
							CompoundPropertyModel parentModel = new CompoundPropertyModel(
									neighborParent);
							textField = new TextField("propertyValue",
									parentModel
											.bind(firstEssentialPropertyCode));
						}
					}
				}
				if (textField == null) {
					textField = new TextField("propertyValue", entityModel
							.bind(propertyCode));
					textField.setVisible(false);
				}
				// textField.setType(PropertyClass.getLongClass());
			} else*/ if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getBoolean())) {
				textField = new TextField("propertyValue", entityModel
						.bind(propertyCode));
				textField.setType(PropertyClass.getBooleanClass());
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getString())) {
				textField = new TextField("propertyValue", entityModel
						.bind(propertyCode)) {
					static final long serialVersionUID = 200915L;

					public IConverter getConverter(final Class type) {
						return new IConverter() {
							public Object convertToObject(String source,
									Locale locale) {
								if (propertyConfig.isWhitespaceAllowed())
									return source.isEmpty() ? null : source;
								return source.trim().isEmpty() ? null : source;

							}

							public String convertToString(Object source,
									Locale locale) {
								return source == null ? null : (String) source;
							}
						};
					}
				};
				textField.setType(PropertyClass.getStringClass());
				if (propertyConfig.isScramble()) {
					setAttribute("type", "password");
				}
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getInteger())) {
				textField = new TextField("propertyValue", entityModel
						.bind(propertyCode));
				textField.setType(PropertyClass.getIntegerClass());
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getLong())) {
				textField = new TextField("propertyValue", entityModel
						.bind(propertyCode));
				textField.setType(PropertyClass.getLongClass());
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getFloat())) {
				textField = new TextField("propertyValue", entityModel
						.bind(propertyCode));
				textField.setType(PropertyClass.getFloatClass());
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getDouble())) {
				textField = new TextField("propertyValue", entityModel
						.bind(propertyCode));
				textField.setType(PropertyClass.getDoubleClass());
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getDate())) {
				textField = new TextField("propertyValue", entityModel
						.bind(propertyCode)) {
					static final long serialVersionUID = 200915L;

					String datePattern = viewModel.getModel().getModelConfig()
							.getDatePattern();

					public IConverter getConverter(final Class type) {
						return new DateConverter(datePattern);
					}
				};
				textField.setType(PropertyClass.getDateClass());
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getUrl())) {
				textField = new TextField("propertyValue", entityModel
						.bind(propertyCode)) {
					static final long serialVersionUID = 200916L;

					public IConverter getConverter(final Class type) {
						return new UrlConverter();
					}
				};
				textField.setType(PropertyClass.getUrlClass());
			} else if (propertyConfig.getPropertyClass().equals(
					PropertyClass.getEmail())) {
				textField = new TextField("propertyValue", entityModel
						.bind(propertyCode)) {
					static final long serialVersionUID = 200917L;

					public IConverter getConverter(final Class type) {
						return new EmailConverter();
					}
				};
				textField.setType(PropertyClass.getEmailClass());
			}

			// Sets loacalized label for textfield
			textField.setLabel(new Model(LocalizedText.getPropertyName(this,
					entity, propertyConfig)));

			ModelConfig modelConfig = propertyConfig.getConceptConfig()
					.getModelConfig();
			if (modelConfig.getDomainConfig().isValidateForm()) {
				if (propertyConfig.isRequired() && propertyConfig.isUpdate()
						&& !propertyConfig.isWhitespaceAllowed()) {
					textField.setRequired(true);
				}
			}

			if (propertyConfig.getDisplayLengthInt() > 0) {
				setAttribute("size", propertyConfig.getDisplayLength());
			}
			if (!propertyConfig.isUpdate()) {
				setAttribute("readonly", "readonly");
			}

			FormComponentFeedbackBorder propertyFeedback = new FormComponentFeedbackBorder(
					"propertyFeedback");
			propertyFeedback.add(textField);
			add(propertyFeedback);
	}

	/**
	 * Gets inner textField component
	 * 
	 * @return inner textField component
	 */
	public TextField getTextField() {
		return textField;
	}

	/**
	 * Sets an attribute.
	 * 
	 * @param attributeName
	 *            attribute name
	 * @param attributeValue
	 *            attribute value
	 */
	public void setAttribute(String attributeName, String attributeValue) {
		AttributeModifier attributeModifier = new AttributeModifier(
				attributeName, true, new Model(attributeValue));
		textField.add(attributeModifier);
	}

	/**
	 * Empties the text field.
	 */
	public void emptyTextField() {
		String[] empty = new String[1];
		empty[0] = "";
		textField.setModelValue(empty);
	}

}
