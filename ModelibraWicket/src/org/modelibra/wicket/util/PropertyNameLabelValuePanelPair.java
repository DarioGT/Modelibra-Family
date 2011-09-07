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
package org.modelibra.wicket.util;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Property name label/value panel pair.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-03-27
 */
@SuppressWarnings("serial")
public class PropertyNameLabelValuePanelPair implements Serializable {

	private Label propertyNameLabel;

	private Panel propertyValuePanel;

	/**
	 * Constructs a property name label/value panel pair.
	 */
	public PropertyNameLabelValuePanelPair() {
		super();
	}

	/**
	 * Sets a property name label.
	 * 
	 * @param propertyNameLabel
	 *            property name label
	 */
	public void setPropertyNameLabel(Label propertyNameLabel) {
		this.propertyNameLabel = propertyNameLabel;
	}

	/**
	 * Gets a property name label.
	 * 
	 * @return property name label
	 */
	public Label getPropertyNameLabel() {
		return propertyNameLabel;
	}

	/**
	 * Sets a property value panel.
	 * 
	 * @param propertyValuePanel
	 *            property value panel
	 */
	public void setPropertyValuePanel(Panel propertyValuePanel) {
		this.propertyValuePanel = propertyValuePanel;
	}

	/**
	 * Gets a property value panel.
	 * 
	 * @return property value panel
	 */
	public Panel getPropertyValuePanel() {
		return propertyValuePanel;
	}

}