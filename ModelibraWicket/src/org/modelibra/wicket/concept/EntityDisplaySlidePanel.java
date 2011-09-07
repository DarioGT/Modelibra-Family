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
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.DomainModel;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.NeighborsConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.concept.navigation.EntitySlideNavigatePanel;
import org.modelibra.wicket.container.PanelListView;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity display slide panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-01-19
 */
public class EntityDisplaySlidePanel extends EntityDisplayPanel {

	private static final long serialVersionUID = 102170L;

	private static Log log = LogFactory.getLog(EntityDisplaySlidePanel.class);

	/**
	 * Constructs an entity display slide panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityDisplaySlidePanel(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();

		final IEntities entities = viewModel.getEntities();
		final IEntity entity = viewModel.getEntity();

		ConceptConfig conceptConfig = entity.getConceptConfig();
		ViewModel slideNavigateModel = new ViewModel();
		slideNavigateModel.copyPropertiesFrom(viewModel);

		View slideNavigateView = new View();
		slideNavigateView.copyPropertiesFrom(view);
		slideNavigateView.setWicketId("slideNavigatePanel");
		Panel entitySlideNavigatePanel = new EntitySlideNavigatePanel(
				slideNavigateModel, slideNavigateView);
		add(entitySlideNavigatePanel);
		if (!app.getAccessPoint().isConceptDisplayAllowed(getAppSession(),
				conceptConfig)) {
			entitySlideNavigatePanel.setVisible(false);
		}

		List<Panel> neighborList = new ArrayList<Panel>();
		NeighborsConfig neighborsConfig = conceptConfig.getNeighborsConfig();
		for (IEntity neighborConfigEntity : neighborsConfig) {
			NeighborConfig neighborConfig = (NeighborConfig) neighborConfigEntity;
			if (neighborConfig.getType().equals("child")) {
				String neighborCode = neighborConfig.getCode();
				DomainModel model = (DomainModel) viewModel.getModel();
				IEntities childEntities = (IEntities) model.getModelMeta()
						.getChildNeighbor(entity, neighborCode);

				ViewModel neighborModel = new ViewModel();
				neighborModel.copyPropertiesFrom(viewModel);
				ViewModel contextModel = new ViewModel();
				contextModel.copyPropertiesFrom(viewModel);
				contextModel.setEntity(entity);
				contextModel.setEntities(entities);
				neighborModel.setContextViewModel(contextModel);
				neighborModel.setEntities(childEntities);

				View neighborView = new View();
				neighborView.copyPropertiesFrom(view);
				neighborView.setWicketId("entityDisplayListPanel");

				Panel entityDisplayListPanel = app.getViewMeta(modelCode)
						.getPanel("EntityDisplayListPanel", neighborModel,
								neighborView);
				neighborList.add(entityDisplayListPanel);
				if (!app.getAccessPoint().isNeighborDisplayAllowed(
						getAppSession(), neighborConfig)) {
					entityDisplayListPanel.setVisible(false);
				}
			} // if (neighborConfig.getType().equals("child")) {
		} // for

		add(new PanelListView("neighborEntityListPanelListView", neighborList));
	}

}
