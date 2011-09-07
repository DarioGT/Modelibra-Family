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
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.modelibra.IEntity;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Context image panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-16
 */
public class ContextImagePanel extends Panel {

	private static final long serialVersionUID = 101874L;

	private ContextImage contextImage;

	/**
	 * Constructs a multi line label panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public ContextImagePanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		IEntity<?> entity = viewModel.getEntity();
		String propertyCode = viewModel.getPropertyCode();
		contextImage = new ContextImage("propertyValue", new PropertyModel(
				entity, propertyCode));
		add(contextImage);
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
		contextImage.add(attributeModifier);
	}

}
