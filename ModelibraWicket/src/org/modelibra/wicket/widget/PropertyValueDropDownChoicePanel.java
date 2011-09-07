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

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.Entities;
import org.modelibra.IEntity;
import org.modelibra.config.PropertyConfig;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Property value drop down choice panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-07-01
 */
public class PropertyValueDropDownChoicePanel extends DmPanel {

	private static final long serialVersionUID = 101890L;

	protected DropDownChoice dropDownChoice;

	/**
	 * Constructs a property value drop down choice panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public PropertyValueDropDownChoicePanel(final ViewModel viewModel,
			final View view) {
		super(view.getWicketId());
		IEntity<?> entity;
		String action = viewModel.getAction();
		if (action != null && action.equals("update")) {
			entity = viewModel.getUpdateEntity();
		} else {
			entity = viewModel.getEntity();
		}
		Entities<?> entities = (Entities<?>) viewModel.getEntities();

		PropertyConfig propertyConfig = viewModel.getPropertyConfig();

		String propertyCode = propertyConfig.getCode();

		List<?> propertyValueList = entities.getPropertyList(propertyCode);
		CompoundPropertyModel entityModel = new CompoundPropertyModel(entity);
		PropertyModel propertyModel = new PropertyModel(entityModel,
				propertyCode);
		dropDownChoice = new PropertyValueDropDownChoice(
				"propertyValueDropDownChoice", propertyModel, propertyValueList);

		add(dropDownChoice);
	}

	private final class PropertyValueDropDownChoice extends DropDownChoice {

		private static final long serialVersionUID = 200891L;

		/**
		 * Constructs a property value drop down choice.
		 * 
		 * @param wicketId
		 *            Wicket id
		 * @param propertyModel
		 *            property model
		 * @param propertyValueList
		 *            property value list
		 */
		public PropertyValueDropDownChoice(final String wicketId,
				final PropertyModel propertyModel, List<?> propertyValueList) {
			super(wicketId, propertyModel, propertyValueList);
		}

		/**
		 * To force a server makes a roundtrip on each selection change.
		 * 
		 * @return <code>true</code> if a server makes a roundtrip on each
		 *         selection change
		 */
		protected boolean wantOnSelectionChangedNotifications() {
			return true;
		}
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
