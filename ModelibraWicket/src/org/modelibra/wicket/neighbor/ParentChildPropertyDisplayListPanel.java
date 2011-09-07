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

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListView;
import org.modelibra.config.ConceptConfig;
import org.modelibra.wicket.app.DomainApp;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.util.LocalizedText;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * Displays parent child property list as a panel.
 * 
 * @author Vensada Okanovic
 * @version 2007-11-28
 */
public class ParentChildPropertyDisplayListPanel extends DmPanel {

	private static final long serialVersionUID = 102390L;

	public ParentChildPropertyDisplayListPanel(final ViewModel viewModel,
			final View view) {
		super(viewModel, view);
		DomainApp app = (DomainApp) getApplication();
		String modelCode = viewModel.getModel().getModelConfig().getCode();

		ConceptConfig parentConceptConfig = viewModel.getEntities()
				.getConceptConfig();

		String sectionTitle = LocalizedText.getApplicationPropertiesText(this,
				view.getTitle());
		Label sectionTitleLabel = new Label("sectionTitle", sectionTitle);
		add(sectionTitleLabel);

		ViewModel parentChildModel = new ViewModel();
		parentChildModel.copyPropertiesFrom(viewModel);

		View parentChildView = new View();
		parentChildView.copyPropertiesFrom(view);
		parentChildView.setWicketId("parentChildPropertyList");
		parentChildView.getUserProperties().addUserProperty("shortText",
				Boolean.TRUE);

		ListView parentChildPropertyDisplayListListView = app.getViewMeta(
				modelCode).getListView(
				"ParentChildPropertyDisplayListListView", parentChildModel,
				parentChildView);
		add(parentChildPropertyDisplayListListView);
		if (!app.getAccessPoint().isConceptDisplayAllowed(getAppSession(),
				parentConceptConfig)) {
			parentChildPropertyDisplayListListView.setVisible(false);
		}
	}

}
