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
package dm.wicket.meta.property;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.modelibra.wicket.container.DmListView;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dm.meta.property.Property;

/**
 * Displays property list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-02-01
 */
public class PropertyList extends DmListView {

	private static final long serialVersionUID = 110110145L;

	private static Log log = LogFactory.getLog(PropertyList.class);

	public PropertyList(final ViewModel viewModel, final View view) {
		super(viewModel, view);
	}

	protected void populateItem(final ListItem item) {
		Property property = (Property) item.getModelObject();
		String propertyName = property.getCode();
		Label propertyNameLabel = new Label("propertyName", propertyName);
		item.add(propertyNameLabel);
	}

}
