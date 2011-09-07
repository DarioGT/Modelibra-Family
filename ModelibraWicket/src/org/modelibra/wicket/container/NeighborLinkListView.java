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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

/**
 * Neighbor link list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-06-06
 */
public class NeighborLinkListView extends ListView {

	private static final long serialVersionUID = 101034L;

	/**
	 * Constructs a neighbor link list view.
	 * 
	 * @param wicketId
	 *            Wicket id
	 * @param neighborLinkList
	 *            neighbor link list
	 */
	public NeighborLinkListView(final String wicketId,
			final List<?> neighborLinkList) {
		super(wicketId, neighborLinkList);
	}

	/**
	 * Populates list view lines.
	 * 
	 * @param item
	 *            list item
	 */
	protected void populateItem(ListItem item) {
		Link neighborLink = (Link) item.getModelObject();
		item.add(neighborLink);
	}

}
