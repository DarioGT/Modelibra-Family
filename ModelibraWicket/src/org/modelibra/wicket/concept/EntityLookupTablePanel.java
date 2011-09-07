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
 * Entity lookup table panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityLookupTablePanel extends DmPanel {

	private static final long serialVersionUID = 102260L;

	private static Log log = LogFactory.getLog(EntityLookupTablePanel.class);

	/**
	 * Constructs an entity display lookup table panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityLookupTablePanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();

		IEntities lookupEntities = viewModel.getLookupEntities();
		ConceptConfig lookupConceptConfig = lookupEntities.getConceptConfig();

		String conceptsName = LocalizedText
				.getConceptName(this, lookupEntities);
		add(new Label("conceptsName", conceptsName));

		List<String> conceptEssentialPropertyNames = new ArrayList<String>();
		List<String> conceptEssentiaPropertyCodes = lookupEntities
				.getConceptConfig().getConceptCodeEssentialPropertyCodes();
		String conceptPropertyName;
		for (String conceptPropertyKey : conceptEssentiaPropertyCodes) {
			conceptPropertyName = LocalizedText.getApplicationPropertiesText(
					this, conceptPropertyKey);
			conceptEssentialPropertyNames.add(conceptPropertyName);
		}

		List<String> parentEssentialPropertyNames = new ArrayList<String>();
		List<String> parentEssentialPropertyCodes = lookupEntities
				.getConceptConfig().getParentCodeEssentialPropertyCodes();
		String parentPropertyName;
		for (String parentPropertyKey : parentEssentialPropertyCodes) {
			parentPropertyName = LocalizedText.getApplicationPropertiesText(
					this, parentPropertyKey);
			parentEssentialPropertyNames.add(parentPropertyName);
		}

		List<String> essentialPropertyNames = conceptEssentialPropertyNames;
		if (parentEssentialPropertyNames.size() > 0) {
			essentialPropertyNames.addAll(parentEssentialPropertyNames);
		}
		add(new PropertyNameLabelListView("propertyNameLabelListView",
				essentialPropertyNames));

		List<String> childNeighborNames = lookupConceptConfig
				.getChildNeighborNames();
		add(new NeighborEmptyNameLabelListView(
				"neighborEmptyNameLabelListViewHead", childNeighborNames));

		ViewModel lookupModel = new ViewModel();
		lookupModel.copyPropertiesFrom(viewModel);
		if (!lookupModel.getLookupEntities().getConceptConfig().getCode()
				.equals(lookupModel.getEntities().getConceptConfig().getCode())) {
			ViewModel contextViewModel = new ViewModel();
			contextViewModel.copyPropertiesFrom(viewModel);
			contextViewModel.setEntities(lookupModel.getEntities());
			lookupModel.setContextViewModel(contextViewModel);
		}
		lookupModel.setEntities(lookupEntities);

		View lookupView = new View();
		lookupView.copyPropertiesFrom(view);
		lookupView.setWicketId("entityLookupTableListView");
		PageableListView entityLookupTableListView = app.getViewMeta(modelCode)
				.getPageableListView("EntityLookupTableListView", lookupModel,
						lookupView);
		add(entityLookupTableListView);
		if (!app.getAccessPoint().isConceptDisplayAllowed(getAppSession(),
				lookupConceptConfig)) {
			entityLookupTableListView.setVisible(false);
		}

		add(new PagingNavigator("blockNavigator", entityLookupTableListView));
	}

}
