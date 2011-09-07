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

import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Entity Parent-Child update panel.
 * 
 * @author Vedad Kirlic
 * @version 2008-01-19
 */
public class EntityParentChildUpdatePanel extends EntityParentChildPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs the EntityParentChildUpdatePanel.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public EntityParentChildUpdatePanel(final ViewModel viewModel,
			final View view) {
		super(viewModel, view);
	}

	/**
	 * Gets panel for parent entity. Override this method to provide different
	 * panel then default. Do not set wicketId for view parameter.
	 * 
	 * @param viewModel
	 *            viewModel
	 * @param view
	 *            view
	 * @return Panel
	 */
	protected Panel getParentPanel(ViewModel viewModel, View view) {
		DomainApp app = (DomainApp) getApplication();
		String model = viewModel.getModel().getModelConfig().getCode();
		return app.getViewMeta(model).getPanel("EntityEditFormPanel",
				viewModel, view);
	}

	/**
	 * Gets panel for child entities. Override this method to provide different
	 * panel then default. Do not set wicketId for view parameter.
	 * 
	 * @param viewModel
	 *            viewModel
	 * @param view
	 *            view
	 * @return Panel
	 */
	protected Panel getChildPanel(ViewModel viewModel, View view) {
		DomainApp app = (DomainApp) getApplication();
		String model = viewModel.getModel().getModelConfig().getCode();
		return app.getViewMeta(model).getPanel("EntityUpdateTablePanel",
				viewModel, view);
	}

}
