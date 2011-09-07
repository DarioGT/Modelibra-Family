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

import modelibra.wicket.component.view.PropertyView;

import org.apache.wicket.markup.html.basic.Label;
import org.modelibra.config.PropertyConfig;
import org.modelibra.wicket.util.LocalizedText;

/**
 * Multi line label 2 panel: property header and value.
 */
@SuppressWarnings("serial")
public class MultiLineLabel2Panel extends MultiLineLabelPanel {

	/**
	 * Constructs a multi line label panel.
	 * 
	 * @param propertyView
	 *            property view
	 */
	public MultiLineLabel2Panel(final PropertyView propertyView) {
		super(propertyView);
		PropertyConfig propertyConfig = propertyView.getPropertyConfig();
		String propertyKey = propertyConfig.getConceptConfig().getCode() + "."
				+ propertyView.getHeader();
		String propertyHeaderText = LocalizedText.getApplicationPropertiesText(
				this, propertyKey);
		Label propertyHeader = new Label("propertyHeader", propertyHeaderText);
		add(propertyHeader);
	}

}
