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
package modelibra.wicket.component.util.old;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Property name label/value panel pair.
 */
@SuppressWarnings("serial")
public class PropertyLabelPanelPair implements Serializable {

	private Label propertyLabel;

	private Panel propertyPanel;

	/**
	 * Constructs a property label/panel pair.
	 */
	public PropertyLabelPanelPair() {
		super();
	}

	/**
	 * Sets a property label.
	 * 
	 * @param propertyLabel
	 *            property label
	 */
	public void setPropertyLabel(Label propertyLabel) {
		this.propertyLabel = propertyLabel;
	}

	/**
	 * Gets a property label.
	 * 
	 * @return property label
	 */
	public Label getPropertyLabel() {
		return propertyLabel;
	}

	/**
	 * Sets a property panel.
	 * 
	 * @param propertyPanel
	 *            property panel
	 */
	public void setPropertyPanel(Panel propertyPanel) {
		this.propertyPanel = propertyPanel;
	}

	/**
	 * Gets a property panel.
	 * 
	 * @return property panel
	 */
	public Panel getPropertyPanel() {
		return propertyPanel;
	}

}