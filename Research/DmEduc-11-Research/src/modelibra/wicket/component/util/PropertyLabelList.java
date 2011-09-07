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
package modelibra.wicket.component.util;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

/**
 * Property label list.
 */
@SuppressWarnings("serial")
public class PropertyLabelList extends ListView {

	/**
	 * Constructs a property label list.
	 * 
	 * @param wicketId
	 *            Wicket id
	 * @param propertyLableList
	 *            property label list
	 */
	public PropertyLabelList(final String wicketId,
			final List<String> propertyLableList) {
		super(wicketId, propertyLableList);
	}

	/**
	 * Populates list view lines.
	 * 
	 * @param item
	 *            list item
	 */
	protected void populateItem(ListItem item) {
		String propertyName = (String) item.getModelObject();
		Label propertyLabel = new Label("propertyLabel", propertyName);
		item.add(propertyLabel);
	}

}
