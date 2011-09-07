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

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.validation.FormComponentFeedbackBorder;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.config.ModelConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.exception.ConfigRuntimeException;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Lookup drop down choice panel.
 * 
 * @author Vedad Kirlic
 * @version 2008-10-31
 */
public class LookupDropDownChoicePanel extends DmPanel {

	private static final long serialVersionUID = 101930L;

	private DropDownChoice dropDownChoice;

	/**
	 * Constructs a lookup drop down choice panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public LookupDropDownChoicePanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		final IEntities<?> lookupEntities = viewModel.getLookupEntities();
		final IEntity<?> entity;

		if (viewModel.getAction().equals("update")) {
			entity = viewModel.getUpdateEntity();
		} else {
			entity = viewModel.getEntity();
		}

		PropertyConfig propertyConfig = viewModel.getPropertyConfig();
		if (propertyConfig.isReference()) {
			dropDownChoice = new DropDownChoice("dropDownChoice",
					new PropertyModel(entity, propertyConfig
							.getReferenceNeighbor()), lookupEntities.getList());

			ModelConfig modelConfig = propertyConfig.getConceptConfig()
					.getModelConfig();
			if (modelConfig.getDomainConfig().isValidateForm()) {
				if (propertyConfig.isRequired() && propertyConfig.isUpdate()) {
					dropDownChoice.setRequired(true);
				}
			}
			dropDownChoice.setNullValid(!propertyConfig.isRequired());

			if (!propertyConfig.isUpdate()) {
				setAttribute("readonly", "readonly");
			}
			dropDownChoice.setLabel(new Model(LocalizedText.getPropertyName(
					this, entity, propertyConfig)));

			FormComponentFeedbackBorder propertyFeedback = new FormComponentFeedbackBorder(
					"propertyFeedback");
			propertyFeedback.add(dropDownChoice);
			add(propertyFeedback);
		} else {
			throw new ConfigRuntimeException("The property is not reference: "
					+ propertyConfig.getCode());
		}
	}

	/**
	 * Gets inner dropDownChoice component
	 * 
	 * @return inner dropDownChoice component
	 */
	public DropDownChoice getDropDownChoice() {
		return dropDownChoice;
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
		dropDownChoice.add(attributeModifier);
	}
}