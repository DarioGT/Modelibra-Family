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
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.IEntity;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmDisplayPage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity display slide page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityDisplaySlidePage extends DmDisplayPage {

	private static final long serialVersionUID = 102160L;

	private static Log log = LogFactory.getLog(EntityDisplaySlidePage.class);

	/**
	 * Constructs an entity display slide page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityDisplaySlidePage(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();

		IEntity entity = viewModel.getEntity();

		IEntity firstEntity = null;
		if (entity == null) {
			firstEntity = (IEntity) viewModel.getEntities().first();
			if (firstEntity == null) {
				Panel panel = new Panel("entityDisplaySlidePanel");
				panel.setVisible(false);
				add(panel);
			} else {
				ViewModel entityDisplaySlidePanelModel = new ViewModel();
				entityDisplaySlidePanelModel.copyPropertiesFrom(viewModel);
				entityDisplaySlidePanelModel.setEntity(firstEntity);

				View entityDisplaySlidePanelView = new View();
				entityDisplaySlidePanelView.copyPropertiesFrom(view);
				entityDisplaySlidePanelView.setPage(this);
				entityDisplaySlidePanelView.setUpdate(false);
				entityDisplaySlidePanelView
						.setWicketId("entityDisplaySlidePanel");

				add(app.getViewMeta(modelCode).getPanel(
						"EntityDisplaySlidePanel",
						entityDisplaySlidePanelModel,
						entityDisplaySlidePanelView));
			}
		} else {
			ViewModel entityDisplaySlidePanelModel = new ViewModel();
			entityDisplaySlidePanelModel.copyPropertiesFrom(viewModel);

			View entityDisplaySlidePanelView = new View();
			entityDisplaySlidePanelView.copyPropertiesFrom(view);
			entityDisplaySlidePanelView.setPage(this);
			entityDisplaySlidePanelView.setUpdate(false);
			entityDisplaySlidePanelView.setWicketId("entityDisplaySlidePanel");

			add(app.getViewMeta(modelCode).getPanel("EntityDisplaySlidePanel",
					entityDisplaySlidePanelModel, entityDisplaySlidePanelView));
		}
	}

}
