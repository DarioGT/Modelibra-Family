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

import java.net.URL;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.modelibra.IEntity;
import org.modelibra.config.PropertyConfig;
import org.modelibra.type.Email;
import org.modelibra.type.PropertyClass;
import org.modelibra.type.ValidationType;
import org.modelibra.util.TextHandler;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * External link panel.
 * 
 * @author Dzenan Ridjanovic
 * @author Vedad Kirlic
 * @version 2008-10-27
 */
public class ExternalLinkPanel extends Panel {

	private static final long serialVersionUID = 101830L;

	private ExternalLink externalLink;

	/**
	 * Constructs an external link panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public ExternalLinkPanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		IEntity<?> entity = viewModel.getEntity();

		PropertyConfig propertyConfig = viewModel.getPropertyConfig();
		String displayText = null;
		if (!view.getUserProperties().isEmpty()) {
			displayText = (String) view.getUserProperties().getUserProperty(
					"displayText");
		}

		String propertyClass = propertyConfig.getPropertyClass();
		String propertyCode = propertyConfig.getCode();
		TextHandler textExtractor = new TextHandler();
		Object property = entity.getProperty(propertyCode);
		String link = "";
		String linkDisplayText = link;
		int shortTextLength = viewModel.getModel().getModelConfig()
				.getDomainConfig().getShortTextDefaultLengthInt();
		if (entity != null) {
			if (propertyClass.equals(ValidationType.getUrl())) {
				URL url = (URL) property;
				if (url != null) {
					link = url.toString();
				}
				if (displayText != null) {
					linkDisplayText = displayText;
				} else if (link.length() > shortTextLength) {
					linkDisplayText = textExtractor.extractBeginPlusThreeDots(
							link, shortTextLength);
				} else {
					linkDisplayText = link;
				}
				externalLink = new ExternalLink("propertyValue", link,
						linkDisplayText);
				add(externalLink);
			} else if (propertyClass.equals(ValidationType.getEmail())) {
				Email email = (Email) property;
				if (email != null) {
					link = email.toString();
				}
				if (displayText != null) {
					linkDisplayText = displayText;
				} else if (link.length() > shortTextLength) {
					linkDisplayText = textExtractor.extractBeginPlusThreeDots(
							link, shortTextLength);
				} else {
					linkDisplayText = link;
				}
				externalLink = new ExternalLink("propertyValue", "mailto: "
						+ link, linkDisplayText);
				add(externalLink);
			} else if (propertyClass.equals(PropertyClass.getString())) {
				link = (String) property;
				if (link == null) {
					link = "";
				}
				if (propertyConfig.getValidationType().equals(
						ValidationType.getUrl())) {
					if (displayText != null) {
						linkDisplayText = displayText;

					} else if (link.length() > shortTextLength) {
						linkDisplayText = textExtractor
								.extractBeginPlusThreeDots(link,
										shortTextLength);
					} else {
						linkDisplayText = link;
					}
					externalLink = new ExternalLink("propertyValue", link,
							linkDisplayText);
					add(externalLink);
				} else if (propertyConfig.getValidationType().equals(
						ValidationType.getEmail())) {
					if (displayText != null) {
						linkDisplayText = displayText;
					} else if (link.length() > shortTextLength) {
						linkDisplayText = textExtractor
								.extractBeginPlusThreeDots(link,
										shortTextLength);
					} else {
						linkDisplayText = link;
					}
					externalLink = new ExternalLink("propertyValue", "mailto: "
							+ link, linkDisplayText);
					add(externalLink);
				} else {
					externalLink = new ExternalLink("propertyValue", "", "");
					add(externalLink);
				}
			} else {
				externalLink = new ExternalLink("propertyValue", "", "");
				add(externalLink);
			}
		}
	}

	/**
	 * Gets inner externalLink component
	 * 
	 * @return inner externalLink component
	 */
	public ExternalLink getExternalLink() {
		return externalLink;
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
		externalLink.add(attributeModifier);
	}

}
