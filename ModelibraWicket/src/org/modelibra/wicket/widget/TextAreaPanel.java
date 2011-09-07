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
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.validation.FormComponentFeedbackBorder;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.IEntity;
import org.modelibra.config.ModelConfig;
import org.modelibra.config.PropertyConfig;
import org.modelibra.util.Transformer;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Text area panel.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2007-10-26
 */
public class TextAreaPanel extends Panel {

	private static final long serialVersionUID = 101900L;

	private TextArea textArea;

	/**
	 * Constructs a text area panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public TextAreaPanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		IEntity<?> entity;
		String action = viewModel.getAction();
		if (action != null && action.equals("update")) {
			entity = viewModel.getUpdateEntity();
		} else {
			entity = viewModel.getEntity();
		}
		PropertyConfig propertyConfig = viewModel.getPropertyConfig();

		textArea = new TextArea("propertyValue", new PropertyModel(entity,
				propertyConfig.getCode()));

		// Sets loacalized label for textArea
		textArea.setLabel(new Model(LocalizedText.getPropertyName(this, entity,
				propertyConfig)));

		ModelConfig modelConfig = propertyConfig.getConceptConfig()
				.getModelConfig();
		if (modelConfig.getDomainConfig().isValidateForm()) {
			if (propertyConfig.isRequired() && propertyConfig.isUpdate()) {
				textArea.setRequired(true);
			}
		}

		int propertySize = propertyConfig.getDisplayLengthInt();
		int areaRowSize = (propertySize / DomainApp.AREA_COLUMN_SIZE) + 1;
		String noOfRows = Transformer.string(areaRowSize);
		String noOfColumns = Transformer.string(DomainApp.AREA_COLUMN_SIZE);
		setAttribute("rows", noOfRows);
		setAttribute("cols", noOfColumns);
		if (!propertyConfig.isUpdate()) {
			setAttribute("readonly", "readonly");
		}

		FormComponentFeedbackBorder propertyFeedback = new FormComponentFeedbackBorder(
				"propertyFeedback");
		propertyFeedback.add(textArea);
		add(propertyFeedback);
	}

	/**
	 * Gets inner textArea component
	 * 
	 * @return inner textArea component
	 */
	public TextArea getTextArea() {
		return textArea;
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
		textArea.add(attributeModifier);
	}

}
