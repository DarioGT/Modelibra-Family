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
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.modelibra.IEntity;
import org.modelibra.config.PropertyConfig;
import org.modelibra.exception.ConfigRuntimeException;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Lookup label + link panel.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-10-31
 */
public class LookupPanel extends DmPanel {

	private static final long serialVersionUID = 101860L;

	private Link lookupLink;

	/**
	 * Constructs a lookup panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public LookupPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		PropertyConfig baseEntityPropertyConfig = viewModel.getPropertyConfig();
		final DomainApp app = (DomainApp) getApplication();
		final String modelCode = viewModel.getModel().getModelConfig()
				.getCode();

		if (baseEntityPropertyConfig.isReference()) {
			lookupLink = new Link("lookupLink") {
				static final long serialVersionUID = 200871L;

				public void onClick() {
					ViewModel lookupModel = new ViewModel();
					lookupModel.copyPropertiesFrom(viewModel);
					View lookupView = new View();
					lookupView.copyPropertiesFrom(view);
					lookupView.setUpdate(false);
					lookupView.setContextView(lookupView);
					setResponsePage(app.getViewMeta(modelCode).getLookupPage(
							"EntityLookupTablePage", lookupModel, lookupView));
				}
			};
			add(lookupLink);

			Label notNullValueLabel = new Label("notNullValueLabel", "Ø");
			IEntity<?> entity = viewModel.getEntity();
			if (entity != null) {
				Object referencePropertyValue = entity
						.getProperty(baseEntityPropertyConfig.getCode());
				if (referencePropertyValue == null) {
					notNullValueLabel.setVisible(false);
				}
			} else {
				notNullValueLabel.setVisible(false);
			}
			add(notNullValueLabel);
		} else {
			throw new ConfigRuntimeException("The property is not reference: "
					+ baseEntityPropertyConfig.getCode());
		}
	}

	/**
	 * Gets inner link component
	 * 
	 * @return inner link component
	 */
	public Link getLookupLink() {
		return lookupLink;
	}

	/**
	 * Sets an attribute.
	 * 
	 * @param attributeName
	 *            attribute name
	 * @param attributeValue
	 *            attribute value
	 */
	public void setLookupAttribute(String attributeName, String attributeValue) {
		AttributeModifier attributeModifier = new AttributeModifier(
				attributeName, true, new Model(attributeValue));
		lookupLink.add(attributeModifier);
	}

}
