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
package org.modelibra.wicket.neighbor;

import org.apache.wicket.markup.html.list.ListView;
import org.modelibra.IEntities;
import org.modelibra.IEntity;
import org.modelibra.DomainModel;
import org.modelibra.ModelMeta;
import org.modelibra.config.ConceptConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Displays child property list as a panel.
 * 
 * @author Vensada Okanovic
 * @version 2007-11-28
 */
public class ChildPropertyDisplayListPanel extends DmPanel {

	private static final long serialVersionUID = 102020L;

	/**
	 * Constructs a child property display list panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public ChildPropertyDisplayListPanel(final ViewModel viewModel,
			final View view) {
		super(viewModel, view);
		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();

		ViewModel childModel = new ViewModel();
		childModel.copyPropertiesFrom(viewModel);
		IEntity<?> parentEntity = childModel.getEntity();
		ViewModel contextModel = new ViewModel();
		contextModel.copyPropertiesFrom(viewModel);
		contextModel.setEntity(parentEntity);
		childModel.setContextViewModel(contextModel);
		// childViewModel.setContextEntity(parentEntity);
		String childNeighbor = (String) viewModel.getUserProperties()
				.getUserProperty("childNeighbor");
		DomainModel domainModel = (DomainModel) childModel.getModel();
		ModelMeta modelMeta = domainModel.getModelMeta();
		IEntities<?> childEntities = modelMeta.getChildNeighbor(parentEntity,
				childNeighbor);
		childModel.setEntities(childEntities);

		View childView = new View();
		childView.copyPropertiesFrom(view);
		childView.setWicketId("childPropertyList");

		ListView childPropertyDisplayListView = app.getViewMeta(modelCode)
				.getListView("ChildPropertyDisplayListListView", childModel,
						childView);
		add(childPropertyDisplayListView);
		ConceptConfig childConceptConfig = childEntities.getConceptConfig();
		if (!app.getAccessPoint().isConceptDisplayAllowed(getAppSession(),
				childConceptConfig)) {
			childPropertyDisplayListView.setVisible(false);
		}
	}

}
