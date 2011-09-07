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
 * Entity display min page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityDisplayMinPage extends DmDisplayPage {

	private static final long serialVersionUID = 102120L;

	private static Log log = LogFactory.getLog(EntityDisplayMinPage.class);

	/**
	 * Constructs an entity display min page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityDisplayMinPage(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();

		View entityDisplayMinPanelView = new View();
		entityDisplayMinPanelView.copyPropertiesFrom(view);
		entityDisplayMinPanelView.setWicketId("entityDisplayMinPanel");
		entityDisplayMinPanelView.setPage(this);
		entityDisplayMinPanelView.setUpdate(false);

		add(app.getViewMeta(modelCode).getPanel("entityDisplayMinPanel",
				viewModel, entityDisplayMinPanelView));
	}

}
