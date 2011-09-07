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
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmUpdatePage;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity confirm remove page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
public class EntityConfirmRemovePage extends DmUpdatePage {

	private static final long serialVersionUID = 102070L;

	private static Log log = LogFactory.getLog(EntityConfirmRemovePage.class);

	/**
	 * Constructs an entity confirm remove page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 * 
	 */
	public EntityConfirmRemovePage(final ViewModel viewModel, final View view) {
		super(viewModel, view);
		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();

		add(new FeedbackPanel("feedbackPanel"));

		ViewModel entityDisplayPanelModel = new ViewModel();
		entityDisplayPanelModel.copyPropertiesFrom(viewModel);

		View entityDisplayPanelView = new View();
		entityDisplayPanelView.copyPropertiesFrom(view);
		entityDisplayPanelView.setPage(this);
		entityDisplayPanelView.setWicketId("entityDisplayPanel");
		entityDisplayPanelView.setUpdate(false);

		add(app.getViewMeta(modelCode).getPanel("EntityDisplayPanel",
				entityDisplayPanelModel, entityDisplayPanelView));

		View entityConfirmRemovePanelView = new View();
		entityConfirmRemovePanelView.copyPropertiesFrom(view);
		entityConfirmRemovePanelView.setPage(this);
		entityConfirmRemovePanelView.setWicketId("entityConfirmRemovePanel");
		add(app.getViewMeta(modelCode).getPanel("EntityConfirmRemovePanel",
				entityDisplayPanelModel, entityConfirmRemovePanelView));
	}
}
