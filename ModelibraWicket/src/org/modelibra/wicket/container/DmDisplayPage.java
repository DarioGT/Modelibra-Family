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
package org.modelibra.wicket.container;

import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Dm display page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-28
 */
@SuppressWarnings("serial")
public abstract class DmDisplayPage extends DmPage {

	/**
	 * Constructs a dm display page.
	 * 
	 * @param viewModel
	 *            view model
	 * @param view
	 *            view
	 */
	public DmDisplayPage(final ViewModel viewModel, final View view) {
		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();

		ViewModel menuModel = new ViewModel();
		menuModel.copyPropertiesFrom(viewModel);
		View menuView = new View();
		menuView.copyPropertiesFrom(view);
		menuView.setWicketId("dmMenuPanel");
		add(app.getViewMeta(modelCode).getDmMenuPanel(menuModel, menuView));
	}

}
