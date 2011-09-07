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
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.modelibra.IEntity;
import org.modelibra.config.PropertyConfig;
import org.modelibra.exception.ConfigRuntimeException;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Lookup label + lookup link + empty link panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-31
 */
public class LookupEmptyPanel extends LookupPanel {

	private static final long serialVersionUID = 101850L;

	private Link emptyLink;

	/**
	 * Constructs a lookup empty panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public LookupEmptyPanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		final PropertyConfig baseEntityPropertyConfig = viewModel
				.getPropertyConfig();
		final DomainApp app = (DomainApp) getApplication();
		final String modelCode = viewModel.getModel().getModelConfig()
				.getCode();

		if (baseEntityPropertyConfig.isReference()
				&& !baseEntityPropertyConfig.isRequired()) {
			emptyLink = new Link("emptyLink") {
				static final long serialVersionUID = 200861L;

				public void onClick() {
					ViewModel entityEditFormPageModel = new ViewModel();
					entityEditFormPageModel.copyPropertiesFrom(viewModel);
					IEntity<?> afterUpdateEntity = entityEditFormPageModel
							.getUpdateEntity();
					if (afterUpdateEntity != null) {
						afterUpdateEntity.setProperty(baseEntityPropertyConfig
								.getCode(), null);
						View entityEditFormPageView = new View();
						entityEditFormPageView.copyPropertiesFrom(view);
						if (viewModel.getAction().equals("update")) {
							setResponsePage(app.getViewMeta(modelCode).getPage(
									"EntityEditFormPage",
									entityEditFormPageModel,
									entityEditFormPageView));
						}
					}
				}
			};
			add(emptyLink);
		} else {
			throw new ConfigRuntimeException(
					"The property is not reference or the property is required: "
							+ baseEntityPropertyConfig.getCode());
		}
	}

	/**
	 * Gets inner link component
	 * 
	 * @return inner link component
	 */
	public Link getEmptyLink() {
		return emptyLink;
	}

	/**
	 * Sets an empty link attribute.
	 * 
	 * @param attributeName
	 *            attribute name
	 * @param attributeValue
	 *            attribute value
	 */
	public void setEmptyAttribute(String attributeName, String attributeValue) {
		AttributeModifier attributeModifier = new AttributeModifier(
				attributeName, true, new Model(attributeValue));
		emptyLink.add(attributeModifier);
	}

}
