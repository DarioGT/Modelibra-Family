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
package org.modelibra.wicket.container;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.util.PropertyNameLabelValuePanelPair;

/**
 * Property name label/value panel list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-03-27
 */
public class PropertyNameLabelValuePanelListView extends ListView {

	private static final long serialVersionUID = 101080L;

	/**
	 * Constructs a property name label value panel list view.
	 * 
	 * @param wicketId
	 *            Wicket id
	 * @param propertyNameLabelValuePanelList
	 *            property name label value panel list
	 */
	public PropertyNameLabelValuePanelListView(final String wicketId,
			final List<?> propertyNameLabelValuePanelList) {
		super(wicketId, propertyNameLabelValuePanelList);
	}

	/**
	 * Populates list view lines.
	 * 
	 * @param item
	 *            list item
	 */
	protected void populateItem(ListItem item) {
		PropertyNameLabelValuePanelPair propertyNameLabelValuePanelPair = (PropertyNameLabelValuePanelPair) item
				.getModelObject();
		Label propertyNameLabel = propertyNameLabelValuePanelPair
				.getPropertyNameLabel();
		item.add(propertyNameLabel);
		Panel propertyValuePanel = propertyNameLabelValuePanelPair
				.getPropertyValuePanel();
		item.add(propertyValuePanel);
	}

}
