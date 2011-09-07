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

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.modelibra.wicket.concept.EntityDisplayListPanel;

/**
 * Entity list panel list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-05-09
 */
public class EntityListPanelListView extends ListView {

	private static final long serialVersionUID = 101020L;

	/**
	 * Constructs an entity list panel list view.
	 * 
	 * @param wicketId
	 *            Wicket id
	 * @param entityListPanelList
	 *            entity list panel list
	 */
	public EntityListPanelListView(final String wicketId,
			final List<?> entityListPanelList) {
		super(wicketId, entityListPanelList);
	}

	/**
	 * Populates list view lines.
	 * 
	 * @param item
	 *            list item
	 */
	protected void populateItem(ListItem item) {
		final EntityDisplayListPanel entityListPanel = (EntityDisplayListPanel) item
				.getModelObject();
		item.add(entityListPanel);
	}

}
