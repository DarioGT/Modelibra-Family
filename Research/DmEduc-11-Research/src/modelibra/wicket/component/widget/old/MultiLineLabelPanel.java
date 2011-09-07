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
package modelibra.wicket.component.widget.old;

import modelibra.wicket.component.view.ConceptView;
import modelibra.wicket.component.view.PropertyView;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.modelibra.IEntity;
import org.modelibra.config.PropertyConfig;
import org.modelibra.exception.ModelibraRuntimeException;

/**
 * Multi line label panel.1
 */
@SuppressWarnings("serial")
public class MultiLineLabelPanel extends Panel {

	private MultiLineLabel multiLineLabel;

	/**
	 * Constructs a multi line label panel.
	 * 
	 * @param propertyView
	 *            property view
	 */
	public MultiLineLabelPanel(final PropertyView propertyView) {
		super(propertyView.getWicketId());
		ConceptView conceptView = propertyView.getConceptView();
		IEntity<?> entity = conceptView.getEntity();
		PropertyConfig propertyConfig = propertyView.getPropertyConfig();

		Class<?> propertyClass = propertyConfig.getPropertyClassObject();
		String propertyCode = propertyConfig.getCode();
		String propertyValueText = "";
		if (propertyClass == String.class) {
			propertyValueText = (String) entity.getProperty(propertyCode);
			if (propertyConfig.isScramble()) {
				propertyValueText = "********";
			}
		} else {
			Object propertyValue = entity.getProperty(propertyCode);
			if (propertyValue != null) {
				propertyValueText = propertyValue.toString();
			} else {
				if (propertyConfig.isRequired()) {
					throw new ModelibraRuntimeException(
							"MultiLineLabelPanel.constructor -- required property is null: "
									+ propertyConfig.getCode());
				} else {
					propertyValueText = null;
				}
			}
		}
		multiLineLabel = new MultiLineLabel("propertyValue", propertyValueText);
		add(multiLineLabel);
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
		multiLineLabel.add(attributeModifier);
	}

}
