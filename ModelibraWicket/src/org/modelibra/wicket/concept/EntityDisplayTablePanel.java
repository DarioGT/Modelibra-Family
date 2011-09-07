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
package org.modelibra.wicket.concept;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.modelibra.IEntities;
import org.modelibra.config.ConceptConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.container.NeighborEmptyNameLabelListView;
import org.modelibra.wicket.container.PropertyNameLabelListView;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity display table panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityDisplayTablePanel extends DmPanel {

	private static final long serialVersionUID = 102200L;

	private static Log log = LogFactory.getLog(EntityDisplayTablePanel.class);

	/**
	 * Constructs an entity display table panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityDisplayTablePanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();

		IEntities entities = viewModel.getEntities();
		ConceptConfig conceptConfig = entities.getConceptConfig();

		// Table title
		String conceptsName = LocalizedText.getConceptsName(this, entities);
		add(new Label("conceptsName", conceptsName));

		// Absorbed parent essential property names
		List<String> parentEssentialPropertyNames = new ArrayList<String>();
		List<String> parentEssentialPropertyCodes = entities.getConceptConfig()
				.getParentCodeEssentialPropertyCodes();

		String parentPropertyName;
		for (String parentPropertyKey : parentEssentialPropertyCodes) {
			parentPropertyName = LocalizedText.getApplicationPropertiesText(
					this, parentPropertyKey);
			parentEssentialPropertyNames.add(parentPropertyName);
		}

		// Concept essential property names
		List<String> conceptEssentialPropertyNames = new ArrayList<String>();
		List<String> conceptEssentiaPropertyCodes = entities.getConceptConfig()
				.getConceptCodeEssentialPropertyCodes();
		String conceptPropertyName;
		for (String conceptPropertyKey : conceptEssentiaPropertyCodes) {
			conceptPropertyName = LocalizedText.getApplicationPropertiesText(
					this, conceptPropertyKey);
			conceptEssentialPropertyNames.add(conceptPropertyName);
		}

		List<String> essentialPropertyNames = parentEssentialPropertyNames;
		essentialPropertyNames.addAll(conceptEssentialPropertyNames);
		add(new PropertyNameLabelListView("propertyNameLabelListView",
				essentialPropertyNames));

		// Neighbor empty labels
		List<String> childNeighborNames = conceptConfig.getChildNeighborNames();
		add(new NeighborEmptyNameLabelListView(
				"neighborEmptyNameLabelListViewHead", childNeighborNames));

		ViewModel entityDisplayTableListModel = new ViewModel();
		entityDisplayTableListModel.copyPropertiesFrom(viewModel);

		View entityDisplayTableView = new View();
		entityDisplayTableView.copyPropertiesFrom(view);
		entityDisplayTableView.setWicketId("entityDisplayTableListView");

		PageableListView entityDisplayTableListView = app
				.getViewMeta(modelCode).getPageableListView(
						"EntityDisplayTableListView",
						entityDisplayTableListModel, entityDisplayTableView);
		add(entityDisplayTableListView);
		if (!app.getAccessPoint().isConceptDisplayAllowed(getAppSession(),
				conceptConfig)) {
			entityDisplayTableListView.setVisible(false);
		}

		add(new PagingNavigator("blockNavigator", entityDisplayTableListView));
	}

}
