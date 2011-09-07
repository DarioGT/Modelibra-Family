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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.Entities;
import org.modelibra.IEntity;
import org.modelibra.IDomainModel;
import org.modelibra.config.PropertyConfig;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Type value drop down choice panel.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2007-10-26
 */
public class TypeValueDropDownChoicePanel extends DmPanel {

	private static final long serialVersionUID = 101920L;

	private static Log log = LogFactory
			.getLog(TypeValueDropDownChoicePanel.class);

	protected DropDownChoice dropDownChoice;

	/**
	 * Constructs a type value drop down choice panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public TypeValueDropDownChoicePanel(final ViewModel viewModel,
			final View view) {
		super(view.getWicketId());
		IEntity<?> entity;
		String action = viewModel.getAction();
		if (action != null && action.equals("update")) {
			entity = viewModel.getUpdateEntity();
		} else {
			entity = viewModel.getEntity();
		}
		PropertyConfig propertyConfig = viewModel.getPropertyConfig();
		if (propertyConfig.isValidateEntitiesType()) {
			IDomainModel model = viewModel.getModel();
			String validationType = propertyConfig.getValidationType();
			Entities<?> validationTypeEntities = (Entities<?>) model
					.getEntry(validationType);
			if (validationTypeEntities == null) {
				IDomainModel referenceModel = model.getDomain().getReferenceModel();
				if (referenceModel != null) {
					if (referenceModel != model) {
						validationTypeEntities = (Entities<?>) referenceModel
								.getEntry(validationType);
					}
				} else {
					log.info(model.getDomain().getDomainConfig().getCode()
							+ " domain does not have the reference model.");
				}
			}
			if (validationTypeEntities != null) {
				Entities<?> validationTypeEntitiesOrderedByCode = validationTypeEntities
						.orderByCode();
				List<?> validationTypeList = validationTypeEntitiesOrderedByCode
						.getCodeList();
				String propertyCode = propertyConfig.getCode();
				CompoundPropertyModel entityModel = new CompoundPropertyModel(
						entity);
				PropertyModel propertyModel = new PropertyModel(entityModel,
						propertyCode);
				dropDownChoice = new TypeValueDropDownChoice(
						"typeValueDropDownChoice", propertyModel,
						validationTypeList);

				// Sets loacalized label for dropDownChoice
				dropDownChoice.setLabel(new Model(LocalizedText
						.getPropertyName(this, entity, propertyConfig)));

				add(dropDownChoice);
			} else {
				log.info(validationType
						+ " entities validation type is not valid.");
			}
		} else {
			log
					.info(propertyConfig.getCode()
							+ " property is not well configured for the entities type validation.");
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

	private final class TypeValueDropDownChoice extends DropDownChoice {

		private static final long serialVersionUID = 200921L;

		/**
		 * Constructs a type value drop down choice.
		 * 
		 * @param wicketId
		 *            Wicket id
		 * @param propertyModel
		 *            property model
		 * @param typeValueList
		 *            type value list
		 */
		public TypeValueDropDownChoice(final String wicketId,
				final PropertyModel propertyModel, List<?> typeValueList) {
			super(wicketId, propertyModel, typeValueList);
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
