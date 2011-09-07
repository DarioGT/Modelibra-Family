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
package dm.wicket.meta.concept;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.modelibra.wicket.container.DmListView;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import dm.meta.concept.Concept;
import dm.meta.neighbor.Neighbors;
import dm.meta.property.Properties;
import dm.wicket.meta.neighbor.NeighborListPanel;
import dm.wicket.meta.property.PropertyListPanel;

/**
 * Displays concept list view.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-09
 */
public class ConceptList extends DmListView {

	private static final long serialVersionUID = 110110135L;

	private static Log log = LogFactory.getLog(ConceptList.class);

	private ViewModel viewModel;

	private View view;

	public ConceptList(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		this.viewModel = viewModel;
		this.view = view;
	}

	protected void populateItem(final ListItem item) {
		Concept concept = (Concept) item.getModelObject();
		String conceptName = concept.getCode().toUpperCase();
		Label conceptNameLabel = new Label("conceptName", conceptName);
		item.add(conceptNameLabel);

		ViewModel propertiesViewModel = new ViewModel();
		propertiesViewModel.copyPropertiesFrom(viewModel);
		Properties properties = concept.getProperties();
		propertiesViewModel.setEntities(properties);
		View propertiesView = new View();
		propertiesView.copyPropertiesFrom(view);
		propertiesView.setWicketId("propertyListPanel");
		PropertyListPanel propertyListPanel = new PropertyListPanel(
				propertiesViewModel, propertiesView);
		item.add(propertyListPanel);

		ViewModel neighborsViewModel = new ViewModel();
		neighborsViewModel.copyPropertiesFrom(viewModel);
		Neighbors neighbors = concept.getNeighbors();
		neighborsViewModel.setEntities(neighbors);
		View neighborsView = new View();
		neighborsView.copyPropertiesFrom(view);
		neighborsView.setWicketId("neighborListPanel");
		NeighborListPanel neighborListPanel = new NeighborListPanel(
				neighborsViewModel, neighborsView);
		item.add(neighborListPanel);
	}

}
