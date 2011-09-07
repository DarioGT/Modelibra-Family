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
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.IEntity;
import org.modelibra.config.PropertyConfig;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Check box panel.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2007-10-26
 */
public class CheckBoxPanel extends Panel {

	private static final long serialVersionUID = 101810L;

	private CheckBox checkBox;

	/**
	 * Constructs a check box panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public CheckBoxPanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		IEntity<?> entity;
		String action = viewModel.getAction();
		if (action != null && action.equals("update")) {
			entity = viewModel.getUpdateEntity();
		} else {
			entity = viewModel.getEntity();
		}
		PropertyConfig propertyConfig = viewModel.getPropertyConfig();

		checkBox = new CheckBox("propertyValue", new PropertyModel(entity,
				propertyConfig.getCode()));
		add(checkBox);
	}

	/**
	 * Gets inner checkBox component
	 * 
	 * @return inner checkBox component
	 */
	public CheckBox getCheckBox() {
		return checkBox;
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
		checkBox.add(attributeModifier);
	}

}
