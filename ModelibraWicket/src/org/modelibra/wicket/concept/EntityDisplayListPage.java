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
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmDisplayPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity display list page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityDisplayListPage extends DmDisplayPage {

	private static final long serialVersionUID = 102100L;

	private static Log log = LogFactory.getLog(EntityDisplayListPage.class);

	/**
	 * Constructs an entity display list page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityDisplayListPage(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();

		ViewModel entityDisplayTablePanelModel = new ViewModel();
		entityDisplayTablePanelModel.copyPropertiesFrom(viewModel);

		View entityDisplayListPanelView = new View();
		entityDisplayListPanelView.copyPropertiesFrom(view);
		view.setPage(this);
		entityDisplayListPanelView.setContextView(view);
		entityDisplayListPanelView.setPage(this);
		entityDisplayListPanelView.setUpdate(false);
		entityDisplayListPanelView.setWicketId("entityDisplayListPanel");

		add(app.getViewMeta(modelCode).getPanel("EntityDisplayListPanel",
				entityDisplayTablePanelModel, entityDisplayListPanelView));
	}

}
