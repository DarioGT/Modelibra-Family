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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListView;
import org.modelibra.IEntities;
import org.modelibra.config.ConceptConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity display list panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityDisplayListPanel extends DmPanel {

	private static final long serialVersionUID = 102110L;

	private static Log log = LogFactory.getLog(EntityDisplayListPanel.class);

	/**
	 * Constructs an entity display list panel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityDisplayListPanel(final ViewModel viewModel, final View view) {
		super(view.getWicketId());
		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();

		IEntities entities = viewModel.getEntities();

		ConceptConfig conceptConfig = entities.getConceptConfig();
		String conceptsName = LocalizedText.getConceptsName(this, entities);
		add(new Label("conceptsName", conceptsName));

		ViewModel entityDisplayListModel = new ViewModel();
		entityDisplayListModel.copyPropertiesFrom(viewModel);

		View entityDisplayListView = new View();
		entityDisplayListView.copyPropertiesFrom(view);
		entityDisplayListView.setWicketId("entityDisplayListListView");

		ListView entityDisplayListListView = app.getViewMeta(modelCode)
				.getListView("EntityDisplayListListView",
						entityDisplayListModel, entityDisplayListView);
		add(entityDisplayListListView);
		if (!app.getAccessPoint().isConceptDisplayAllowed(getAppSession(),
				conceptConfig)) {
			entityDisplayListListView.setVisible(false);
		}
	}

}
